import com.gridnine.testing.Flight;
import com.gridnine.testing.ListFlight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ListFlightTest {
    @Test
    public void threeFilterTest() {
        List<List<String>> testString = List.of(
                List.of("до текущего времени", "вылет", "исключить"),
                List.of("дата прилёта раньше даты вылета", "исключить"),
                List.of("время между посадкой - взлетом больше", "2", "исключить"));
        ListFlight test = new ListFlight(List.of(new Flight(List.of(new Segment(LocalDateTime.now().minusHours(1),
                        LocalDateTime.now().minusHours(2)))),new Flight(List.of(new Segment(LocalDateTime.now().plusMinutes(1),
                        LocalDateTime.now().plusHours(4)), new Segment(LocalDateTime.now().plusDays(2),
                        LocalDateTime.now().plusDays(1).plusHours(2)))),new Flight(List.of(new Segment(LocalDateTime.now()
                .plusMinutes(1), LocalDateTime.now().plusDays(1).plusHours(4)), new Segment(LocalDateTime.now().plusDays(1)
                .plusHours(7), LocalDateTime.now().plusDays(1).plusHours(2))))));
        List<Flight> result = test.filter(testString);
        Assertions.assertEquals(result.size(), 0);
    }

    @Test
    public void twoFilterTest() {
        List<List<String>> testString = List.of(
                List.of("до текущего времени", "вылет", "исключить"),
                List.of("дата прилёта раньше даты вылета", "исключить"));
        ListFlight test = new ListFlight(List.of(new Flight(List.of(new Segment(LocalDateTime.now().minusHours(1),
                        LocalDateTime.now().minusHours(2)))),new Flight(List.of(new Segment(LocalDateTime.now()
                        .plusMinutes(1), LocalDateTime.now().plusHours(4)), new Segment(LocalDateTime.now().plusDays(2),
                        LocalDateTime.now().plusDays(1).plusHours(2)))),new Flight(List.of(new Segment(LocalDateTime.now()
                .plusMinutes(1), LocalDateTime.now().plusDays(1).plusHours(4)), new Segment(LocalDateTime.now().plusDays(1)
                .plusHours(7), LocalDateTime.now().plusDays(1).plusHours(2))))));
        List<Flight> result = test.filter(testString);
        Assertions.assertEquals(result.size(), 1);
    }

    @Test
    public void oneFilterTest() {
        List<List<String>> testString = List.of(
                List.of("до текущего времени", "вылет", "исключить"));
        ListFlight test = new ListFlight(List.of(new Flight(List.of(new Segment(LocalDateTime.now().minusHours(1),
                LocalDateTime.now().minusHours(2)))), new Flight(List.of(new Segment(LocalDateTime.now().plusMinutes(1),
                LocalDateTime.now().plusHours(4)), new Segment(LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(1).plusHours(2)))), new Flight(List.of(new Segment(LocalDateTime.now()
                .plusMinutes(1), LocalDateTime.now().plusDays(1).plusHours(4)), new Segment(LocalDateTime.now().plusDays(1).
                plusHours(7), LocalDateTime.now().plusDays(1).plusHours(2))))));
        List<Flight> result = test.filter(testString);
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void oneFilterErrorTest() {
        List<List<String>> testString = List.of(
                List.of("текущего времени", "вылет", "исключить"));
        ListFlight test = new ListFlight(List.of(new Flight(List.of(new Segment(LocalDateTime.now().minusHours(1),
                LocalDateTime.now().minusHours(2)))), new Flight(List.of(new Segment(LocalDateTime.now()
                .plusMinutes(1), LocalDateTime.now().plusHours(4)), new Segment(LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(1).plusHours(2)))), new Flight(List.of(new Segment(LocalDateTime.now()
                .plusMinutes(1), LocalDateTime.now().plusDays(1).plusHours(4)), new Segment(LocalDateTime.now().plusDays(1)
                .plusHours(7), LocalDateTime.now().plusDays(1).plusHours(2))))));
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> test
                .filter(testString).contains("Фильтр с указанным именем отсутствует, проверьте корректность ввода списка фильтрации"));
        Assertions.assertTrue(exception.getMessage().contains("Фильтр с указанным именем отсутствует, проверьте корректность ввода списка фильтрации"));
    }
}
