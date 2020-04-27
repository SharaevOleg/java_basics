package ParserMosMetro;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String pathHtml = "09_FilesAndNetwork/src/main/java/ParserMosMetro/files/page.html";

        Document document = Jsoup.parse(ParserMetro.parseFile(pathHtml));
        Element element = document.select("table").get(3);
        Elements rows = element.select("tr");

        rows.stream().skip(1).forEach((row) -> {
            Elements cols = row.select("td");
            String stationName = cols.get(1).child(0).text(), lineName = cols.get(0).child(1).attr("title");
            List<String> lineNumbers = cols.get(0).children().eachText(),
                    connectionsLineName = cols.get(0).children().eachAttr("title"),
                    connectionsNumber = cols.get(3).children().eachText();

            JsonParser.parseStation(stationName, lineNumbers, connectionsLineName);
            JsonParser.parseLines(lineName, lineNumbers);
            if (connectionsNumber.size() != 0) JsonParser.parseConnections(cols, stationName);

//            System.out.printf("%-25s ***** %-35s ***** %s%n", stationName, lineName, connectionsNumber);
        });

        try {
            JsonParser.createJsonFile();
            JsonParser.jsonParser();
        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }
    }


}
