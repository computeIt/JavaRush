package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

/**
 * Created by Igor on 02.06.2017.
 */
public class SecurityProxyConnector implements Connector {
    private SimpleConnector simpleConnector;
    private SecurityChecker securityChecker = new SecurityCheckerImpl();

    public SecurityProxyConnector(String s) {
        this.simpleConnector = new SimpleConnector(s);
    }

    @Override
    public void connect() {
        if(securityChecker.performSecurityCheck()){
            simpleConnector.connect();
        }
    }
}

/*Требования:
1. В классе SecurityProxyConnector должно быть создано поле типа SimpleConnector.
2. В классе SecurityProxyConnector должно быть создано поле типа SecurityChecker.
3. Конструктор класса SecurityProxyConnector должен принимать один параметр типа String и инициализировать поле типа SimpleConnector.
4. Метод connect класса SecurityProxyConnector должен выполнять проверку безопасности с помощью вызова
метода performSecurityCheck у объекта типа SecurityChecker.
5. Если проверка безопасности была пройдена, должно быть выполнено подключение.
6. Если проверка безопасности не была пройдена, подключение не должно быть выполнено.
7. Класс SecurityProxyConnector должен поддерживать интерфейс Connector*/
