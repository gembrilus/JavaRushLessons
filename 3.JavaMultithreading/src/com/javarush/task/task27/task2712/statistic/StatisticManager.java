package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage=new StatisticStorage();

    private StatisticManager() {}

    public static StatisticManager getInstance()
    {
        return ourInstance;
    }

    public void register(EventDataRow data)
    {
        if (data == null) return;
        statisticStorage.put(data);
    }

    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }
    }

    public Map<Date, Double> getAdvertisementStatistic() {
        Map<Date, Double> resultMap = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow event : statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS)) {
            Date date = normalizeDate(event.getDate());
            VideoSelectedEventDataRow eventData = (VideoSelectedEventDataRow) event;
            if (resultMap.containsKey(date)) {
                resultMap.put(date, resultMap.get(date) + (0.01d * (double) eventData.getAmount()));
            } else {
                resultMap.put(date, (0.01d * (double) eventData.getAmount()));
            }
        }
        return resultMap;
    }

/*    public SortedMap<Date,Double> getAdvertisementStatistic() {
        return statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS).stream()
                .map(eventDataRow -> (VideoSelectedEventDataRow) eventDataRow)
                .collect(
                        Collectors.groupingBy(
                                event -> normalizeDate(event.getDate()),
                                () -> new TreeMap<>(Collections.reverseOrder()),
                                Collectors.summingDouble(e -> 0.1 * e.getAmount())
                        )
                );
    }*/

    public SortedMap<Date, List<CookedOrderEventDataRow>> getCookStatistic() {
        return statisticStorage.getStorage().get(EventType.COOKED_ORDER).stream()
                .map(eventDataRow -> (CookedOrderEventDataRow) eventDataRow)
                .filter(event -> event.getTime() > 0)
                .collect(Collectors.groupingBy(
                        event -> normalizeDate(event.getDate()),
                        () -> new TreeMap<>(Collections.reverseOrder()),
                        Collectors.toList()
                        )
                );
    }

    private Date normalizeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        GregorianCalendar gc = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        return gc.getTime();
    }

}

/*
public class StatisticManager {

    private StatisticStorage statisticStorage;
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
        statisticStorage = new StatisticStorage();
    }

    private static class LazyHolder {
        private static final StatisticManager INSTANCE = new StatisticManager();
    }

    public static StatisticManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public SortedMap<Date,Double> getAdvertisementStatistic() {
        return statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS).stream()
                .map(eventDataRow -> (VideoSelectedEventDataRow) eventDataRow)
                .collect(
                        Collectors.groupingBy(
                                event -> normalizeDate(event.getDate()),
                                () -> new TreeMap<>(Collections.reverseOrder()),
                                Collectors.summingDouble(e -> 0.1 * e.getAmount())
                        )
                );
    }

    public SortedMap<Date, List<CookedOrderEventDataRow>> getCookStatistic() {
        return statisticStorage.getStorage().get(EventType.COOKED_ORDER).stream()
                .map(eventDataRow -> (CookedOrderEventDataRow) eventDataRow)
                .filter(event -> event.getTime() > 0)
                .collect(Collectors.groupingBy(
                        event -> normalizeDate(event.getDate()),
                        () -> new TreeMap<>(Collections.reverseOrder()),
                        Collectors.toList()
                        )
                );
    }

    private Date normalizeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        GregorianCalendar gc = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        return gc.getTime();
    }

    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

    }
}*/
