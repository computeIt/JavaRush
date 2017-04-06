package com.javarush.task.task31.task3101;

//      Проход по дереву файлов
//        1. На вход метода main подаются два параметра.
//        Первый — path — путь к директории, второй — resultFileAbsolutePath — имя файла, который будет содержать результат.
//        2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
//        2.1. Если у файла длина в байтах больше 50, то удалить его (используй метод FileUtils.deleteFile).
//        2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
//        2.2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
//        2.2.2. Переименовать resultFileAbsolutePath в ‘allFilesContent.txt‘ (используй метод FileUtils.renameFile).
//        2.2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять «\n«.
//        Все файлы имеют расширение txt.

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    static List<Path> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        MyFileVisitor mfv = new MyFileVisitor();

        Files.walkFileTree(path, mfv);

        Collections.sort(list, new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return o1.getFileName().compareTo(o2.getFileName());
            }
        });

        File finalFile;
        FileUtils.renameFile(resultFileAbsolutePath, finalFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt"));

        FileWriter fWriter = new FileWriter(finalFile);
        for(Path p : list){
            FileReader fReader = new FileReader(p.toFile());
            while(fReader.ready()){
                char[] charBuf = new char[(int)p.toFile().length()];//по заданию, мы работаем с файлами до 50 байт
                fReader.read(charBuf);
                fWriter.write(charBuf);
            }
            fReader.close();
            fWriter.write("\n");
            fWriter.flush();
        }
        fWriter.close();

    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    private static class MyFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(Files.size(file)>50)
                FileUtils.deleteFile(file.toFile());
            else if(Files.size(file)<=50)
                list.add(file);
            return super.visitFile(file, attrs);
        }
    }
}
