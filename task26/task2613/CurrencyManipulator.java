package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Андрей on 30.06.2017.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();        //это Map<номинал, количество>

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Map<Integer, Integer> getDenominations() {
        return denominations;
    }

    public void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination))
            denominations.put(denomination, count + denominations.get(denomination));
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount(){
        int sum = 0;
        for(Map.Entry<Integer, Integer> pair : denominations.entrySet()){
            sum += pair.getKey() * pair.getValue();
        }
        return sum;
    }

    public boolean hasMoney(){
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount){
        Map<Integer, Integer> result = new HashMap<>();

        return result;
    }
}
