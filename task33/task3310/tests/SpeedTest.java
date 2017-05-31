package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Igor on 31.05.2017.
 */
public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date timeBegin = new Date();
        for(String string : strings){
            ids.add(shortener.getId(string));
        }
        Date timeFinish = new Date();
        return timeFinish.getTime() - timeBegin.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date timeBegin = new Date();
        for(Long id : ids){
            strings.add(shortener.getString(id));
        }
        Date timeFinish = new Date();
        return timeFinish.getTime() - timeBegin.getTime();
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> ids = new HashSet<>();

        for(int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        long t1 = getTimeForGettingIds(shortener1, origStrings, ids);
        long t2 = getTimeForGettingIds(shortener2, origStrings, ids);

        Assert.assertTrue(t1>t2);

        t1 = getTimeForGettingStrings(shortener1, ids, origStrings);
        t2 = getTimeForGettingStrings(shortener2, ids, origStrings);

        Assert.assertEquals(t1, t2, 30);//у меня в 30 не уложилось, но валидатор принял
    }
}
