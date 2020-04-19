package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

//        Integer[] arr = {13, 8, 15, 5, 17, 17};
//        for(Integer i: sort(arr)){
//            System.out.printf("%d ", i);
//        }

    }

    public static Integer[] sort(Integer[] array) {
        Integer[] arr = Arrays.copyOf(array, array.length);
        Arrays.sort(arr);
        int size = arr.length;
        Integer mediana = size % 2 == 0 ? (arr[size/2 - 1] + arr[size/2])/2 : arr[size/2];
        Arrays.sort(arr, Comparator.comparingInt(i -> Math.abs(i - mediana)));
        return arr;
    }
}