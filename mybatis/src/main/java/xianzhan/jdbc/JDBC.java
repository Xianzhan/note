package xianzhan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author xianzhan
 * @since 2020-07-10
 */
public class JDBC {

    private static Connection conn;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final var driver = "com.mysql.jdbc.Driver";
        final var url = "jdbc:mysql://172.30.166.95:3306/lxz";
        final var username = "root";
        final var password = "123456";

        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        var preparedStatement = conn.prepareStatement("select * from employee where id > ?");
        preparedStatement.setInt(1, 1);
        var resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String msg = """
                    id: %d, salary: %d
                    """.formatted(resultSet.getInt("id"), resultSet.getInt("salary"));
            System.out.print(msg);
        }

        insert(124);
    }

    private static void insert(int salary) throws SQLException {
        String iSql = """
                insert into employee (salary) value (?)
                """;
        var preparedStatement = conn.prepareStatement(iSql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, salary);
        var update = preparedStatement.executeUpdate();
        System.out.println(update);

        var generatedKeys = preparedStatement.getGeneratedKeys();
        var metaData = generatedKeys.getMetaData();
        var columnCount = metaData.getColumnCount();
        var builder = new StringBuilder();
        while (generatedKeys.next()) {
            while (columnCount > 0) {
                var columnName = metaData.getColumnName(columnCount);
                var string = generatedKeys.getString(columnName);
                builder.append(columnName)
                        .append(':')
                        .append(string)
                        .append("  ");
                columnCount--;
            }
        }
        System.out.println(builder);
    }
}
