package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private AdvertisementStorage advertisementStorage= AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static final class LazyHolder {
        private static final StatisticAdvertisementManager INSTANCE = new StatisticAdvertisementManager();
    }

    private StatisticAdvertisementManager() {}

    public List<Advertisement> getActiveVideos() {
        return advertisementStorage.list().stream()
                .filter(adv -> adv.getHits() > 0)
                .collect(Collectors.toList());
    }

    public List<Advertisement> getArchivedVideos() {
        return advertisementStorage.list().stream()
                .filter(adv -> adv.getHits() <= 0)
                .collect(Collectors.toList());
    }
}
