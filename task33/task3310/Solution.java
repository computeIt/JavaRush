package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import static com.javarush.task.task33.task3310.Helper.printMessage;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new HashSet<>();
        for(String str : strings){
            result.add(shortener.getId(str));
        }
        return result;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new HashSet<>();
        for(Long id : keys){
            result.add(shortener.getString(id));
        }
        return result;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        printMessage(strategy.getClass().getSimpleName());

        Set<String> set = new HashSet<>();
        for(int i = 0; i < elementsNumber; i++) {
            set.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date date1 = new Date();
        Set<Long> setLongs = getIds(shortener, set);
        Date date2 = new Date();
        long tRes = date2.getTime() - date1.getTime();
        Helper.printMessage(String.valueOf(tRes));

        date1 = new Date();
        Set<String> setStrings = getStrings(shortener, setLongs);
        date2 = new Date();
        tRes = date2.getTime() - date1.getTime();
        Helper.printMessage(String.valueOf(tRes));

        if(set.equals(setStrings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args) {
        StorageStrategy strategy1 = new HashMapStorageStrategy();
        testStrategy(strategy1, 1000);

        StorageStrategy strategy2 = new OurHashMapStorageStrategy();
        testStrategy(strategy2, 1000);

        StorageStrategy strategy3 = new FileStorageStrategy();
        testStrategy(strategy3, 100);

        StorageStrategy strategy4 = new OurHashBiMapStorageStrategy();
        testStrategy(strategy4, 1000);

        StorageStrategy strategy5 = new DualHashBidiMapStorageStrategy();
        testStrategy(strategy5, 1000);
    }
}
