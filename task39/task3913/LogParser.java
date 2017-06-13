package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//необходимо дополнить проверкой на все log-файлы в принимаемой директории
//возможно, стоит ввести дополнительное поле со считанными строками
//написать метод с проверкой на соответствие даты

public class LogParser implements IPQuery {
    private Path logDir;
    private List<String> allStrings;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        allStrings = getStringFromAllLogFiles(logDir);
    }

    private List<String> getStringFromAllLogFiles(Path logDir) {
        List<String> res = new ArrayList<>();

        File finalFile = logDir.toFile();
        if (!finalFile.isDirectory()) {
            return res;
        }

        File[] files = finalFile.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".log")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while (reader.ready()) {
                        res.add(reader.readLine());
                    }
                } catch (Exception e) {

                }
            }
        }
        return res;
    }

    public List<Object> parseString(String string) {
        List<Object> result = new ArrayList<>();
        String[] array = string.split("\\t");
        //split a string by a space or a tabulation
        String ip = array[0];
        String name = "";
        String stringDate = "";
        Date date = new Date();
        String stringEvent = "";
        String stringStatus = "";
        int i;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        for (i = 1; i < array.length; i++) {
            char ch1 = array[i].charAt(0);
            char ch2 = array[i + 1].charAt(0);

            if (Character.isDigit(ch1) && Character.isDigit(ch2)) {
                stringDate = array[i] + " " + array[i + 1];
                break;
            }
        }

        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        name = array[1];
        for (int j = 2; j < i; j++) {
            name = name + " " + array[j];
        }

        stringEvent = array[i + 2];
        if ((i + 5) == array.length)
            stringEvent = stringEvent + " " + array[i + 3];

        stringStatus = array[array.length - 1];
        //ip username date event status
        result.add(ip);
        result.add(name);
        result.add(date);
        result.add(stringEvent);
        result.add(stringStatus);

        return result;
    }


    private boolean checkDate(Date date, Date after, Date before) {
        if(after == null && before == null)
            return true;
        if(after == null && (date.getTime() <= before.getTime()))
            return true;
        if(before == null && (date.getTime() >= after.getTime()))
            return true;
        return false;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Object> objectList = new ArrayList<>();

        for(String str : allStrings){
            objectList = parseString(str);
            Date date = (Date) objectList.get(2);
            if(checkDate(date, after, before)){
                result.add((String) objectList.get(0));
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Object> objectList = new ArrayList<>();

        for(String str : allStrings){
            objectList = parseString(str);
            String currentUser = (String) objectList.get(1);
            Date date = (Date) objectList.get(2);
            if(currentUser.equals(user) && checkDate(date, after, before)){
                result.add((String) objectList.get(0));
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Object> objectList = new ArrayList<>();

        for(String str : allStrings){
            objectList = parseString(str);
            String currentEvent = String.valueOf(objectList.get(3));
            Date date = (Date) objectList.get(2);
            if(currentEvent.equals(String.valueOf(event)) && checkDate(date, after, before)){
                result.add((String) objectList.get(0));
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        List<Object> objectList = new ArrayList<>();

        for(String str : allStrings) {
            objectList = parseString(str);
            Date date = (Date) objectList.get(2);
            String currentStatus = String.valueOf(objectList.get(3));
            if(currentStatus.equals(String.valueOf(status)) && checkDate(date, after, before)){
                result.add((String) objectList.get(0));
            }
        }
        return result;
    }
}
