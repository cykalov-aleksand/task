package org.example;

import java.util.ArrayList;
import java.util.List;

public record ListFlight(List<Flight> flightList) {

    public List<Flight> filter(List<List<String>> listFilter)throws IllegalArgumentException {
        List<Flight> result = new ArrayList<>();
        List<Flight> newFligthList = new ArrayList<>(flightList);
        boolean oneElement;

        for (List<String> variable : listFilter) {
            result.clear();
            switch (variable.get(0).toLowerCase()) {
                case "до текущего времени":
                    if (variable.get(1).equalsIgnoreCase("вылет")) {
                        oneElement = true;
                    } else {
                        oneElement = false;
                    }
                    if (variable.get(2).equalsIgnoreCase("исключить")) {
                        for (Flight element : newFligthList) {
                            if (!element.filterDepartureAndArrivalUpToCurrentTime(oneElement)) {
                                result.add(element);
                            }
                        }
                    } else {
                        for (Flight element : newFligthList) {
                            if (element.filterDepartureAndArrivalUpToCurrentTime(oneElement)) {
                                result.add(element);
                            }
                        }
                    }
                    break;
                case "дата прилёта раньше даты вылета":
                    if (variable.get(1).equalsIgnoreCase("исключить")) {
                        for (Flight element : newFligthList) {
                            if (!element.arrivalDateBeforeDeparture()) {
                                result.add(element);
                            }
                        }
                    } else {
                        for (Flight element : newFligthList) {
                            if (element.arrivalDateBeforeDeparture()) {
                                result.add(element);
                            }
                        }
                    }
                    break;
                case "время между посадкой- взлетом больше":
                    if (variable.get(2).equalsIgnoreCase("исключить")) {
                        for (Flight element : newFligthList) {
                            if (!element.timeOnGroundBetweenDepartures(Integer.parseInt(variable.get(1)))) {
                                result.add(element);
                            }
                        }
                    } else {
                        for (Flight element : newFligthList) {
                            if (element.timeOnGroundBetweenDepartures(Integer.parseInt(variable.get(1)))) {
                                result.add(element);
                            }
                        }
                    }
                    break;
                default:throw new IllegalArgumentException("Фильтр с указанным именем отсутствует, проверьте корректность ввода списка фильтрации");
            }
            newFligthList.clear();
            newFligthList.addAll(result);
        }
        return result;
    }
}

