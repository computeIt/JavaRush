package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

//Конвертация из одного класса в другой используя JSON
//        НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1
//
//        Два класса имеют одинаковые поля, но не имеют общий суперкласс. Пример, классы First и Second.
//        Реализовать логику метода convertOneToAnother, который должен возвращать объект класса resultClassObject,
// значения полей которого равны значениям полей в объекте one.
//        Используй объект типа ObjectMapper.
//        Известно, что у классов есть JSON аннотация, у которой значение property равно имени класса в нижнем регистре.
//        На примере класса First, это className=»first»
//        Классы First и Second не участвуют в тестировании, они предоставлены в качестве тестовых данных.
//
//        Требования:
//        1. Метод convertOneToAnother должен возвращать объект класса resultClassObject значения полей которого равны
// значениям полей объекта one.
//        2. В методе convertOneToAnother должен быть создан объект типа ObjectMapper.
//        3. Метод convertOneToAnother должен быть статическим.
//        4. Метод convertOneToAnother должен быть публичным.

public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper om = new ObjectMapper();
        StringWriter writer = new StringWriter();
        om.writeValue(writer, one);

        /*тут важно понимать, что, например, если мы записывали в строку объект класса First следующего вида
        First first = new First(100, "any string as a name")
        то строка json внутри writer сейчас выглядит следующим образом
        {"className":"first","i":100,"name":"any string as a name"}
        мы просто меняем first на second, работая с json строкой, как с обычной, и запихиваем ее в StringReader,
        откуда впоследствии вытаскиваем вместо json-строки - объект другого класса(т.к. java его считала другим классом в результате наших
        манипуляций с объектом, пока тот был json строкой*/
        String oneClassName = one.getClass().getSimpleName().toLowerCase();
        String resClassName = resultClassObject.getSimpleName().toLowerCase();
        String jsonString = writer.toString().replaceFirst(oneClassName, resClassName);
        /*после этой строки кода наша json-строка стала выглядеть так
        {"className":"second","i":100,"name":"any string as a name"}
        мы подменили имя класса и в 56й строке указываем, какой класс нам нужен в результате а именно - resultClassObject
        данный класс совпадает с тем, который указан в самой json-строке, поэтому после десериализации мы получим объект класса Second*/

        StringReader sReader = new StringReader(jsonString);
        Object result = om.readValue(sReader, resultClassObject);
        return result;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")/*тут мы задаем имя, которое будет фигурировать 
    перед именем класса в json строке после сериализации - см строку 43*/
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))/*это - изменение видимого имени класса*/
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i;
        public String name;
    }
}
