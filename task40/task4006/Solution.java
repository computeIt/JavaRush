package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http (80).
Классы HttpURLConnection, HttpClient и т.д. не использовать.
Не оставляй закомементированный код.

Требования:
1. Метод getSite должен создавать объект класса Socket с правильными параметрами (String host, int port).
2. Метод getSite должен записать в OutputStream правильный запрос.
3. Метод getSite должен выводить на экран InputStream сокета.
4. Метод getSite не должен использовать HttpURLConnection или HttpClient.*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            String host = url.getHost();    //из урла тащим хост
            int port = 80;

            Socket socket = new Socket(host, port);

            OutputStream out = socket.getOutputStream();

            String request = "GET " + url.getPath();    //склеиваем get запрос

            out.write(request.getBytes());    //записываем его в выходной канал
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inContent;

            while((inContent = reader.readLine()) != null)
                System.out.println(inContent);    //распечатка содержимого inputStream сокета
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
