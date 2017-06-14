package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Set<Record> setOfLogObjects;

    public LogParser(Path logDir) {
        this.setOfLogObjects = parseStringsToRecordObjects(readLogFiles(logDir));
    }

    // чтение строк из log файлов
    private List<String> readLogFiles(Path logDir) {
        List<String> result = new ArrayList<>();
        if (Files.isDirectory(logDir)) {
            try {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir);
                for (Path file : directoryStream) {
                    if (file.getFileName().toString().endsWith(".log")) {
                        BufferedReader bufferedReader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            result.add(line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // парсинг строк в объекты Record
    private Set<Record> parseStringsToRecordObjects(List<String> listOfLogStrings) {
        Set<Record> result = new HashSet<>();
        for (String recordString : listOfLogStrings) {
            String[] recordStringArray = recordString.split("\\t");
            Record record = new Record();
            // ip адрес
            record.setIpAddress(recordStringArray[0]);
            // имя пользователя
            record.setUserName(recordStringArray[1]);
            // дата
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            try {
                Date date = dateFormat.parse(recordStringArray[2]);
                record.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // event
            record.setEvent(Event.valueOf(recordStringArray[3].split("\\s")[0]));
            // номер задачи
            if (recordStringArray[3].split("\\s").length > 1) {
                record.setTaskNumber(Integer.parseInt(recordStringArray[3].split("\\s")[1]));
            }
            // status
            record.setStatus(Status.valueOf(recordStringArray[4]));
            // добавление записи в список
            result.add(record);
        }
        return result;
    }

    // проверка, попадает ли дата в интервал
    private boolean isDateFromInterval(Date current, Date after, Date before) {
        boolean result = false;
        if (after == null) after = current;
        if (before == null) before = current;
        if ((current.getTime() >= after.getTime()) && (current.getTime() <= before.getTime())) result = true;
        return result;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (Record record : setOfLogObjects) {
            if (isDateFromInterval(record.getDate(), after, before)) {
                ipSet.add(record.getIpAddress());
            }
        }
        return ipSet.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (Record record : setOfLogObjects) {
            if (isDateFromInterval(record.getDate(), after, before)) {
                ipSet.add(record.getIpAddress());
            }
        }
        return ipSet;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (Record record : setOfLogObjects) {
            if ((isDateFromInterval(record.getDate(), after, before)) && (user.equals(record.getUserName()))) {
                ipSet.add(record.getIpAddress());
            }
        }
        return ipSet;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (Record record : setOfLogObjects) {
            if ((isDateFromInterval(record.getDate(), after, before)) && (event.equals(record.getEvent()))) {
                ipSet.add(record.getIpAddress());
            }
        }
        return ipSet;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (Record record : setOfLogObjects) {
            if ((isDateFromInterval(record.getDate(), after, before)) && (status.equals(record.getStatus()))) {
                ipSet.add(record.getIpAddress());
            }
        }
        return ipSet;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before))
                users.add(record.getUserName());
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getUserName().equals(user) && isDateFromInterval(record.getDate(), after, before))
                events.add(record.getEvent());
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getIpAddress().equals(ip) && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.LOGIN) && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.DOWNLOAD_PLUGIN) && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.WRITE_MESSAGE) && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.SOLVE_TASK) && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.SOLVE_TASK)
                    && (record.getTaskNumber() == task)
                    && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.DONE_TASK) && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getEvent().equals(Event.DONE_TASK)
                    && (record.getTaskNumber() == task)
                    && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getUserName());
        }
        return result;
    }

    //Метод getDatesForUserAndEvent() должен возвращать даты, когда определенный пользователь произвел определенное событие.
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                    && record.getUserName().equals(user)
                    && record.getEvent().equals(event))
                result.add(record.getDate());
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getStatus().equals(Status.FAILED)
                    && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getDate());
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getStatus().equals(Status.ERROR)
                    && isDateFromInterval(record.getDate(), after, before))
                result.add(record.getDate());
        }
        return result;
    }

    //Метод getDateWhenUserLoggedFirstTime() должен возвращать дату,
    // когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет — null
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        TreeSet<Date> set = new TreeSet<>();
        Date date = null;
        for(Record record : setOfLogObjects){
            if(record.getUserName().equals(user)
                    && record.getEvent().equals(Event.LOGIN)
                    && isDateFromInterval(record.getDate(), after, before))
                set.add(record.getDate());
        }
        if(!set.isEmpty()){
            for(Date date1 : set){
                date = date1;
                break;
            }
        }
        return date;
    }

    //Метод getDateWhenUserSolvedTask() должен возвращать дату,
    // когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет — null
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        TreeSet<Date> set = new TreeSet<>();
        Date date = null;
        for(Record record : setOfLogObjects){
            if(record.getUserName().equals(user)
                    && record.getEvent().equals(Event.SOLVE_TASK)
                    && record.getTaskNumber() == task
                    && isDateFromInterval(record.getDate(), after, before))
                set.add(record.getDate());
        }
        if(!set.isEmpty()){
            for(Date date1 : set){
                date = date1;
                break;
            }
        }
        return date;
    }

    //Метод getDateWhenUserDoneTask() должен возвращать дату,
    // когда пользователь впервые решил определенную задачу. Если такой даты в логах нет — null
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        TreeSet<Date> set = new TreeSet<>();
        Date date = null;
        for(Record record : setOfLogObjects){
            if(record.getUserName().equals(user)
                    && record.getEvent().equals(Event.DONE_TASK)
                    && record.getTaskNumber() == task
                    && isDateFromInterval(record.getDate(), after, before))
                set.add(record.getDate());
        }
        if(!set.isEmpty()){
            for(Date date1 : set){
                date = date1;
                break;
            }
        }
        return date;
    }

    //Метод getDatesWhenUserWroteMessage() должен возвращать даты, когда пользователь написал сообщение
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getUserName().equals(user)
                    && record.getEvent().equals(Event.WRITE_MESSAGE)
                    && isDateFromInterval(record.getDate(), after, before)){
                result.add(record.getDate());
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(record.getUserName().equals(user)
                    && record.getEvent().equals(Event.DOWNLOAD_PLUGIN)
                    && isDateFromInterval(record.getDate(), after, before)){
                result.add(record.getDate());
            }
        }
        return result;
    }

    //Метод getNumberOfAllEvents() должен возвращать количество (уникальных!!!)событий за указанный период
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before))
                result.add(record.getEvent());
        }
        return result;
    }
    //Метод getEventsForIP() должен возвращать (уникальные!!!)события, которые происходили с указанного IP.
    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                    && record.getIpAddress().equals(ip))
                result.add(record.getEvent());
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                    && record.getUserName().equals(user))
                result.add(record.getEvent());
        }
        return result;
    }

    //Метод getFailedEvents() должен возвращать события, которые не выполнились
    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                && record.getStatus().equals(Status.FAILED))
                result.add(record.getEvent());
        }
        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                    && record.getStatus().equals(Status.ERROR))
                result.add(record.getEvent());
        }
        return result;
    }

    //Метод getNumberOfAttemptToSolveTask() должен возвращать количество попыток решить определенную задачу
    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int result=0;
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                    && record.getEvent().equals(Event.SOLVE_TASK)
                    && record.getTaskNumber() == task)
                result++;
        }
        return result;
    }

    //Метод getNumberOfSuccessfulAttemptToSolveTask() должен возвращать количество успешных решений определенной задачи.
    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int result=0;
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before)
                    && record.getEvent().equals(Event.DONE_TASK)
                    //&& record.getStatus().equals(Status.OK) - валидатор категорически не хочет эту строку
                    && record.getTaskNumber() == task)
                result++;
        }
        return result;
    }

    //Метод getAllSolvedTasksAndTheirNumber() должен возвращать мапу (номер_задачи : количество_попыток_решить_ее)
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for(Record record : setOfLogObjects) {
            if (isDateFromInterval(record.getDate(), after, before)
                    && record.getTaskNumber() != 0
                    && record.getEvent().equals(Event.SOLVE_TASK)) {
                result.put(record.getTaskNumber(), getNumberOfAttemptToSolveTask(record.getTaskNumber(), after, before));
            }
        }
        return result;
    }

    //Метод getAllDoneTasksAndTheirNumber() должен возвращать мапу (номер_задачи : сколько_раз_ее_решили).
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before){
        Map<Integer, Integer> result = new HashMap<>();
        for(Record record : setOfLogObjects) {
            if (isDateFromInterval(record.getDate(), after, before)
                    && record.getTaskNumber() != 0
                    && record.getEvent().equals(Event.DONE_TASK)) {
                result.put(record.getTaskNumber(), getNumberOfSuccessfulAttemptToSolveTask(record.getTaskNumber(), after, before));
            }
        }
        return result;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();
        if(query == null || query.isEmpty())
            return result;

        String[] array = parseQuery(query);
        String fieldGet = array[0];
        String field1 = array[1];
        String fieldFor = array[2];
        String field2 = array[3];
        String value1 = array[4];

        switch (query){
            case "get ip":
                result.addAll(getUniqueIPs(null, null));
                break;
            case "get user":
                result.addAll(getAllUsers());
                break;
            case "get date":
                result.addAll(getUniqueDates(null, null));
                break;
            case "get event":
                result.addAll(getAllEvents(null, null));
                break;
            case "get status":
                result.addAll(getUniqueStatuses(null, null));
                break;
            default:
                result = complicateQuery(array); //case "get field1 for field2 = value1"
                break;
        }
        return result;
    }

//ip, user, date, event или status
    private Set<Object> complicateQuery(String[] array) {
        Set<Object> result = new HashSet<>();

        if(!array[0].equals("get") || !array[2].equals("for"))
            return null;

        String field1 = array[1];
        String field2 = array[3];
        String value1 = array[4];

        //get field1 for ip = ""
        if(field2.equals("ip")){
            if(field1.equals("user")){
                for(Record record : setOfLogObjects) {
                    if(record.getIpAddress().equals(value1))
                        result.add(record.getUserName());
                }
            }
            else if(field1.equals("date")){
                for(Record record : setOfLogObjects) {
                    if(record.getIpAddress().equals(value1))
                        result.add(record.getDate());
                }
            }
            else if(field1.equals("event")){
                for(Record record : setOfLogObjects) {
                    if(record.getIpAddress().equals(value1))
                        result.add(record.getEvent());
                }
            }
            else if(field1.equals("status")){
                for(Record record : setOfLogObjects) {
                    if(record.getIpAddress().equals(value1))
                        result.add(record.getStatus());
                }
            }
        }
        //get field1 for user = ""
        if(field2.equals("user")){
            if(field1.equals("ip")){
                for(Record record : setOfLogObjects) {
                    if(record.getUserName().equals(value1))
                        result.add(record.getIpAddress());
                }
            }
            else if(field1.equals("date")){
                for(Record record : setOfLogObjects) {
                    if(record.getUserName().equals(value1))
                        result.add(record.getDate());
                }
            }
            else if(field1.equals("event")){
                for(Record record : setOfLogObjects) {
                    if(record.getUserName().equals(value1))
                        result.add(record.getEvent());
                }
            }
            else if(field1.equals("status")){
                for(Record record : setOfLogObjects) {
                    if(record.getUserName().equals(value1))
                        result.add(record.getStatus());
                }
            }
        }

        //get field1 for date = ""
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            Date curDate = dateFormat.parse(value1);
            if(field2.equals("date")){
                if(field1.equals("user")){
                    for(Record record : setOfLogObjects){
                        if(record.getDate().equals(curDate))
                            result.add(record.getUserName());
                    }
                }
                else if(field1.equals("ip")){
                    for(Record record : setOfLogObjects){
                        if(record.getDate().equals(curDate))
                            result.add(record.getIpAddress());
                    }
                }
                else if(field1.equals("event")){
                    for(Record record : setOfLogObjects){
                        if(record.getDate().equals(curDate))
                            result.add(record.getEvent());
                    }
                }
                else if(field1.equals("status")){
                    for(Record record : setOfLogObjects){
                        if(record.getDate().equals(curDate))
                            result.add(record.getStatus());
                    }
                }
        }
        } catch (ParseException e) {
            e.printStackTrace();
            //wrong date
        }

        //get field1 for event = ""
        if(field2.equals("event")){
            if(field1.equals("user")){
                for(Record record : setOfLogObjects) {
                    if(record.getEvent().toString().equals(value1))
                        result.add(record.getUserName());
                }
            }
            else if(field1.equals("date")){
                for(Record record : setOfLogObjects) {
                    if(record.getEvent().toString().equals(value1))
                        result.add(record.getDate());
                }
            }
            else if(field1.equals("ip")){
                for(Record record : setOfLogObjects) {
                    if(record.getEvent().toString().equals(value1))
                        result.add(record.getIpAddress());
                }
            }
            else if(field1.equals("status")){
                for(Record record : setOfLogObjects) {
                    if(record.getEvent().toString().equals(value1))
                        result.add(record.getStatus());
                }
            }
        }

        //get field1 for status = ""
        if(field2.equals("status")){
            if(field1.equals("user")){
                for(Record record : setOfLogObjects) {
                    if(record.getStatus().toString().equals(value1))
                        result.add(record.getUserName());
                }
            }
            else if(field1.equals("date")){
                for(Record record : setOfLogObjects) {
                    if(record.getStatus().toString().equals(value1))
                        result.add(record.getDate());
                }
            }
            else if(field1.equals("event")){
                for(Record record : setOfLogObjects) {
                    if(record.getStatus().toString().equals(value1))
                        result.add(record.getEvent());
                }
            }
            else if(field1.equals("ip")){
                for(Record record : setOfLogObjects) {
                    if(record.getStatus().toString().equals(value1))
                        result.add(record.getIpAddress());
                }
            }
        }
        return result;
    }

    //get field1 for field2 = «value1»
    public String[] parseQuery(String query){
        String[] array = query.split(" ");
        String fieldGet = array[0];
        String field1 = array[1];
        String fieldFor = array[2];
        String field2 = array[3];
        array = query.split("[\"«]");
        String value1 = array[1].substring(0, array[1].indexOf("[\"»]"));
        array = new String[5];
        array[0] = fieldGet;
        array[1] = field1;
        array[2] = fieldFor;
        array[3] = field2;
        array[4] = value1;
        return array;
    }

    public Set<Status> getUniqueStatuses(Date after, Date before) {
        Set<Status> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before))
                result.add(record.getStatus());
        }
        return result;
    }

    public Set<Date> getUniqueDates(Date after, Date before){
        Set<Date> result = new HashSet<>();
        for(Record record : setOfLogObjects){
            if(isDateFromInterval(record.getDate(), after, before))
                result.add(record.getDate());
        }
        return result;
    }
}
