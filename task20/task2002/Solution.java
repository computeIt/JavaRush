package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
*/
//      Реализуйте логику записи в файл и чтения из файла для класса JavaRush.
//        В файле your_file_name.tmp может быть несколько объектов JavaRush.
//        Метод main реализован только для вас и не участвует в тестировании
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File file1 = File.createTempFile("C:\\Users\\Igor\\Desktop\\data11.txt", null);
            OutputStream outputStream = new FileOutputStream(file1);
            InputStream inputStream = new FileInputStream(file1);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            User ivanov = new User();
            ivanov.setFirstName("Ivan");
            ivanov.setLastName("Ivanov");
            ivanov.setBirthDate(new Date());
            ivanov.setMale(true);
            ivanov.setCountry(User.Country.RUSSIA);
            javaRush.users.add(ivanov);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println("saved and loaded objects equal? - " + loadedObject.equals(javaRush));
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            if(this == null)
                writer.println("nullObject");
            else {
                writer.println("neNullObject");
                writer.println(users.size());
                for(User user : users){
                    if(user == null)
                        writer.println("nullUser");
                    else{
                        writer.println("neNullUser");

                        if(user.getFirstName()==null)
                            writer.println("noFirstName");
                        else
                            writer.println(user.getFirstName());

                        if(user.getLastName()==null)
                            writer.println("noLastName");
                        else
                            writer.println(user.getLastName());

                        if(user.getBirthDate() == null)
                            writer.println("noBirthDate");
                        else
                            writer.println(user.getBirthDate().getTime());

                        if(user.isMale() == true)
                            writer.println("true");
                        else
                            writer.println("false");

                        if(user.getCountry() == null)
                            writer.println("noCountry");
                        else
                            writer.println(user.getCountry().getDisplayedName());
                    }
                }
            }
            writer.flush();
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                String s = reader.readLine();
                if (s.equals("neNullObject")) {
                    int i = Integer.parseInt(reader.readLine());
                    while ((i--) > 0) {
                        User user = new User();
                        String flag = reader.readLine();
                        if (flag.equals("nullUser"))
                            user = null;
                        else if (flag.equals("neNullUser")) {
                            user = new User();
                            String firstName = reader.readLine();
                            if (firstName.equals("noFirstName"))
                                user.setFirstName(null);
                            else
                                user.setFirstName(firstName);

                            String lastName = reader.readLine();
                            if (lastName.equals("noLastName"))
                                user.setLastName(null);
                            else
                                user.setLastName(lastName);

                            String birthDate = reader.readLine();
                            if (birthDate.equals("noBirthDate"))
                                user.setBirthDate(null);
                            else
                                user.setBirthDate(new Date(Long.parseLong(birthDate)));

                            String male = reader.readLine();
                            user.setMale(Boolean.parseBoolean(male));

                            String country = reader.readLine();
                            if (country.equals("noCountry"))
                                user.setCountry(null);
                            else {
                                switch (country) {
                                    case ("Ukraine"):
                                        user.setCountry(User.Country.UKRAINE);
                                        break;
                                    case ("Russia"):
                                        user.setCountry(User.Country.RUSSIA);
                                        break;
                                    default:
                                        user.setCountry(User.Country.OTHER);
                                        break;
                                }
                            }
                        }
                        users.add(user);
                    }
                }
            }
            reader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;
        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
