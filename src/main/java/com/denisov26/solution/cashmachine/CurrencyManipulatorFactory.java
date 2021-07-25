package com.denisov26.solution.cashmachine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, com.javarush.task.task26.task2613.CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static com.javarush.task.task26.task2613.CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        currencyCode = currencyCode.toUpperCase();
        if (!map.containsKey(currencyCode)) {
            com.javarush.task.task26.task2613.CurrencyManipulator manipulator = new com.javarush.task.task26.task2613.CurrencyManipulator(currencyCode);
            map.put(manipulator.getCurrencyCode(), manipulator);
        }
        return map.get(currencyCode);
    }

    public static Collection<com.javarush.task.task26.task2613.CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
