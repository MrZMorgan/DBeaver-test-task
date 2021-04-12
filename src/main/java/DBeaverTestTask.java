import java.sql.*;

public class DBeaverTestTask {

    private final static String user = "postgres";
    private final static String password = "1234";
    private final static String url = "jdbc:postgresql://localhost:5432/dbeaver";
    private final static String DB_QUERY = "SELECT department, sum(salary) FROM employees GROUP BY department;";

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            PreparedStatement statement = connection.prepareStatement(DB_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("department")
                        + " : "
                        + resultSet.getString("sum")
                );
            }
        }
    }
}

