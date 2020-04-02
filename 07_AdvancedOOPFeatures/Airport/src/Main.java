import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Airport airport = Airport.getInstance();
//        List<Terminal> terminals = airport.getTerminals();
//        List<Flight> flights = null;
//        int i = terminals.size();
//        int x = i;
//        while (x > 0) {
//            flights = terminals.get(x - 1).getFlights();
//            x--;
//        }

        List<Flight> flights = new ArrayList<>();
        airport.getTerminals().forEach(t -> flights.addAll(t.getFlights()));

        flights.stream().filter(f -> {
            Calendar calendar = Calendar.getInstance();
            return f.getDate().after(calendar.getTime());
        })

                .filter(f -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR_OF_DAY, +2);
                    return f.getDate().before(calendar.getTime());
                })
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .sorted(Comparator.comparing(Flight::getDate))
                .forEach(System.out::println);
    }
}
