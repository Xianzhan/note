package xianzhan.note.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMain {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stat = conn.createStatement();
        boolean execute = stat.execute("SELECT 1");
        System.out.println(execute);
    }
}
