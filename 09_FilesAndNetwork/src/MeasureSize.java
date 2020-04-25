import java.io.*;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MeasureSize {

    private static long size;
    private static final long KB = 1024, MB = 1048576, GB = 1073741824;

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        for (; ; ) {
//            try {
//                System.out.println("Укажите путь к папке - ");
//                File f = new File(reader.readLine());
//                if (f.isDirectory()) {
//                    File[] ff = f.listFiles();
//                    for (File fs : ff) {
//                        size = size + fs.length();
//                    }
//                } else {
//                    System.out.println("Папка не найдена");
//                    continue;
//                }
//                getSize();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    static void getSize() {
//        if (size > GB) {
//            System.out.println("Размер всех вложений папки - " + (double) size / GB + " Гб");
//        } else if (size > MB) {
//            System.out.println("Размер всех вложений папки - " + (double) size / MB + " Мб");
//        } else if (size > KB) {
//            System.out.println("Размер всех вложений папки - " + (double) size / KB + " Кб");
//        } else {
//            System.out.println("Размер всех вложений папки - " + size + " Бт");
//        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        Path path = Paths.get(reader.readLine());
//        File f = new File(reader.readLine());
        Stream<Path> stream = Files.walk(Paths.get(reader.readLine()));
        size = stream.filter(f -> f.toFile().isFile())
                .mapToLong(f -> f.toFile().length())
                .reduce((s1, s2) -> s1 + s2)
                .getAsLong();

        getSize();


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
