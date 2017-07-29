package com.javarush.task.task26.task2613;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Андрей on 30.06.2017.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        for(String str : map.keySet()){
            if(currencyCode.equalsIgnoreCase(str))
                return map.get(str);
        }
        map.put(currencyCode, new CurrencyManipulator(currencyCode));
        return map.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        Collection<CurrencyManipulator> result = new ArrayList<CurrencyManipulator>();

        for(CurrencyManipulator currencyManipulator : map.values()){
            result.add(currencyManipulator);
        }

        return result;
    }
}
