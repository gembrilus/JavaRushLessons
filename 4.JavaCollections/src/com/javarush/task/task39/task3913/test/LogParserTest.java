package com.javarush.task.task39.task3913.test;

import com.javarush.task.task39.task3913.LogParser;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LogParserTest {

    private LogParser logParser = new LogParser(
            Paths.get("/home/maksim/work/IntelliJIDEAProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/test/logs")
        );
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Test
    public void getNumberOfUniqueIPs() throws ParseException {

        assertEquals(9, logParser.getNumberOfUniqueIPs(null, null));

        Date after = sdf.parse("30.08.2012 16:08:13");
        Date before = sdf.parse("12.12.2013 21:56:30");
        int actual = logParser.getNumberOfUniqueIPs(after, before);

        assertEquals(5, actual);
        assertEquals(5, logParser.getNumberOfUniqueIPs(null, before));
        assertEquals(9, logParser.getNumberOfUniqueIPs(after, null));

    }

    @Test
    public void executeForIPAndEvent() {
        Set<Object> actual;
        Set<Object> expected = new HashSet<>();

        actual = logParser.execute("get ip for event = \"WRITE_MESSAGE\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        expected.add("127.0.0.1");
        expected.add("146.34.15.5");
        expected.add("122.122.1.1");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for event = \"DOWNLOAD_PLUGIN\" and date between \"11.12.2012 0:00:00\" and \"11.12.2012 23:59:59\"");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for event = \"DOWNLOAD_PLUGIN\" and date between \"11.12.2021 0:00:00\" and \"03.01.2030 23:59:59\"");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for event = \"SOLVE_TASK\" and date between \"11.12.2021 0:00:00\" and \"3.1.2022 00:00:00\"");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for event = \"SOLVE_TASK\" and date between \"1.1.2021 0:00:00\" and \"01.01.2030 00:00:00\"");
        expected.add("12.12.12.12");
        expected.add("120.120.120.122");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for event = \"SOLVE_TASK\"");
        expected.add("12.12.12.12");
        expected.add("120.120.120.122");
        expected.add("192.168.100.2");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for event = \"WRITE_MESSAGE\"");
        expected.add("01.0.0.0");
        expected.add("127.0.0.1");
        expected.add("146.34.15.5");
        expected.add("122.122.1.1");

        doAssert(expected, actual);
    }

    @Test
    public void executeForIPAndStatus(){

        Set<Object> actual;
        Set<Object> expected = new HashSet<>();

        actual = logParser.execute("get ip for status = \"OK\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        expected.add("146.34.15.5");
        expected.add("122.122.1.1");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for status = \"OK\"");
        expected.add("127.0.0.1");
        expected.add("127.0.0.2");
        expected.add("120.120.120.122");
        expected.add("01.0.0.0");
        expected.add("120.120.120.123");
        expected.add("192.168.100.2");
        expected.add("146.34.15.5");
        expected.add("122.122.1.1");

        doAssert(expected, actual);

        actual = logParser.execute("get ip for status = \"OK\" and date between \"11.12.2021 0:00:00\" and \"03.01.2030 23:59:59\"");
        expected.add("120.120.120.122");
        expected.add("01.0.0.0");

        doAssert(expected, actual);
    }

    @Test
    public void executeForDateAndEvent() throws ParseException{
        Set<Object> actual;
        Set<Object> expected = new HashSet<>();

        actual = logParser.execute("get date for event = \"WRITE_MESSAGE\"");
        expected.add(sdf.parse("29.2.2029 5:04:50"));
        expected.add(sdf.parse("14.11.2015 07:08:01"));
        expected.add(sdf.parse("11.12.2013 10:11:12"));
        expected.add(sdf.parse("12.12.2013 21:56:30"));
        expected.add(sdf.parse("11.12.2013 0:00:00"));

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"SOLVE_TASK\"");
        expected.add(sdf.parse("21.10.2021 19:45:25"));
        expected.add(sdf.parse("29.2.2028 5:4:7"));
        expected.add(sdf.parse("30.01.2014 12:56:22"));
        expected.add(sdf.parse("19.03.2016 00:00:00"));

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"WRITE_MESSAGE\" and date between \"11.12.2013 0:00:00\" and \"14.11.2015 07:08:01\"");
        expected.add(sdf.parse("14.11.2015 07:08:01"));
        expected.add(sdf.parse("11.12.2013 0:0:0"));
        expected.add(sdf.parse("11.12.2013 10:11:12"));
        expected.add(sdf.parse("12.12.2013 21:56:30"));

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"WRITE_MESSAGE\" and date between \"11.12.2012 0:00:00\" and \"11.12.2012 23:59:59\"");

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"DOWNLOAD_PLUGIN\" and date between \"11.12.2021 0:00:00\" and \"03.01.2030 23:59:59\"");

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"DOWNLOAD_PLUGIN\" and date between \"11.12.2021 0:00:00\" and \"11.11.2021 23:59:59\"");

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"SOLVE_TASK\" and date between \"21.10.2021 19:45:25\" and \"21.10.2021 19:45:25\"");
        expected.add(sdf.parse("21.10.2021 19:45:25"));

        doAssert(expected, actual);

        actual = logParser.execute("get date for event = \"SOLVE_TASK\" and date between \"1.1.2021 0:00:00\" and \"01.01.2030 00:00:00\"");
        expected.add(sdf.parse("21.10.2021 19:45:25"));
        expected.add(sdf.parse("29.2.2028 5:4:7"));

        doAssert(expected, actual);

    }

    private <U,T extends Set<U>> void doAssert(T expected, T actual){
        MatcherAssert.assertThat(
                actual,
                Matchers.allOf(
                        Matchers.hasSize(expected.size()),
                        Matchers.containsInAnyOrder(expected.toArray())
                )
        );
        expected.clear();
    }


}