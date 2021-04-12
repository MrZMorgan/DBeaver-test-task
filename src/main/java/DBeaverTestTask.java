import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBeaverTestTask {

    private final static String user = "postgres";
    private final static String password = "1234";
    private final static String url = "jdbc:postgresql://localhost:5432/dbeaver";
    private final static String DB_QUERY = "SELECT department, sum(salary) FROM employees GROUP BY department;";

    public List<DepartmentSalaryDto> readDataFromTable() throws SQLException {
        Connection connection = null;
        List<DepartmentSalaryDto> dtoList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(DB_QUERY);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dtoList.add(
                        new DepartmentSalaryDto()
                        .department(resultSet.getString("department"))
                        .salarySum(resultSet.getInt("sum"))
                );
            }
        } finally {
            connection.close();
        }
        return dtoList;
    }
}

class DepartmentSalaryDto {

    private String depatrment;
    private int salarySum;

    public DepartmentSalaryDto() {
    }

    public DepartmentSalaryDto department(String depatrment) {
        this.depatrment = depatrment;
        return this;
    }

    public DepartmentSalaryDto salarySum(int salarySum) {
        this.salarySum = salarySum;
        return this;
    }

    @Override
    public String toString() {
        return depatrment + " : " + salarySum;
    }
}

class Solution {

    public static void main(String[] args) throws SQLException {
        DBeaverTestTask dBeaverTestTask = new DBeaverTestTask();
        List<DepartmentSalaryDto> dtoList = dBeaverTestTask.readDataFromTable();
        dtoList.forEach(System.out::println);
    }
}
