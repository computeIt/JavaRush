package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.*;

//      Читаем и пишем в файл: Human
//        Реализуй логику записи в файл и чтения из файла для класса Human.
//        Поле name в классе Human не может быть пустым.
//        Метод main реализован только для вас и не участвует в тестировании.
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File file1 = File.createTempFile("C:\\Users\\Igor\\Desktop\\data1.txt", null);
            OutputStream outputStream = new FileOutputStream(file1);
            InputStream inputStream = new FileInputStream(file1);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car")); //asset in english = актив
            ivanov.save(outputStream);                                                  //wrote
            outputStream.flush();                                                       //flush the buffer

            Human somePerson = new Human();             //create new Human
            somePerson.load(inputStream);               //and load it from the file

            if(ivanov.equals(somePerson))               //check that loaded Human is equals our saved Human
                System.out.println("they are equal");
            else
                System.out.println("they are not equal");

            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }
    
    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            String isNameExist = (name != null) ? "yes" : "no";         //we are marking file - is there an information about object??? - it will need us later - during loading
            writer.println(isNameExist);                                   

            if(isNameExist.equals("yes")) {
                writer.println(name);
                if(assets.size() > 0){
                    for(Asset asset : assets){
                        writer.println(asset.getName());
                        writer.println(asset.getPrice());
                    }
                }
            }
            writer.flush();     
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String isNameExist = reader.readLine();
                if(isNameExist.equals("yes")){
                    name = reader.readLine();
                    try {
                        String s = reader.readLine();
                        this.assets = new ArrayList<>();

                        while (!s.equals("")) {
                            Asset as = new Asset(s);
                            as.setPrice(Double.parseDouble(reader.readLine()));
                            this.assets.add(as);
                            s = reader.readLine();
                        }
                    }catch (NullPointerException e){}
                }
            reader.close();
        }
    }
}
