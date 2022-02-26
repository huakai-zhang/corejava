package corejava.chapter13;

import java.sql.*;

public class TransactionTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        Statement stat = conn.createStatement();
        stat.executeUpdate("");
        Savepoint svpt = conn.setSavepoint();
        stat.executeUpdate("");
       /* if () {
            conn.rollback(svpt);
        }*/
        conn.commit();
        conn.releaseSavepoint(svpt);
        String command = "CREATE TABLE ...";
        stat.addBatch(command);

       /* while () {
            command = "INSERT INTO ... VALUES ("+ ... +")";
            stat.addBatch(command);
        }*/

        int[] counts = stat.executeBatch();
    }
}
