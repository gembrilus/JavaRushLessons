package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long totalTime = 0;

        for (String s: strings){
            long startTime = new Date().getTime();
            Long id = shortener.getId(s);
            long endTime = new Date().getTime();
            ids.add(id);
            totalTime += (endTime - startTime);
        }
        return totalTime;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long totalTime = 0;

        for (Long id: ids){
            long startTime = new Date().getTime();
            String s = shortener.getString(id);
            long endTime = new Date().getTime();
            strings.add(s);
            totalTime += (endTime - startTime);
        }
        return totalTime;
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = Stream.generate(Helper::generateRandomString)
                .limit(10000)
                .collect(Collectors.toSet());

        Set<Long> ids = new HashSet<>();

        long t1 = getTimeToGetIds(shortener1, origStrings, ids);
        long t2 = getTimeToGetIds(shortener2, origStrings, ids);

        Assert.assertThat(t1, Matchers.greaterThan(t2));

        t1 = getTimeToGetStrings(shortener1, ids, origStrings);
        t2 = getTimeToGetStrings(shortener2, ids, origStrings);
        
        Assert.assertEquals(t1, t2, 30);
    }

}
