import java.io.*;

public class MeasureSize {

    private static long size;
    private static final long KB = 1024, MB = 1048576, GB = 1073741824;

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (; ; ) {
            try {
                System.out.println("Укажите путь к папке - ");
                File f = new File(reader.readLine());
                if (f.isDirectory()) {
                    File[] ff = f.listFiles();
                    for (File fs : ff) {
                        size = size + fs.length();
                    }
                } else {
                    System.out.println("Папка не найдена");
                    continue;
                }
                getSize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void getSize() {
        if (size > GB) {
            System.out.println("Размер всех вложений папки - " + (double) size / GB + " Гб");
        } else if (size > MB) {
            System.out.println("Размер всех вложений папки - " + (double) size / MB + " Мб");
        } else if (size > KB) {
            System.out.println("Размер всех вложений папки - " + (double) size / KB + " Кб");
        } else {
            System.out.println("Размер всех вложений папки - " + size + " Бт");
        }
    }
}
