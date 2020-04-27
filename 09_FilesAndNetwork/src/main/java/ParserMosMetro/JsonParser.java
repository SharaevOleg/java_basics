package ParserMosMetro;

import ParserMosMetro.core.Line;
import ParserMosMetro.core.Metro;
import ParserMosMetro.core.Station;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonParser {

    private static String pathJson = "09_FilesAndNetwork/src/main/java/ParserMosMetro/files/map.json";
    private static List<List<Station>> connection = new ArrayList<>();
    private static Metro metro;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Map<String, List<String>> stations = new TreeMap<>();
    private static List<Line> lines = new LinkedList<>();

    static void parseConnections(Elements cols, String stationName) {
        List<String> connectionsLine = cols.get(3).children().eachAttr("title");
        List<String> connectionsNumber = cols.get(3).children().eachText();
        List<String> lineNumbers = cols.get(0).children().eachText();
        String lineId = lineNumbers.get(1);
        if (connectionsNumber.size() != 0) {
            List<Station> temp = new ArrayList<>();
            temp.add(new Station(lineId, stationName));
            for (int i = 0; i < connectionsNumber.size(); i++) {
                temp.add(new Station(connectionsNumber.get(i), connectionsLine.get(i)));
            }
            connection.add(temp);
        }
    }

    static void createJsonFile() throws IOException {
        metro = new Metro(lines, stations, connection);
        try (FileWriter file = new FileWriter(pathJson)) {
            file.write(GSON.toJson(metro));
        }
    }

    static void parseStation(String stationName, List<String> lineNumbers, List<String> connectionsLineName) {
        String lineId = lineNumbers.get(0);
        if (!stations.containsKey(lineId)) {
            stations.put(lineId, new ArrayList<>());
        }
        stations.get(lineId).add(stationName);

        if (connectionsLineName.size() == 2) {
            if (!stations.containsKey(lineNumbers.get(1)))
                stations.put(lineNumbers.get(1), new ArrayList<>());
            else stations.get(lineNumbers.get(1)).add(stationName);
        }
    }

    static void parseLines(String lineName, List<String> lineNumbers) {
        Line line = new Line(lineNumbers.get(0), lineName);
        if (!lines.contains(line)) lines.add(line);
    }

    static void jsonParser() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(ParserMetro.parseFile(pathJson));

        HashMap<String, List<String>> stations = (HashMap<String, List<String>>) jsonObject.get("stations");

        System.out.printf("%-9s | %-35s | %s%n", "NUM line", "NAME line", "LINE size");
        for (String lineId : stations.keySet()) {
            JSONArray stationsArray = (JSONArray) stations.get(lineId);
            for (Line line : metro.getLines()) {
                if (line.getNumber().equals(lineId)) {
                    System.out.printf("%-9s | %-35s | %s%n", lineId, line.getName(), stationsArray.size());
                }
            }
        }
    }


}
