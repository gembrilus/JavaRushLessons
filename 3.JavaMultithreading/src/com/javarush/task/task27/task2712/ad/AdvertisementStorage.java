package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {

    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        add(new Advertisement(someContent, "Fourth Video", 1500, 10, 30 * 60));
        add(new Advertisement(someContent, "Fifth Video", 4000, 10, 2 * 60));
        add(new Advertisement(someContent, "Seventh Video", 2500, 10, 20 * 60));
        add(new Advertisement(someContent, "Eight Video", 3000, 10, 36 * 60));
        add(new Advertisement(someContent, "Ninth Video", 500, 10, 3 * 60));
        add(new Advertisement(someContent, "Ten Video", 4500, 10, 18 * 60));
    }

    private static class LazyHolder{
        private static final AdvertisementStorage INSTANCE = new AdvertisementStorage();
    }

    public static AdvertisementStorage getInstance() {
        return LazyHolder.INSTANCE;
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
