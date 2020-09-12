package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private Path logDir;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private List<Log> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        this.logDir = logDir;
        parse();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return (int) filterByDate(after, before).stream()
                .map(log -> log.ip)
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return filterByDate(after, before).stream()
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> user.equals(log.name))
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> event.equals(log.event))
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> status.equals(log.status))
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return logs.stream()
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) filterByDate(after, before).stream()
                .map(log -> log.name)
                .distinct()
                .count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return (int) filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user))
                .map(log -> log.event)
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.ip.equals(ip))
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.LOGIN))
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE))
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .filter(log -> log.eventNumber == task)
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DONE_TASK))
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DONE_TASK) && log.eventNumber == task)
                .map(log -> log.name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user) && log.event.equals(event))
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.status.equals(Status.FAILED))
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.status.equals(Status.ERROR))
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user) && log.event.equals(Event.LOGIN))
                .map(log -> log.date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user) && log.event.equals(Event.SOLVE_TASK) && log.eventNumber == task)
                .map(log -> log.date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user) && log.event.equals(Event.DONE_TASK) && log.eventNumber == task)
                .map(log -> log.date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user) && log.event.equals(Event.WRITE_MESSAGE))
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user) && log.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.date)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event != null)
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.ip.equals(ip))
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.name.equals(user))
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.status.equals(Status.FAILED))
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.status.equals(Status.ERROR))
                .map(log -> log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK) && log.eventNumber == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DONE_TASK) && log.eventNumber == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .collect(
                        Collectors.groupingBy(
                                log -> log.eventNumber,
                                Collectors.summingInt(l -> 1)
                        )
                );
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return filterByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DONE_TASK))
                .collect(
                        Collectors.groupingBy(
                                log -> log.eventNumber,
                                Collectors.summingInt(l -> 1)
                        )
                );
    }

    @Override
    public Set<Object> execute(String query) {

        String what, forWhat, value, afterString, beforeString;

        String regexpSimple = "get\\s+(\\w+)";
        String regexpBig = "get\\s+(\\w+)\\s+for\\s+(\\w+)\\s*=\\s*\"(.+?)\"(?:\\s+and\\s+date\\s+between\\s+\"(.+)\"\\s+and\\s+\"(.+)\")?";
        Pattern patternSimple = Pattern.compile(regexpSimple);
        Matcher matcherSimple = patternSimple.matcher(query);
        Pattern patternBig = Pattern.compile(regexpBig);
        Matcher matcherBig = patternBig.matcher(query);

        if (matcherSimple.matches()) {

            what = matcherSimple.group(1);
            return simpleExecute(what);

        } else if (matcherBig.matches()) {

            what = matcherBig.group(1);
            forWhat = matcherBig.group(2);
            value = matcherBig.group(3);
            afterString = matcherBig.group(4);
            beforeString = matcherBig.group(5);

            Date after = null;
            Date before = null;
            try {
                if (afterString != null && !afterString.isEmpty()) {
                    after = formatter.parse(afterString);
                }
            } catch (ParseException e){
                e.printStackTrace();
            }
            try {
                if (beforeString != null && !beforeString.isEmpty()) {
                    before = formatter.parse(beforeString);
                }
            } catch (ParseException e){
                e.printStackTrace();
            }
            return extendedExecute(what, forWhat, value, after, before);
        } else {
            return Collections.emptySet();
        }
    }

    private Set<Log> filter(Collection<Log> set, Predicate<Log> filter) {
        return set.stream()
                .filter(filter)
                .collect(Collectors.toSet());
    }

    private Set<Object> map(Collection<Log> set, String key) {
        Function<Log, Object> mapper = log -> {
            switch (key) {
                case "ip":
                    return log.ip;
                case "user":
                    return log.name;
                case "date":
                    return log.date;
                case "event":
                    return log.event;
                case "status":
                    return log.status;
                default:
                    return null;
            }
        };
        return set.stream()
                .map(mapper)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Set<Object> extendedExecute(final String what, final String forWhat, final String value, final Date after, final Date before) {
        Predicate<Log> filter = log -> {
            switch (forWhat) {
                case "ip":      return log.ip.equals(value);
                case "user":    return log.name.equals(value);
                case "date":
                    Date date = null;
                    try {
                        date = formatter.parse(value);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (date == null) return false;
                    return log.date.equals(date);
                case "event":   return log.event.equals(Event.valueOf(value));
                case "status":  return log.status.equals(Status.valueOf(value));
                default:        return false;
            }
        };

        List<Log> filteredByDate = betweenDates(after, before);

        Set<Log> filteredByFilter = filter(filteredByDate, filter);

        return map(filteredByFilter, what);
    }

    private Set<Object> simpleExecute(final String query) {
        return map(logs, query);
    }

    private void parse() {
        try (Stream<Path> stream = Files.list(logDir)) {
            for (Path path : stream.collect(Collectors.toSet())) {
                if (path.toString().endsWith(".log")) {
                    try (Stream<String> strings = Files.lines(path)) {
                        for (String string : strings.collect(Collectors.toSet())) {
                            logs.add(convertToLog(string));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Log convertToLog(String string) {
        Log log = new Log();
        String[] parts = string.trim().split("\\s+");
        int size = parts.length;
        log.ip = parts[0];
        log.status = Status.valueOf(parts[size - 1]);

        int pos = size - 2;
        if (parts[pos].matches("\\d+")) {
            log.eventNumber = Integer.valueOf(parts[pos]);
            log.event = Event.valueOf(parts[--pos]);
        } else {
            log.event = Event.valueOf(parts[pos]);
        }
        pos -= 1;

        try {
            log.date = formatter.parse(parts[pos - 1] + " " + parts[pos]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pos -= 2;

        StringBuilder name = new StringBuilder();
        for (int i = 1; i <= pos; i++) {
            name.append(parts[i]).append(" ");
        }

        log.name = name.toString().trim();

        return log;
    }

    private List<Log> filterByDate(Date after, Date before) {
        return logs.stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime()))
                        && (before == null || (log.date.getTime() <= before.getTime()))
                )
                .collect(Collectors.toList());
    }

    private List<Log> betweenDates(Date after, Date before) {
        return logs.stream()
                .filter(log -> (after == null || (log.date.getTime() > after.getTime()))
                        && (before == null || (log.date.getTime() < before.getTime()))
                )
                .collect(Collectors.toList());
    }

}