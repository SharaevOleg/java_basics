import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadPage {

    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\User\\Desktop\\01\\page.html";
        URL url = new URL("https://lenta.ru/");
        InputStream stream = url.openStream();
        FileOutputStream fos = new FileOutputStream(path);

        for (; ; ) {
            int code = stream.read();
            if (code < 0) {
                break;
            }
            fos.write(code);
        }
        Document document = Jsoup.parse(new File(path), "UTF-8");
        Elements elements = document.select("img");
        for (Element element : elements) {

            String str = element.attr("src");
            int lastOf = str.lastIndexOf("/");
            String str2 = str.substring(lastOf + 1);

            try (InputStream in = new URL(element.attr("src")).openStream()) {
                Files.copy(in, Paths.get("C:\\Users\\User\\Desktop\\01\\" + str2));
            } catch (Exception e) {
                System.out.println("Icon " + str2 + " not copy!");
            }

        }
    }
}
