package ParserMosMetro;

import ParserMosMetro.files.ParserMetro;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

//        StringBuilder stringBuilder = new StringBuilder();
//        List<String> list = Files.readAllLines(Paths.get("09_FilesAndNetwork/src/ParserMosMetro/files/page.html"));
//        list.forEach(f -> stringBuilder.append(f).append("\n"));
//        String list1 = stringBuilder.toString();
//        System.out.println(list1);



        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        Document document = Jsoup.parse(ParserMetro.parseFile("09_FilesAndNetwork/src/ParserMosMetro/files/page.html"));
        Element element = document.select("table").get(3);
        Elements rows = element.select("tr");
        rows.stream().skip(1).forEach((row) -> {
            Elements cols = row.select("td");
            String stationName = cols.get(1).child(0).text();
            String lineName = cols.get(0).child(1).attr("title");

//            FileWriter file = new FileWriter("09_FilesAndNetwork/src/ParserMosMetro/files/map.json") {
//                file.write(GSON.toJson(metro));
            System.out.printf("%-30s ***** %s%n", stationName, lineName);
        });

    }
}
