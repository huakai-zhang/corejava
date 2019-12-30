package chapter13.section3;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Spring Zhang
 * @date 2019/12/30 15:55
 */
public class JDBCTest {
    public static void main(String[] args) throws Exception {
        //Class.forName("com.mysql.jdbc.Driver");
        Connection coon =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        if (coon != null) {
            System.out.println("连接成功");
        } else {
            System.out.println("连接失败");
        }
    }
}
