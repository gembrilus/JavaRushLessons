package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
//        PersonScannerAdapter adapter = new PersonScannerAdapter(new Scanner("Иванов Иван Иванович 31 12 1950\n" +
//                "Петров Петр Петрович 31 12 1957"));
//
//        System.out.println(adapter.read());
    }

    public static class PersonScannerAdapter implements PersonScanner{

        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String[] s = fileScanner.nextLine().split(" ", 4);
            Date bd = new Date();
            try {
                bd = new SimpleDateFormat("dd MM yyyy").parse(s[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Person(s[1], s[2], s[0], bd);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
