import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExperiments {
    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true";
        String url = "jdbc:mysql://localhost:3306/skillbox?useLegacyDatetimeCode=false&serverTimezone=Australia/Sydney&useSSL=false";
        String user = "root";
        String pass = "mmm333";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT course_name, COUNT(course_name)/12\n" +
                    "FROM skillbox.purchaselist\n" +
                    "GROUP BY course_name;");

            while (resultSet.next()) {
                System.out.printf("%-35s --> %-2s%n", resultSet.getString("course_name"),
                        resultSet.getString("COUNT(course_name)/12"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

