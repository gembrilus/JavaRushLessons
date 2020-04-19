package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

//    private static void rules() {
//        System.out.println(
//                        "Допустимые значения:\n" +
//                        "-c \"name\" \"sex\" \"date\"- добавить человека\n" +
//                        "-u \"id\" \"name\" \"sex\" \"date\"- изменить человека по индексу id\n" +
//                        "-d \"id\" - удалить человека по индексу id\n" +
//                        "-i \"id\" - вывести информаци о человеке по индексу id");
//    }
//
//    private static void checkInput() {
//        System.out.println(
//                        "Проверьте правильность ввода:\n" +
//                        "id - это число\n" +
//                        "name - должно состоять из букв и может содержать пробел\n" +
//                        "sex - принимает два значения: ж, м\n" +
//                        "date - в формате DD/MM/YYYY");
//    }
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

    public static void main(String[] args) {

//        int len = args.length;
//        String key = "", name = "";
//        int id = 0;
//        Sex sex = null;
//        Date bd = null;
//
//        switch (len) {
//            case 4:
//            case 5:
//
//                if (args[len - 3].matches("\\w+")) name = args[len - 3];
//                else {
//                    checkInput();
//                    return;
//                }
//
//                if (args[len - 2].equalsIgnoreCase("ж")) sex = Sex.FEMALE;
//                else if (args[len - 2].equalsIgnoreCase("м")) sex = Sex.MALE;
//                else {
//                    checkInput();
//                    return;
//                }
//
//                try {
//                    if(args[len-1].matches("\\d{2}/\\d{2}/\\d{4}"))
//                        bd = new SimpleDateFormat("dd/MM/yyyy").parse(args[len - 1]);
//                } catch (ParseException e) { checkInput(); return; }
//
//            case 2:
//
//                if (args[0].matches("-[cudi]")) key = args[0];
//                else {
//                    rules();
//                    return;
//                }
//                if(len != 4) {
//                    if (args[1].matches("\\p{Digit}*")) id = Integer.valueOf(args[1]);
//                    else {
//                        checkInput();
//                        return;
//                    }
//                }
//                    break;
//            default:
//                rules();
//                return;
//        }
//
//        switch (key) {
//            case "-c": System.out.println(create(name, sex, bd)); break;
//            case "-u": update(id, name, sex, bd); break;
//            case "-d": delete(id); break;
//            case "-i": printInfo(id); break;
//        }

        switch (args[0]) {
            case "-c":
                Person p;
                Date date = null;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(args[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (args[2].equals("ж")) p = Person.createFemale(args[1], date);
                else p = Person.createMale(args[1], date);
                allPeople.add(p);
                System.out.println(allPeople.indexOf(p));
                break;
            case "-u":
                Person p2 = allPeople.get(Integer.valueOf(args[1]));
                Date date1 = null;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(args[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                p2.setName(args[2]);
                p2.setSex(args[3].equals("ж") ? Sex.FEMALE : Sex.MALE);
                p2.setBirthDate(date1);
                break;
            case "-d":
                Person p3 = allPeople.get(Integer.valueOf(args[1]));
                p3.setBirthDate(null);
                p3.setName(null);
                p3.setSex(null);
                break;
            case "-i":
                Person p4 = allPeople.get(Integer.valueOf(args[1]));
                String d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(p4.getBirthDate());
                String sex = p4.getSex()==Sex.FEMALE ? "ж" : "м";
                System.out.println(p4.getName() + " " + sex + " " + d);
                break;
        }
    }
}
