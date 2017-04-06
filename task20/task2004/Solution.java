package com.javarush.task.task20.task2004;

import java.io.*;

/* 
Читаем и пишем в файл статики
*/
//      Реализуй логику записи в файл и чтения из файла для класса ClassWithStatic.
//        Метод load должен инициализировать объект включая статические поля данными из файла.
//        Метод main не участвует в тестировании.
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File file1 = File.createTempFile("C:\\Users\\Igor\\Desktop\\data1.txt", null);
            OutputStream outputStream = new FileOutputStream(file1);
            InputStream inputStream = new FileInputStream(file1);

            ClassWithStatic classWithStatic = new ClassWithStatic();
            classWithStatic.i = 3;
            classWithStatic.j = 4;
            classWithStatic.save(outputStream);
            outputStream.flush();

            ClassWithStatic loadedObject = new ClassWithStatic();
            loadedObject.staticString = "something";
            loadedObject.i = 6;
            loadedObject.j = 7;

            loadedObject.load(inputStream);
            System.out.println("saved and downloaded object equals? - "+classWithStatic.equals(loadedObject));
            //check here that classWithStatic object equals to loadedObject object - проверьте тут, что classWithStatic и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class ClassWithStatic {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            if(this == null)
                writer.println("nullObject");
            else{
                writer.println("neNullObject");
                if(this.staticString == null)
                    writer.println("nullStaticString");
                else{
                    writer.println("neNullStaticString");
                    writer.println(ClassWithStatic.staticString);
                }
                writer.println(String.valueOf(i));
                writer.println(String.valueOf(j));
            }
            writer.flush();
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while(reader.ready()) {
                String firstLine = reader.readLine();
                if (firstLine.equals("nullObject")) {
                    ClassWithStatic.staticString = null;
                    this.i = 0;
                    this.j = 0;
                } else if (firstLine.equals("neNullObject")) {
                    firstLine = reader.readLine();
                    if (firstLine.equals("nullStaticString"))
                        ClassWithStatic.staticString = null;
                    else
                        ClassWithStatic.staticString = reader.readLine();
                    this.i = Integer.parseInt(reader.readLine());
                    this.j = Integer.parseInt(reader.readLine());
                }
            }
            reader.close();
            //implement this method - реализуйте этот метод
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClassWithStatic that = (ClassWithStatic) o;

            if (i != that.i) return false;
            return j == that.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }
}
