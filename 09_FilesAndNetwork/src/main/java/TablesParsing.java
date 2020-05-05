import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TablesParsing {

    public static double debit;
    public static double credit;
    static Map<String, Double> movementList = new HashMap<>();


    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("09_FilesAndNetwork/files/movementList.csv"));
        for (int i = 1; i < lines.size(); i++) {
            sumData(lines.get(i));
        }
        System.out.printf("Общий приход: %.2f%n", debit); // длина десятичной части 2 %n- перенос строки
        System.out.printf("Общий расход: %.2f%n%n", credit);
        for (String str : movementList.keySet()) {
            System.out.printf("%S - %.2f%n", str, movementList.get(str)); // % S - форматирование строки(все заглавные)
        }
    }


    static void sumData(String str) {
        String[] fragments = str.split(",");
        double fragmentsSix = Double.parseDouble(fragments[6]);
        double fragmentsSeven = Double.parseDouble(fragments[7]
                .replaceAll("\\\"", "").replace(',', '.'));
        if (fragmentsSix == 0) {
            sumExpense(fragments[5], fragmentsSeven);
        } else debit += fragmentsSix;
    }

    static void sumExpense(String str, double exp) {
        credit += exp;
        String[] fragments = str.trim().split(" {3,}");
        String[] fragmentsOne = fragments[1].split("/");
        String[] fragmentsSplit = fragmentsOne[fragmentsOne.length - 1].split("\\\\");
        String supplies = fragmentsSplit[fragmentsSplit.length - 1];
        if (!movementList.containsKey(supplies)) {
            movementList.put(supplies, exp);
        } else {
            double sum = movementList.get(supplies);
            sum += credit;
            movementList.put(supplies.trim(), sum);
        }
    }
}


//import java.io.BufferedReader;
//        import java.io.FileNotFoundException;
//        import java.io.FileReader;
//        import java.io.IOException;
//        import java.nio.file.Files;
//        import java.nio.file.Paths;
//        import java.text.ParseException;
//        import java.text.SimpleDateFormat;
//        import java.util.*;
//        import java.util.stream.Stream;
//
//public class TablesParsing {
//
//    private static String staffFile = "09_FilesAndNetwork/files/movementList.csv";
//    //            "E:\\Skillbox_JAVA-разработчик с нуля\\skillbox\\java_basics\\07_AdvancedOOPFeatures\\" +
////                    "LambdaExpressions\\data\\staff.txt";
//    private static String dateFormat = "dd.MM.yyyy";
//
//
//    public static void main(String[] args) throws IOException, ParseException {
//
//        List<String> lines = Files.readAllLines(Paths.get(staffFile));
////        lines.forEach(System.out::println);
////        System.out.println(lines.get(1));
//
//
//        List<String> prixod = new ArrayList<>();
//        List<String> rasxod = new ArrayList<>();
//        List<String> operation = new ArrayList<>();
//
//
//        for (String line : lines) {
//            String[] fragments = line.split(",");
////            if (fragments.length != 8) {
////                System.out.println("Wrong line: " + line);
////                continue;
////            }
//            operation.add(fragments[5]);
//            prixod.add(fragments[6]);
//            rasxod.add(fragments[7]);
//        }
////        prixod.forEach(System.out::println);
////        rasxod.forEach(System.out::println);
////        operation.forEach(System.out::println);
//
//
//        List<String> dta = new ArrayList<>();
//
//        for (String line : operation) {
////            System.out.println(line);
////            String str = line.replaceAll("\\\\|/", ",");
//
//            String str = line
//                    .replaceAll("\\s{3,}|\\\\|/|\\d\\d\\.\\d\\d\\.\\d\\d\\s\\d\\d\\.\\d\\d\\.\\d\\d", ",")
//                    .replaceAll("548673\\+{2,}1028,", "")
//                    .replaceAll("\\d{1,},|\\d{1,}\\s{1,},", "")
//                    .replaceAll(",,{1,}", ",")
//                    .replaceAll("MCC\\d{1,}", "");
//
//
//            System.out.println(str);
//            String[] fragments = str.split(",");
//            if (fragments.length <= 1) {
//                System.out.println("Wrong line: " + line);
//                continue;
//            }
//
//            dta.add(fragments[1]);
//        }
//
////        System.out.println(dta.size());
//        dta.forEach(System.out::println);
////        operation.forEach(System.out::println);
//
//
////
////        ArrayList<Employee> staff = loadStaffFromFile();
////
////        staff.stream().filter(e -> {
////            Calendar calendar = Calendar.getInstance();
////            calendar.setTime(e.getWorkStart());
////            return calendar.get(Calendar.YEAR) == 2017;
////        })
////                .max(Comparator.comparing(Employee::getSalary))
////                .ifPresent(System.out::println);
////
////    }
////
////    private static ArrayList<Employee> loadStaffFromFile() {
////        ArrayList<Employee> staff = new ArrayList<>();
////
////        Stream stream = staff.stream();
////        stream.sorted(Comparator.comparing(Employee::getWorkStart)).forEach(System.out::println);
////
////        try {
////            List<String> lines = Files.readAllLines(Paths.get(staffFile));
////            for (String line : lines) {
////                String[] fragments = line.split(",");
////                if (fragments.length != 3) {
////                    System.out.println("Wrong line: " + line);
////                    continue;
////                }
////                staff.add(new Employee(
////                        fragments[0],
////                        Integer.parseInt(fragments[1]),
////                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
////                ));
////            }
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        return staff;
//    }
//}