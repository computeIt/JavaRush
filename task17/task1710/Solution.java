package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
CrUD — Create, Update, Delete

Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id

Значения параметров:
name — имя, String
sex — пол, «м» или «ж», одна буква
bd — дата рождения в следующем формате 15/04/1990
-c — добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u — обновляет данные человека с данным id
-d — производит логическое удаление человека с id, заменяет все его данные на null
-i — выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
id соответствует индексу в списке

Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров:
-c Миронов м 15/04/1990

Пример вывода для параметра -і:
Миронов м 15-Apr-1990

Требования:
1. Класс Solution должен содержать public static поле allPeople типа List.
2. Класс Solution должен содержать статический блок, в котором добавляются два человека в список allPeople.
3. При запуске программы с параметром -с программа должна добавлять человека с заданными параметрами в конец списка allPeople, и выводить id (index) на экран.
4. При запуске программы с параметром -u программа должна обновлять данные человека с заданным id в списке allPeople.
5. При запуске программы с параметром -d программа должна логически удалять человека с заданным id в списке allPeople.
6. При запуске программы с параметром -i программа должна выводить на экран данные о человеке с заданным id по формату указанному в задании.
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        switch (args[0]){
            case "-c":
                add(args[1], args[2], args[3]);
                break;
            case "-u":
                update(args[1], args[2], args[3], args[4]);
                break;
            case "-d":
                delete(args[1]);
                break;
            case "-i":
                info(args[1]);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void info(String arg) {
        Person person = allPeople.get(Integer.parseInt(arg));
        String name = person.getName();
        String sex = "ж";
        if(person.getSex().equals(Sex.MALE))
            sex = "м";
        Date date = person.getBirthDay();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.println(name + " " + sex + " " + dateFormat.format(date));
    }

    private static void delete(String arg) {
        int id = Integer.parseInt(arg);

        Person person = allPeople.get(id);
        person.setName(null);
        person.setSex(null);
        person.setBirthDay(null);
        allPeople.set(id, person);
    }
    
    private static void update(String arg, String arg1, String arg2, String arg3) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = dateFormat.parse(arg3);
        Person person = null;
        if(arg2.equals("м"))
            person = Person.createMale(arg1, date);
        else
            person = Person.createFemale(arg1, date);
        int id = Integer.parseInt(arg);
        allPeople.set(id, person);
    }
    
    private static void add(String arg, String arg1, String arg2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = dateFormat.parse(arg2);
        Person person = null;
        if(arg1.equals("м")) {
            person = Person.createMale(arg, date);
        }
        else {
            person = Person.createFemale(arg, date);
        }
        allPeople.add(person);
        int id = allPeople.size()-1;
        System.out.println(id);
    }
}
