package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Bean that represents a flight.
 */
public class Flight {

    private final List<Segment> segments;

    public Flight(final List<Segment> segs) {
        segments = segs;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public boolean filterDepartureAndArrivalUpToCurrentTime(boolean departure) {
        LocalDateTime dateTime = LocalDateTime.now();
        if (departure) {
            return segments.get(0).getDepartureDate().isBefore(dateTime);
        } else {
            return segments.get(0).getArrivalDate().isBefore(dateTime);
        }
    }

    public boolean arrivalDateBeforeDeparture() {
        if (segments.size() >= 2) {
            for (int count = 0; count < segments.size() - 1; count++) {
                if (segments.get(count).getArrivalDate().toLocalDate().isBefore(segments.get(count + 1).getDepartureDate().toLocalDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean timeOnGroundBetweenDepartures(int hour) {
        if (segments.size() >= 2) {
            for (int count = 0; count < segments.size() - 1; count++) {
                if (segments.get(count).getArrivalDate().plusHours(hour).isBefore(segments.get(count + 1).getDepartureDate())) {
                    return true;
                }
            }
        }
        return false;
    }
}

