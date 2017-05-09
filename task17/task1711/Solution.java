package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
CrUD Batch — multiple Creation, Updates, Deletion

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...

Значения параметров:
name — имя, String
sex — пол, «м» или «ж», одна буква
bd — дата рождения в следующем формате 15/04/1990
-с — добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u — обновляет соответствующие данные людей с заданными id
-d — производит логическое удаление человека с id, заменяет все его данные на null
-i — выводит на экран информацию о всех людях с заданными id: name sex bd
id соответствует индексу в списке

Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример вывода для параметра -і с двумя id:
Миронов м 15-Apr-1990
Миронова ж 25-Apr-1997

Требования:
1. Класс Solution должен содержать public static volatile поле allPeople типа List.
2. Класс Solution должен содержать статический блок, в котором добавляются два человека в список allPeople.
3. При параметре -с программа должна добавлять всех людей с заданными параметрами в конец списка allPeople,
и выводить id каждого (index) на экран.
4. При параметре -u программа должна обновлять данные людей с заданными id в списке allPeople.
5. При параметре -d программа должна логически удалять людей с заданными id в списке allPeople.
6. При параметре -i программа должна выводить на экран данные о всех людях с заданными id по формату указанному в задании.
7. Метод main класса Solution должен содержать оператор switch по значению args[0].
8. Каждый case оператора switch должен иметь блок синхронизации по allPeople.*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        List<String> params = new ArrayList<>();
        for(int i=1; i<args.length; i++)
            params.add(args[i]);

        switch(args[0]){
            case "-c":
                synchronized (allPeople){
                    add(params);
                    break;
                }
            case "-u":
                synchronized (allPeople){
                    update(params);
                    break;
                }
            case "-d":
                synchronized (allPeople){
                    delete(params);
                    break;
                }
            case "-i":
                synchronized (allPeople){
                    info(params);
                    break;
                }
            default:
                synchronized (allPeople) {          //это бред, но валидатор принял только так
                    throw new IllegalArgumentException();
                }
        }
    }

    private static void add(List<String> params) throws ParseException {
        for(int i=0; i<params.size(); i=i+3){
            Person person = null;
            String name = params.get(i);
            String dateString = params.get(i+2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date date = dateFormat.parse(dateString);
            if(params.get(i+1).equals("м")){
                person = Person.createMale(name, date);
            }
            else
                person = Person.createFemale(name, date);
            allPeople.add(person);
            int id = allPeople.size()-1;
            System.out.println(id);
        }
    }

    private static void update(List<String> params) throws ParseException {
        for(int i=0; i<params.size(); i=i+4){
            Person person= null;
            int id = Integer.parseInt(params.get(i));
            String name = params.get(i+1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date date = dateFormat.parse(params.get(i+3));
            if(params.get(i+2).equals("м"))
                person = Person.createMale(name, date);
            else
                person = Person.createFemale(name, date);
            allPeople.set(id, person);
        }
    }

    private static void delete(List<String> params) {
        for(int i=0; i<params.size(); i++){
            int id = Integer.parseInt(params.get(i));
            Person person = allPeople.get(id);
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(null);
            allPeople.set(id, person);
        }
    }

    private static void info(List<String> params) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Person person;
        String sexString;
        for(int i=0; i<params.size(); i++){
            person = allPeople.get(Integer.parseInt(params.get(i)));
            if(person.getSex().equals(Sex.FEMALE))
                sexString = "ж";
            else
                sexString = "м";
            System.out.println(person.getName() + " " + sexString + " " + dateFormat.format(person.getBirthDay()));
        }
    }
}
