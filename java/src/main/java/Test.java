import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author 春阳
 * @date 2021-06-01 19:09
 */
public class Test {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Zhang Chunyang\\Downloads\\67053_hl-p-ticket-war01_catalina.out.tar\\67053_hl-p-ticket-war01_catalina.out\\aaaaaaaaaaaaaaa.txt");
        String encoding = "utf-8";
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
             BufferedReader bufferedReader = new BufferedReader(read)) {
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt.substring(1, 24));
                    System.out.println(lineTxt.substring(lineTxt.indexOf("http://new-crm.jirongyunke.net/csc-web//eventInfo/saveQiYuEvent, param : ") + 73));
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
        }
    }
}
/*
 */
