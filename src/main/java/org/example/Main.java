package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<List<String>> listFilter1 = List.of(List.of("до текущего времени", "вылет", "исключить"));
        List<List<String>> listFilter2 = List.of(List.of("дата прилёта раньше даты вылета", "исключить"));
        List<List<String>> listFilter3 = List.of(List.of("время между посадкой- взлетом больше", "2", "исключить"));
        ListFlight proba = new ListFlight(FlightBuilder.createFlights());
        try {
            for (Flight variable : proba.flightList()) {
                System.out.println(variable);
            }
            System.out.println();
            for (Flight variable : proba.filter(listFilter1)) {
                System.out.println(variable);
            }
            System.out.println();
            for (Flight variable : proba.filter(listFilter2)) {
                System.out.println(variable);
            }
            System.out.println();
            for (Flight variable : proba.filter(listFilter3)) {
                System.out.println(variable);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }
}