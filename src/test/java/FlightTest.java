import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightTest {
    @Test
    public void filterDepartureUpToCurrentTimeTrueTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusMinutes(1),LocalDateTime.now().plusHours(2))));
                boolean result=flight.filterDepartureAndArrivalUpToCurrentTime(true);
        Assertions.assertTrue(result);
        }
    @Test
    public void filterDepartureUpToCurrentTimeFalseTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().plusMinutes(1),LocalDateTime.now().plusHours(2))));
        boolean result=flight.filterDepartureAndArrivalUpToCurrentTime(true);
        Assertions.assertFalse(result);
    }
    @Test
    public void filterArrivalUpToCurrentTimeTrueTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),LocalDateTime.now().minusMinutes(1))));
        boolean result=flight.filterDepartureAndArrivalUpToCurrentTime(false);
        Assertions.assertTrue(result);
    }
    @Test
    public void filterArrivalUpToCurrentTimeFalseTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),LocalDateTime.now().plusMinutes(1))));
        boolean result=flight.filterDepartureAndArrivalUpToCurrentTime(false);
        Assertions.assertFalse(result);
    }
    @Test
    public void arrivalDateBeforeDepartureTrueTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusDays(1).minusHours(2),
                LocalDateTime.now().minusDays(1)),(new Segment(LocalDateTime.now(),LocalDateTime.now().plusHours(2)))));
        boolean result=flight.arrivalDateBeforeDeparture();
        Assertions.assertTrue(result);
    }
    @Test
    public void arrivalDateBeforeDepartureOneSegmentFalseTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),
                LocalDateTime.now())));
        boolean result=flight.arrivalDateBeforeDeparture();
        Assertions.assertFalse(result);
    }
    @Test
    public void timeOnGroundBetweenDeparturesOneSegmentFalseTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),
                LocalDateTime.now())));
        boolean result=flight.timeOnGroundBetweenDepartures(2);
        Assertions.assertFalse(result);
    }
    @Test
    public void timeOnGroundBetweenDeparturesFalseTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),
                LocalDateTime.now()),(new Segment(LocalDateTime.now().plusHours(1).plusMinutes(1),LocalDateTime.now().plusHours(4)))));
        boolean result=flight.timeOnGroundBetweenDepartures(2);
        Assertions.assertFalse(result);
    }
    @Test
    public void timeOnGroundBetweenDeparturesTrueTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),
                LocalDateTime.now()),(new Segment(LocalDateTime.now().plusHours(2).plusMinutes(1),LocalDateTime.now().plusHours(4)))));
        boolean result=flight.timeOnGroundBetweenDepartures(2);
        Assertions.assertTrue(result);
    }
    @Test
    public void timeOnGroundBetweenDeparturesFalseHourThreeTest(){
        Flight flight=new Flight(List.of(new Segment(LocalDateTime.now().minusHours(2),
                LocalDateTime.now()),(new Segment(LocalDateTime.now().plusHours(2).plusMinutes(1),LocalDateTime.now().plusHours(4)))));
        boolean result=flight.timeOnGroundBetweenDepartures(3);
        Assertions.assertFalse(result);
    }
}
