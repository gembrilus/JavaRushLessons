package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

//    private static void rules() {
//        System.out.println(
//                "Допустимые значения:\n" +
//                        "-c \"name\" \"sex\" \"date\" \"name\" \"sex\" \"date\"... - добавить человека\n" +
//                        "-u \"id\" \"name\" \"sex\" \"date\" \"name\" \"sex\" \"date\"... - изменить человека по индексу id\n" +
//                        "-d \"id\" \"id2\" ... - удалить человека по индексу id\n" +
//                        "-i \"id\" \"id2\" ... - вывести информаци о человеке по индексу id");
//    }
//
//    private static void checkInput() {
//        System.out.println(
//                "Проверьте правильность ввода:\n" +
//                        "id - это число\n" +
//                        "name - должно состоять из букв и может содержать пробел\n" +
//                        "sex - принимает два значения: ж, м\n" +
//                        "date - в формате DD/MM/YYYY");
//    }
//
//
//    private static int create(String name, Sex sex, Date date){
//        Person p;
//        if (sex == Sex.FEMALE) p = Person.createFemale(name, date);
//        else p = Person.createMale(name, date);
//        allPeople.add(p);
//        return allPeople.indexOf(p);
//    }
//
//    private static void update(int id, String name, Sex sex, Date date){
//        Person p = allPeople.get(id);
//        p.setName(name);
//        p.setSex(sex);
//        p.setBirthDate(date);
//    }
//
//    private static void delete(int id){
//        Person p = allPeople.get(id);
//        p.setBirthDate(null);
//        p.setName(null);
//        p.setSex(null);
//    }
//
//    private static void printInfo(int id){
//        Person p = allPeople.get(id);
//        String d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(p.getBirthDate());
//        String sex = p.getSex()==Sex.FEMALE ? "ж" : "м";
//        System.out.println(p.getName() + " " + sex + " " + d);
//    }
//
//    private static String[][] unpackArgs(String[] a, int startindex, int count){
//        String[][] arr = new String[(a.length-1)/count][count];
//        for (int k = 0, i = startindex; i < a.length; i++, k++) {
//            if (k == count) k = 0;
//            arr[(i - startindex) / count][k] = a[i];
//        }
//        return arr;
//    }
//
//
//    private static String check(String s, int key){
//        switch (key) {
//            case 1:
//                if (s.matches("-[cudi]"))
//                    return s;
//                else {
//                    rules();
//                    return null;
//                }
//            case 2:
//                    if (s.matches("\\p{Digit}*")) return s;
//                    else {
//                        checkInput();
//                        return null;
//                    }
//            case 3:
//                if (s.matches("\\w+")) return s;
//                else {
//                    checkInput();
//                    return null;
//                }
//            case 4:
//                if (s.matches("[жм]")) return s;
//                else {
//                    checkInput();
//                    return null;
//                }
//            case 5:
//                    if(s.matches("\\d{2}/\\d{2}/\\d{4}")) return s;
//                else { checkInput(); return null; }
//            default:
//                rules();
//                return null;
//        }
//    }


    public static void main(String[] args) {
//        if(args.length <= 1) return;
//        String key = Objects.requireNonNull(check(args[0], 1));
//        int id = 0;
//        String name = null;
//        Sex sex = null;
//        Date bd = null;
//
//        switch (key) {
//            case "-c":
//                String[][] arr = unpackArgs(args, 1, 3);
//                for(String[] s: arr){
//                    name = Objects.requireNonNull(check(s[0], 3));
//                    sex = Objects.requireNonNull(check(s[1], 4)).equals("ж") ? Sex.FEMALE : Sex.MALE;
//                    try {
//                        bd = new SimpleDateFormat("dd/MM/yyyy")
//                                .parse(Objects.requireNonNull(check(s[2], 5)));
//                    } catch (ParseException e) { }
//                    synchronized (allPeople) {
//                        System.out.println(create(name, sex, bd));
//                    }
//                }
//                break;
//            case "-u":
//                String[][] arr2 = unpackArgs(args, 1, 4);
//                for(String[] s: arr2){
//                    id = Integer.valueOf(Objects.requireNonNull(check(s[0], 2)));
//                    name = Objects.requireNonNull(check(s[1], 3));
//                    sex = Objects.requireNonNull(check(s[2], 4)).equals("ж") ? Sex.FEMALE : Sex.MALE;
//                    try {
//                        bd = new SimpleDateFormat("dd/MM/yyyy")
//                                .parse(Objects.requireNonNull(check(s[3], 5)));
//                    } catch (ParseException e) { e.printStackTrace(); }
//                    synchronized (allPeople) {
//                        update(id, name, sex, bd);
//                    }
//                }
//                break;
//            case "-d":
//                for(int i = 1; i < args.length; i++) {
//                    id = Integer.valueOf(Objects.requireNonNull(check(args[i], 2)));
//                    synchronized (allPeople){
//                        delete(id);
//                    }
//                }
//                break;
//            case "-i":
//                for(int i = 1; i < args.length; i++){
//                    id = Integer.valueOf(Objects.requireNonNull(check(args[i], 2)));
//                    synchronized (allPeople){
//                        printInfo(id);
//                    }
//                }
//                break;
//        }

            switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                for(int i = 1; i < args.length; i+=3){
                    String name = args[i];
                    Sex sex = args[i+1].equals("ж") ? Sex.FEMALE : Sex.MALE;
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(args[i+2]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Person p;
                    if(sex == Sex.FEMALE) p = Person.createFemale(name, date);
                    else p = Person.createMale(name, date);
                    allPeople.add(p);
                        System.out.println(allPeople.indexOf(p));
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                for(int i = 1; i < args.length; i+=4){
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy")
                                .parse(args[i+3]);
                    } catch (ParseException e) { e.printStackTrace(); }

                        Person p = allPeople.get(Integer.valueOf(args[i]));
                        p.setName(args[i+1]);
                        p.setSex(args[i+2].equals("ж") ? Sex.FEMALE : Sex.MALE);
                        p.setBirthDate(date);
                    }
                }
                break;
            case "-d":
                synchronized (allPeople){
                for(int i = 1; i < args.length; i++) {

                        Person p = allPeople.get(Integer.valueOf(args[i]));
                        p.setBirthDate(null);
                        p.setName(null);
                        p.setSex(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople){
                for(int i = 1; i < args.length; i++){
                        Person p = allPeople.get(Integer.valueOf(args[i]));
                        String d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(p.getBirthDate());
                        String sex = p.getSex()==Sex.FEMALE ? "ж" : "м";
                        System.out.println(p.getName() + " " + sex + " " + d);
                    }
                }
                break;
        }


    }
}
