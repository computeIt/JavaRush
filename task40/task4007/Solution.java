package com.javarush.task.task40.task4007;

import java.util.Calendar;

/*
Работа с датами
Реализуй метод printDate(String date).
Он должен в качестве параметра принимать дату (в одном из 3х форматов)
и выводить ее в консоль в соответствии с примером:

1) Для «21.4.2014 15:56:45» вывод должен быть:
День: 21
День недели: 1
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 4
Год: 2014
AM или PM: PM
Часы: 3
Часы дня: 15
Минуты: 56
Секунды: 45

2) Для «21.4.2014»:
День: 21
День недели: 1
День месяца: 21
День года: 111
Неделя месяца: 4
Неделя года: 17
Месяц: 4
Год: 2014

3) Для «17:33:40»:
AM или PM: PM
Часы: 5
Часы дня: 17
Минуты: 33
Секунды: 40

Используй класс Calendar.

Требования:
1. Если в метод printDate передана дата в формате "дата время", он должен вывести:
День, День недели, День месяца, День года, Неделя месяца, Неделя года, Месяц, Год, AM или PM, Часы, Часы дня, Минуты, Секунды.
2. Если в метод printDate передана дата в формате "дата", он должен вывести:
День, День недели, День месяца, День года, Неделя месяца, Неделя года, Месяц, Год.
3. Если в метод printDate передана дата в формате "время", он должен вывести:
AM или PM, Часы, Часы дня, Минуты, Секунды.
4. Используй статический метод getInstance класса Calendar для получения календаря.*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String[] splt = date.split(" ");

        boolean dateFlag = false, timeFlag = false;
        String sDate = "";
        String sTime = "";

        if(date.contains(".")){
            dateFlag = true;
            sDate = splt[0];
        }
        if(date.contains(":")){
            timeFlag = true;
            sTime = splt[0];
        }

        if(splt.length == 2){
            dateFlag = true;
            timeFlag = true;
            sDate = splt[0];
            sTime = splt[1];
        }

        String[] dateArr = new String[3];
        String[] timeArr = new String[3];

        Calendar cal = Calendar.getInstance();
        if(dateFlag == true){
            dateArr[0] = sDate.substring(0, sDate.indexOf("."));//day
            dateArr[1] = sDate.substring(sDate.indexOf(".")+1, sDate.lastIndexOf("."));//month
            dateArr[2] = sDate.substring(sDate.lastIndexOf(".")+1);//year
        }

        if(timeFlag == true){
            timeArr[0] = sTime.substring(0, sTime.indexOf(":"));//hours
            timeArr[1] = sTime.substring(sTime.indexOf(":")+1, sTime.lastIndexOf(":"));//minutes
            timeArr[2] = sTime.substring(sTime.lastIndexOf(":")+1);//seconds
        }

        if(dateFlag == true && timeFlag == true){
            cal.set(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[0]),
                    Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]), Integer.parseInt(timeArr[2]));
            System.out.println("День: " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println("День недели: " + ((cal.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : cal.get(Calendar.DAY_OF_WEEK) - 1));
            System.out.println("День месяца: " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + cal.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + cal.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + cal.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (cal.get(Calendar.MONTH)+1));
            System.out.println("Год: " + cal.get(Calendar.YEAR));
            System.out.println("AM или PM: " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
            System.out.println("Часы: " + cal.get(Calendar.HOUR));
            System.out.println("Часы дня: " + cal.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + cal.get(Calendar.MINUTE));
            System.out.println("Секунды: " + cal.get(Calendar.SECOND));
        }
        else if(dateFlag == true && timeFlag == false){
            cal.set(Integer.parseInt(dateArr[2]), Integer.parseInt(dateArr[1])-1, Integer.parseInt(dateArr[0]));
            System.out.println("День: " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println("День недели: " + ((cal.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : cal.get(Calendar.DAY_OF_WEEK) - 1));
            System.out.println("День месяца: " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + cal.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + cal.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + cal.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (cal.get(Calendar.MONTH)+1));
            System.out.println("Год: " + cal.get(Calendar.YEAR));
        }
        else if(dateFlag == false && timeFlag == true){
            cal.set(0,0,0, Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]), Integer.parseInt(timeArr[2]));
            System.out.println("AM или PM: " + (cal.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
            System.out.println("Часы: " + cal.get(Calendar.HOUR));
            System.out.println("Часы дня: " + cal.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + cal.get(Calendar.MINUTE));
            System.out.println("Секунды: " + cal.get(Calendar.SECOND));
        }
    }
}
