//IDE категорически не хотела устанавливать и понимать что от нее требуется с исходными импортами
//я их срубил, с помощью IDE установил аналогичные но с другими путями
//сходил на http://requestb.in/, получил ссылку, поправил прогу в соответствии с заданием
//запрос прошел, все отлично отработало, http://requestb.in/ дал инфу о запросе, но 
//валидатор разругался на импорты и не принял
//либо я на лыжах, либо всему виной то, что перешел с community версии IDE на полную 

package com.javarush.task.task40.task4002;

import org.apache.hc.client5.http.impl.sync.HttpClientBuilder;
import org.apache.hc.client5.http.methods.HttpPost;
import org.apache.hc.client5.http.sync.HttpClient;
import org.apache.hc.core5.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Опять POST, а не GET
Исправь ошибки в методе sendPost, чтобы он отправлял POST-запрос с переданными параметрами.
Примечание: метод main в тестировании не участвует, но чтобы программа корректно работала локально,
можешь зайти на сайт http://requestb.in/, создать свой RequestBin и использовать его в main.

Требования:
1. Метод sendPost должен создавать объект типа HttpPost с параметром url.
2. Метод sendPost должен вызвать метод setEntity у созданного объекта типа HttpPost.
3. В OutputStream соединения должны быть записаны переданные в метод sendPost параметры.
4. Метод sendPost должен использовать метод getHttpClient для получения HttpClient.*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.sendPost("https://requestb.in/t2hv8wt2", "name=zapp&mood=good&locale=&id=777");
    }

    public void sendPost(String url, String urlParameters) throws Exception {
        HttpClient client = getHttpClient();
        HttpPost request = new HttpPost(url);//правил тут - вместо GET поставил POST

        request.addHeader("User-Agent", "Mozilla/5.0");

        HttpResponse response = client.execute(request);

        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
            result.append(responseLine);
        }

        System.out.println("Response: " + result.toString());
    }

    protected HttpClient getHttpClient() {
        return HttpClientBuilder.create().build();
    }
}
