package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementManager {

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private StatisticManager statisticManager = StatisticManager.getInstance();
    private int timeSeconds;

    private List<Advertisement> bestVideoSet;
    private int bestDuration;
    private long bestAmount;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> videos = storage.list().stream()
                .filter(adv -> adv.getDuration() <= timeSeconds && adv.getAmountPerOneDisplaying() != 0)
                .sorted(Comparator
                        .comparingLong(Advertisement::getAmountPerOneDisplaying)
                        .thenComparingLong(Advertisement::getDuration)
                        .thenComparing((adv1, adv2) -> adv2.getHits() - adv1.getHits())
                        .reversed()
                )
                .collect(Collectors.toList());

        select(videos);

        if (bestVideoSet.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        registerEvent(bestVideoSet);

        bestVideoSet.forEach(adv -> {
            long amount = adv.getAmountPerOneDisplaying();
            ConsoleHelper.writeMessage(adv.getName()
                    + " is displaying... "
                    + amount + ", "
                    + amount * 1000 / adv.getDuration());
        });
    }

    private void select(List<Advertisement> videos) {
        int size = videos.size();
        if (size > 0) {
            checkVideoSet(videos);
        }
        for (int i= 0; i < size; i++){
            List<Advertisement> tmp = new ArrayList<>(videos);
            select(videos.subList(1, size));
        }
    }

    private void checkVideoSet(List<Advertisement> advertisements) {
        int duration = totalDuration(advertisements);
        long amount = totalAmount(advertisements);

        if (bestVideoSet == null) {
            if (duration <= timeSeconds) {
                bestVideoSet = advertisements;
                bestAmount = amount;
                bestDuration = duration;
            }
        } else {
            if (duration <= timeSeconds && amount >= bestAmount && duration > bestDuration) {
                bestVideoSet = advertisements;
                bestAmount = amount;
                bestDuration = duration;
            }
        }
    }

    private long totalAmount(List<Advertisement> list) {
        return list.stream()
                .mapToLong(Advertisement::getAmountPerOneDisplaying)
                .sum();
    }

    private int totalDuration(List<Advertisement> list) {
        return list.stream()
                .mapToInt(Advertisement::getDuration)
                .sum();
    }

    private void registerEvent(List<Advertisement> list) {
        statisticManager.register(
                new VideoSelectedEventDataRow(
                        list,
                        totalAmount(list),
                        totalDuration(list)
                )
        );
    }
}
