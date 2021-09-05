package corejava.chapter11.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author 春阳
 * @date 2021-06-16 11:17
 */
public class MultiPartDownLoad {
    /**
     * 线程下载成功标志
     */
    private static int flag = 0;

    /**
     * 服务器请求路径
     */
    private String serverPath;
    /**
     * 本地路径
     */
    private String localPath;
    /**
     * 线程计数同步辅助
     */
    private CountDownLatch latch;

    // 定长线程池
    private static ExecutorService threadPool;

    public MultiPartDownLoad(String serverPath, String localPath) {
        this.serverPath = serverPath;
        this.localPath = localPath;
    }

    public boolean executeDownLoad() {
        try {
            URL url = new URL(serverPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);//设置超时时间
            conn.setRequestMethod("GET");//设置请求方式
            conn.setRequestProperty("Connection", "Keep-Alive");
            int code = conn.getResponseCode();
            if (code != 200) {
                System.out.printf("无效网络地址：%s%n", serverPath);
                return false;
            }
            //服务器返回的数据的长度，实际上就是文件的长度,单位是字节
            //int length = conn.getContentLength();  //文件超过2G会有问题
            long length = getRemoteFileSize(serverPath);
            System.out.println("文件总长度:" + length + "字节(B)");
            RandomAccessFile raf = new RandomAccessFile(localPath, "rwd");
            //指定创建的文件的长度
            raf.setLength(length);
            raf.close();
            //分割文件
            int partCount = Constans.MAX_THREAD_COUNT;
            int partSize = (int)(length / partCount);
            latch = new CountDownLatch(partCount);
            threadPool = Constans.getMyThreadPool();
            for (int threadId = 1; threadId <= partCount; threadId++) {
                // 每一个线程下载的开始位置
                long startIndex = (threadId - 1) * partSize;
                // 每一个线程下载的开始位置
                long endIndex = startIndex + partSize - 1;
                if (threadId == partCount) {
                    //最后一个线程下载的长度稍微长一点
                    endIndex = length;
                }
                System.out.println("线程" + threadId + "下载:" + startIndex + "字节~" + endIndex + "字节");
                threadPool.execute(new DownLoadThread(threadId, startIndex, endIndex, latch));
            }
            latch.await();
            if(flag == 0){
                return true;
            }
        } catch (Exception e) {
            System.out.printf("文件下载失败，文件地址：%s,失败原因：%s%n", serverPath, e.getMessage());
        } finally {
            threadPool.shutdown();
        }
        return false;
    }


    /**
     * 内部类用于实现下载
     */
    public class DownLoadThread implements Runnable {
        /**
         * 线程ID
         */
        private int threadId;
        /**
         * 下载起始位置
         */
        private long startIndex;
        /**
         * 下载结束位置
         */
        private long endIndex;

        private CountDownLatch latch;

        public DownLoadThread(int threadId, long startIndex, long endIndex, CountDownLatch latch) {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程" + threadId + "正在下载...");
                URL url = new URL(serverPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestMethod("GET");
                //请求服务器下载部分的文件的指定位置
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                conn.setConnectTimeout(5000);
                int code = conn.getResponseCode();
                System.out.println("线程" + threadId + "请求返回code=" + code);
                InputStream is = conn.getInputStream();//返回资源
                RandomAccessFile raf = new RandomAccessFile(localPath, "rwd");
                //随机写文件的时候从哪个位置开始写
                raf.seek(startIndex);//定位文件
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                }
                is.close();
                raf.close();
                System.out.println("线程" + threadId + "下载完毕");
            } catch (Exception e) {
                //线程下载出错
                MultiPartDownLoad.flag = 1;
                System.out.println(e.getMessage());
            } finally {
                //计数值减一
                latch.countDown();
            }

        }
    }

    /**
     * 内部方法，获取远程文件大小
     * @param remoteFileUrl
     * @return
     * @throws IOException
     */
    private long getRemoteFileSize(String remoteFileUrl) throws IOException {
        long fileSize = 0;
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(remoteFileUrl).openConnection();
        httpConnection.setRequestMethod("HEAD");
        int responseCode = 0;
        try {
            responseCode = httpConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseCode >= 400) {
            System.out.println("Web服务器响应错误!");
            return 0;
        }
        String sHeader;
        for (int i = 1;; i++) {
            sHeader = httpConnection.getHeaderFieldKey(i);
            if (sHeader != null && sHeader.equals("Content-Length")) {
                fileSize = Long.parseLong(httpConnection.getHeaderField(sHeader));
                break;
            }
        }
        return fileSize;
    }

    public static void main(String[] args) {
        MultiPartDownLoad downLoad = new MultiPartDownLoad("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fyouimg1.c-ctrip.com%2Ftarget%2Ftg%2F210%2F360%2F468%2F12c6835f2b124851b7e18c7dc6fdc281.jpg&refer=http%3A%2F%2Fyouimg1.c-ctrip.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626405928&t=2bc8a8c9ddc861a65a0840a3164b886d", "E:\\home\\data\\aa.jpg");
        downLoad.executeDownLoad();
    }
}
