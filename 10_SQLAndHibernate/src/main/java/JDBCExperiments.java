import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExperiments {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String urlNew = "jdbc:mysql://localhost:3306/skillbox?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;seLegacyDatetimeCode=false&amp;serverTimezone=UTC&amp;useSSL=false";

        String user = "root";
        String pass = "mmm333";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statment = connection.createStatement();
            ResultSet resultSet = statment.executeQuery("SELECT * FROM Courses");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
