package com.javarush.task.task39.task3913;

import java.nio.file.Paths;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("/home/maksim/work/IntelliJIDEAProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getIPsForUser("Pupkin", null, null));
        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getIPsForStatus(Status.OK, null, null));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get ip for date = \"30.01.2014 12:56:22\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get ip for event = \"WRITE_MESSAGE\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get date for event = \"WRITE_MESSAGE\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}