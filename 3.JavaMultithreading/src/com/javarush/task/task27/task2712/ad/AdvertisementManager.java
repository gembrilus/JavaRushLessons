package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.List;

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

    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> videoList=new ArrayList<>(storage.list());
        if (videoList.isEmpty())
            throw new NoVideoAvailableException();

        videoList.sort((o1, o2) -> {
            int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
            if (result != 0)
                return -result;

            long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
            long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

            return Long.compare(oneSecondCost1, oneSecondCost2);
        });

        long sumAmount=0;
        int sumDuration=0;

        List<Advertisement> list=new ArrayList<>();

        int timeLeft = timeSeconds;
        for (Advertisement advertisement : videoList) {
            if (timeLeft < advertisement.getDuration() || advertisement.getHits()<=0) {
                continue;
            }
            list.add(advertisement);
            sumAmount+=advertisement.getAmountPerOneDisplaying();
            sumDuration+=advertisement.getDuration();
            timeLeft -= advertisement.getDuration();
        }

        if (timeLeft == timeSeconds) {
            throw new NoVideoAvailableException();
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(list, sumAmount, sumDuration));

        for (Advertisement advertisement : list) {
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());
            advertisement.revalidate();
        }
    }

    /*public void processVideos() {
        List<Advertisement> videos = storage.list().stream()
                .filter(adv -> adv.getDuration() <= timeSeconds && adv.getAmountPerOneDisplaying() != 0 && adv.getHits() != 0)
                .sorted(Comparator
                        .comparingLong(Advertisement::getAmountPerOneDisplaying)
                        .thenComparingLong(Advertisement::getDuration)
                        .thenComparing((adv1, adv2) -> adv2.getHits() - adv1.getHits())
                        .reversed()
                )
                .collect(Collectors.toList());

        if (videos.isEmpty()){
            throw new NoVideoAvailableException();
        }

        select(videos);

        if (bestVideoSet.isEmpty()) {
            throw new NoVideoAvailableException();
        }

        registerEvent(bestVideoSet);

        bestVideoSet.forEach(adv -> {
            adv.revalidate();
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
    }*/
}
