package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}

Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"

Требования:
1. Метод getQuery должен принимать один параметр типа Map.
2. Метод getQuery должен иметь тип возвращаемого значения String.
3. Метод getQuery должен быть статическим.
4. Метод getQuery должен возвращать строку сформированную по правилам описанным в условии задачи.*/
public class Solution {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<>();       //иначе он будет доставать параметры не по порядку
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(getQuery(map));

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String, String> entry : params.entrySet()){
            if(entry.getValue() == null)                  //если value == null то переходим к следующей иттерации
                continue;
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" = \'");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\' and ");
        }

        String res = stringBuilder.toString();
        res = res.substring(0, res.lastIndexOf("\'")+1);    //обрезаем излишки
        return res;
    }
}
