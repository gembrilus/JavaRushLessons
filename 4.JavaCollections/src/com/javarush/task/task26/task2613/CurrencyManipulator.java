package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        denominations.merge(denomination, count, Integer::sum);
    }

    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> temp = new TreeMap<>(Comparator.reverseOrder());
        temp.putAll(denominations);

        TreeMap<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Integer> pair : temp.entrySet()) {
            Integer key = pair.getKey();
            Integer value = pair.getValue();

            while (expectedAmount >= key && value > 0) {
                result.merge(key, 1, Integer::sum);
                expectedAmount -= key;
                value--;
            }
            temp.put(key, value);

            if (expectedAmount == 0) break;
        }

        if (expectedAmount > 0) throw new NotEnoughMoneyException();

        denominations.clear();
        denominations.putAll(temp);
        return result;
    }

    public int getTotalAmount(){
        return denominations.entrySet().stream()
                .map(entry -> entry.getKey() * entry.getValue())
                .reduce(0, Integer::sum);
    }
}
