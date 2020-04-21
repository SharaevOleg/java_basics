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
