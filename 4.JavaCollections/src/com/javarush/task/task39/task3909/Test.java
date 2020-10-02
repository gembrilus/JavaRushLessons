package com.javarush.task.task39.task3909;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void test(){
        Assert.assertTrue(Solution.isOneEditAway("Флаг","Флаг"));
        Assert.assertTrue(Solution.isOneEditAway(" ","  "));
        Assert.assertTrue(Solution.isOneEditAway("max","max "));
        Assert.assertFalse(Solution.isOneEditAway("max","max  "));
        Assert.assertTrue(Solution.isOneEditAway("",""));
        Assert.assertTrue(Solution.isOneEditAway("1",""));
        Assert.assertTrue(Solution.isOneEditAway("","A"));
        Assert.assertTrue(Solution.isOneEditAway("B","A"));
        Assert.assertTrue(Solution.isOneEditAway("BBB","ABB"));
        Assert.assertTrue(Solution.isOneEditAway("ABB","BBB"));
        Assert.assertTrue(Solution.isOneEditAway("AAB","BAB"));

        Assert.assertFalse(Solution.isOneEditAway("BBB","AbB"));
        Assert.assertFalse(Solution.isOneEditAway("AAB","BaB"));

        Assert.assertTrue(Solution.isOneEditAway("Фла","Флаг"));
        Assert.assertTrue(Solution.isOneEditAway("Флаг","Фла"));

        Assert.assertTrue(Solution.isOneEditAway("лаг","Флаг"));
        Assert.assertTrue(Solution.isOneEditAway("Флаг","лаг"));

        Assert.assertTrue(Solution.isOneEditAway("Флваг","Флаг"));
        Assert.assertTrue(Solution.isOneEditAway("Флаг","Флваг"));

        Assert.assertTrue(Solution.isOneEditAway("Флаг","флаг"));
        Assert.assertTrue(Solution.isOneEditAway("флаг","Флаг"));

        Assert.assertTrue(Solution.isOneEditAway("01","91"));
        Assert.assertTrue(Solution.isOneEditAway("01","091"));
        Assert.assertTrue(Solution.isOneEditAway("01","1"));
        Assert.assertTrue(Solution.isOneEditAway("1","01"));
        Assert.assertTrue(Solution.isOneEditAway("12","1"));
        Assert.assertTrue(Solution.isOneEditAway("1","12"));
        Assert.assertTrue(Solution.isOneEditAway("091","91"));
        Assert.assertTrue(Solution.isOneEditAway("123", "1253"));
        Assert.assertTrue(Solution.isOneEditAway("123", "1023"));
        Assert.assertTrue(Solution.isOneEditAway("22266", "2266"));
        Assert.assertTrue(Solution.isOneEditAway("sldkfjsdlfjsdlkfjsldkfj!", "sldkfjsdlfjsdlkfjsldkfj"));

//false
        Assert.assertFalse(Solution.isOneEditAway("Флаг","Фиг"));
        Assert.assertFalse(Solution.isOneEditAway("Фиг","Флаг"));

        Assert.assertFalse(Solution.isOneEditAway("Флаг","Фиговый"));
        Assert.assertFalse(Solution.isOneEditAway("Фиговый","Флаг"));

        Assert.assertFalse(Solution.isOneEditAway("am", "mak"));
        Assert.assertFalse(Solution.isOneEditAway("123", "1453"));
        Assert.assertFalse(Solution.isOneEditAway("2", "33"));
        Assert.assertFalse(Solution.isOneEditAway("01", "102"));
        Assert.assertFalse(Solution.isOneEditAway("2", "44"));
        Assert.assertFalse(Solution.isOneEditAway("1234", "12367"));
    }

}
