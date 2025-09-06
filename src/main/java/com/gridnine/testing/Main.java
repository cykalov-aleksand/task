package com.gridnine.testing;

import java.util.List;

public class Main {
        public static void main(String[] args) {

        List<List<String>> listFilter1 = List.of(List.of("до текущего времени", "вылет", "исключить"));
        List<List<String>> listFilter2 = List.of(List.of("дата прилёта раньше даты вылета", "исключить"));
        List<List<String>> listFilter3 = List.of(List.of("время между посадкой - взлетом больше", "2", "исключить"));
        List<List<String>> reversListFilter1 = List.of(List.of("до текущего времени", "вылет", "показать"));
        List<List<String>> reversListFilter2 = List.of(List.of("дата прилёта раньше даты вылета", "показать"));
        List<List<String>> reversListFilter3 = List.of(List.of("время между посадкой - взлетом больше", "2", "показать"));
        ListFlight proba = new ListFlight(FlightBuilder.createFlights());
        try {
            System.out.println("\nИсходный тестовый список");
            if(proba.flightList()!=null) {
                for (Flight variable : proba.flightList()) {
                    System.out.println(variable);
                    }
            }else {
                throw new IllegalArgumentException("список пуст");
            }
            System.out.println("\nУдалим из вышеуказанного списка вылет рейсов до текущего момента времени");
            proba.filter(listFilter1);
            for (Flight variable : proba.filter(listFilter1)) {
                System.out.println(variable);
            }
            System.out.println("\nПоказали удаленный элемент списка");
            for (Flight variable : proba.filter(reversListFilter1)) {
                System.out.println(variable);
            }
            System.out.println("\nУдалим из исходного списка, элементы списка у которых сегменты с датой прилёта раньше даты вылета");
            for (Flight variable : proba.filter(listFilter2)) {
                System.out.println(variable);
            }
            System.out.println("\nПоказали удаленный элемент списка");
           for (Flight variable : proba.filter(reversListFilter2)) {
                System.out.println(variable);
            }
            System.out.println("\nУдалим из исходного списка, элементы в которых общее время, проведённое на земле, превышает два часа");
            for (Flight variable : proba.filter(listFilter3)) {
                System.out.println(variable);
            }
            System.out.println("\nПоказали удаленный элемент списка");
            for (Flight variable : proba.filter(reversListFilter3)) {
                System.out.println(variable);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
    }
}