package corejava.chapter11.download;

import java.io.RandomAccessFile;

/**
 * @author 春阳
 * @date 2021-06-16 11:01
 */
public class RAFReadFile extends Thread {
    private int start = 0;
    private int parts = 0;
    private String file = "";

    public RAFReadFile(int start, int parts, String file){
        this.start = start;
        this.file = file;
        this.parts = parts;
    }

    @Override
    public void run(){
        System.out.println("第" + start + "个线程正在运行！");
        try {
            RandomAccessFile raf = new RandomAccessFile(file , "rw");
            long len =  raf.length();
            //跳到第start部分开始读
            raf.seek(len * start / parts);
            //读取len/parts个字节
            byte[] buf = new byte[(int)(len / parts)];
            raf.read(buf);
            raf.close();

            int index = file.lastIndexOf(".");
            String newFileName = file.substring(0,index) + "_bak" + file.substring(index);
            System.out.println(newFileName);
            raf = new RandomAccessFile(newFileName, "rw");
            raf.seek(len * start / parts);
            raf.write(buf);
            raf.getFD().sync();
            raf.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i ++){
            RAFReadFile rf = new RAFReadFile(i, 5, "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201306%2F21%2F220728m5zcr5ecr7cqq7bw.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1626405012&t=cf80686215ff820cb57c9b0befb9170e");
            rf.start();
        }
        System.out.println("复制成功！");
    }

}
