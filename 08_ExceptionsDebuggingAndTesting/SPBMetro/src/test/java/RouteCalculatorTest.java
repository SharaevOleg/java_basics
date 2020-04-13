import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {
    RouteCalculator routeCalculator;
    StationIndex stationIndex;
    List<Station> route, transferNone, transfersTwo, transfersThree;
    Station ribatskoe, proletarskaya, lomonosovskaya, obuhovo;

    @Override
    protected void setUp() throws Exception {
        route = new ArrayList<>();
        stationIndex = new StationIndex();
        routeCalculator = new RouteCalculator(stationIndex);

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        route.add(new Station("Петровская", line1));
        route.add(new Station("Арбузная", line1));
        route.add(new Station("Морковная", line2));
        route.add(new Station("Яблочная", line2));

        ribatskoe = new Station("Рыбацкое", line1);
        proletarskaya = new Station("Пролетарская", line1);
        lomonosovskaya = new Station("Ломоносовская", line2);
        obuhovo = new Station("Обухово", line3);

        Station elizarovskaya = new Station("Елизаровская", line1);
        Station nevskogo = new Station("Невского", line2);
        Station vostaniya = new Station("Восстания", line2);
        Station moyakovskaya = new Station("Маяковская", line3);
        Station gostinyiDvor = new Station("Гостиный двор", line3);

        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);
        Stream.of(ribatskoe, elizarovskaya, proletarskaya, lomonosovskaya, nevskogo, vostaniya, obuhovo, moyakovskaya,
                gostinyiDvor).peek(s -> s.getLine().addStation(s)).forEach(stationIndex::addStation);
        stationIndex.addConnection(Stream.of(elizarovskaya, nevskogo).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(vostaniya, gostinyiDvor).collect(Collectors.toList()));

        transferNone = Stream.of(ribatskoe, elizarovskaya, proletarskaya).collect(Collectors.toList());
        transfersTwo = Stream.of(ribatskoe, elizarovskaya, nevskogo, lomonosovskaya).collect(Collectors.toList());
        transfersThree = Stream.of(ribatskoe, elizarovskaya, nevskogo, vostaniya, gostinyiDvor, moyakovskaya, obuhovo).collect(Collectors.toList());
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute() {
        List<Station> actualNoTransfer = routeCalculator.getShortestRoute(ribatskoe, proletarskaya);
        List<Station> expectedNoTransfer = transferNone;

        assertEquals(actualNoTransfer, expectedNoTransfer);
    }


    public void testGetShortestRouteTransfersTwo() {
        List<Station> actualTwoTransfer = routeCalculator.getShortestRoute(ribatskoe, lomonosovskaya);
        List<Station> expectedTwoTransfers = transfersTwo;

        assertEquals(actualTwoTransfer, expectedTwoTransfers);
    }

    public void testGetShortestRouteTransfersThree() {
        List<Station> actualThreeTransfers = routeCalculator.getShortestRoute(ribatskoe, obuhovo);
        List<Station> expectedThreeTransfers = transfersThree;

        assertEquals(actualThreeTransfers, expectedThreeTransfers);
    }
}
