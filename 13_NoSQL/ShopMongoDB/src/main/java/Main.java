import java.io.*;

public class Main {

    private static String commandExample = "Примеры команд: \n * ДОБАВИТЬ_МАГАЗИН Девяточка \n * ДОБАВИТЬ_ТОВАР Вафли 54"
            + "\n * ВЫСТАВИТЬ_ТОВАР Вафли Девяточка \n * СТАТИСТИКА_ТОВАРОВ \n * EXIT - завершает работу программы";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(commandExample);

        for (; ; ) {
            System.out.println("Введите команду:");
            String[] input = MongoDB.split(reader.readLine().trim());

            String instruction = input[0];
            String object = input.length > 1 ? input[1] : "";

            switch (instruction) {
                case "ДОБАВИТЬ_МАГАЗИН":
                    MongoDB.addShop(object);
                    break;
                case "ДОБАВИТЬ_ТОВАР":
                    MongoDB.addProducts(object);
                    break;
                case "ВЫСТАВИТЬ_ТОВАР":
                    MongoDB.addProductsToShop(object);
                    break;
                case "СТАТИСТИКА_ТОВАРОВ":
                    MongoDB.printStatistic();
                    break;
                case "EXIT":
                    MongoDB.shutdownDB();
                    return;
                default:
                    System.out.println("Неверный ввод! \n" +commandExample);
            }
        }
    }

}