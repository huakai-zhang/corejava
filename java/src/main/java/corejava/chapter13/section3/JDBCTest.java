package corejava.chapter13.section3;

import java.sql.*;

/**
 * @author Spring Zhang
 * @date 2019/12/30 15:55
 */
public class JDBCTest {
    public static void main(String[] args) {
        //Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            if (coon != null) {
                Statement stat = coon.createStatement();
                SQLWarning w = stat.getWarnings();
                while (w != null) {
                    w = w.getNextWarning();
                }
                DatabaseMetaData aa = coon.getMetaData();
                System.out.println(aa.getMaxStatements());
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        } catch (SQLException e) {
            // 产生符合X/Open或SQL:2013标准的字符串（调用DatabaseMetaData接口的getSQLStateType方法可以查看驱动程序所使用的标准）
            e.getSQLState();
            // 错误代码是与具体的供应商相关
            e.getErrorCode();
            for (Throwable t : e) {

            }
        }
    }
}
