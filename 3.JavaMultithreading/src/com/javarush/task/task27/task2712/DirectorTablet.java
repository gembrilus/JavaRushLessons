package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DirectorTablet {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        double totalSum = 0.0;
        for (Map.Entry<Date, Double> pair : StatisticManager.getInstance().getAdvertisementStatistic().entrySet()) {
            double sum = pair.getValue();
                ConsoleHelper.writeMessage(
                        String.format(Locale.ENGLISH, "%s - %.2f", sdf.format(pair.getKey()), sum)
                );
                totalSum += sum;
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalSum));
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
    public void printActiveVideoSet()
    {

    }
    public void printArchivedVideoSet()
    {

    }
}
