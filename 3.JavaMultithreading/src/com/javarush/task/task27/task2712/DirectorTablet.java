package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DirectorTablet {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        SortedMap<Date, Double> map = StatisticManager.getInstance().getAdvertisementStatistic();
        double totalSum = 0.0;

        for (Map.Entry<Date, Double> pair : map.entrySet()) {
            double sum = pair.getValue();
            if (Double.compare(sum, 0.0) > 0) {
                totalSum += sum;
                ConsoleHelper.writeMessage(
                        String.format(
                                Locale.ENGLISH,
                                "%s - %.2f",
                                sdf.format(pair.getKey()),
                                sum
                        )
                );
            }
        }

        ConsoleHelper.writeMessage(
                String.format(
                        Locale.ENGLISH,
                        "Total - %.2f%n",
                        totalSum
                )
        );
    }

    public void printCookWorkloading() {
        StatisticManager.getInstance().getCookStatistic().forEach((date, events) -> {
                    ConsoleHelper.writeMessage(String.format("%s", sdf.format(date)));
                    events.stream()
                            .collect(Collectors.groupingBy(
                                    CookedOrderEventDataRow::getCookName,
                                    TreeMap::new,
                                    Collectors.summingInt(EventDataRow::getTime)
                            ))
                            .forEach((name, time) ->
                                    ConsoleHelper.writeMessage(
                                            String.format(
                                                    Locale.ENGLISH,
                                                    "%s - %d min",
                                                    name,
                                                    (int) Math.ceil(time)
                                            )
                                    )
                            );
                    ConsoleHelper.writeMessage("");
                }
        );
    }

    public void printActiveVideoSet() {

    }

    public void printArchivedVideoSet() {

    }

}
