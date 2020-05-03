import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExperiments {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true";
        String urlNew = "jdbc:mysql://localhost:3306/skillbox?useLegacyDatetimeCode=false&serverTimezone=Australia/Sydney&useSSL=false";
        String user = "root";
        String pass = "mmm333";
        int i = 01;


        for (; i <= 12; ) {
            try {
                Connection connection = DriverManager.getConnection(urlNew, user, pass);
                Statement statment = connection.createStatement();
                String str = String.valueOf(i);
                ResultSet resultSet = statment.executeQuery("SELECT course_name, subscription_date, COUNT(course_name)\n" +
                        "FROM skillbox.purchaselist\n" +
                        "WHERE subscription_date BETWEEN STR_TO_DATE('2018-" + str + "-00 00:00:00', '%Y-%m-%d %H:%i:%s') \n" +
                        "  AND STR_TO_DATE('2018-" + str + "-31 23:59:59', '%Y-%m-%d %H:%i:%s')\n" +
                        "GROUP BY course_name;");

                System.out.println("\n****** " + "Среднее количество покупок " + str + " месяц");
                while (resultSet.next()) {
                    System.out.printf("%-35s --> %-2s%n", resultSet.getString("course_name"), resultSet.getString("COUNT(course_name)"));

                }

                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
