import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;

public class Main {

    public static void main(String[] args) {


/*
Используя библиотеку airport.jar,
распечатать время вылета и модели самолётов,
вылетающие в ближайшие 2 часа */


//        calendar.setTime(emplor.getWorkStart());
//        return calendar.get(Calendar.YEAR) == 2017;

        Airport airport = Airport.getInstance();
        List<Terminal> terminals = airport.getTerminals();
        List<Flight> flights = null;
        int i = terminals.size();
        int x = i;
        while (x > 0) {
            flights = terminals.get(x - 1).getFlights();
            x--;
        }

        flights.stream().filter(f -> {
            Calendar calendar = Calendar.getInstance();
            return f.getDate().after(calendar.getTime());
        })

                .filter(f -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.HOUR_OF_DAY, +2);
                    return f.getDate().before(calendar.getTime());
                })
                .sorted(Comparator.comparing(Flight::getDate))
                .forEach(System.out::println);
    }
}
