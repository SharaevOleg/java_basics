import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static String staffFile =
            "E:\\Skillbox_JAVA-разработчик с нуля\\skillbox\\java_basics\\07_AdvancedOOPFeatures\\" +
                    "LambdaExpressions\\data\\staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        staff.stream().filter(e -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getWorkStart());
            return calendar.get(Calendar.YEAR) == 2017;
        })
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();

        Stream stream = staff.stream();
        stream.sorted(Comparator.comparing(Employee::getWorkStart)).forEach(System.out::println);

        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}