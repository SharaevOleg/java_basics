package ParserMosMetro.core;

public class Station {
    private String line, name;

    public Station(String line, String name) {
        this.line = line;
        this.name = name;
    }

    public String getLine() {
        return line;
    }
    public String getName() {
        return name;
    }
}