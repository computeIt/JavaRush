package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;
/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        List<Word> list = new ArrayList<Word>();
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        list = detectAllWords(crossword, "emoh", "home", null, "", "same", "emas", "fderlk", "klredf", "fulmp", "poeejj", "jjeeop", "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "o", "", null, "test", "eo", "oe");
        for (Word s : list) {
            System.out.println(s.toString());
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        boolean textFineshed = false;
        List<Word> list = new ArrayList<Word>();
        for (String findWords : words) {
            if (findWords == null || findWords == "") continue;
            textFineshed = false;
            int xStart = 0,
                    yStart = 0,
                    xEnd = 0,
                    yEnd = 0;
            boolean isFirstSimbol, isText = false,
                    up = true, down = true, left = true, right = true,
                    leftUp = true, leftDown = true, rightUp = true, rightDown = true;
            char[] simbolWords = findWords.toLowerCase().toCharArray();
            isFirstSimbol = false;
            for (int x = 0; x < crossword.length; x++) {
                for (int y = 0; y < crossword[x].length; y++) {
                    if ((char) crossword[x][y] == simbolWords[0]) isFirstSimbol = true;
                    if (isFirstSimbol) {
                        xStart = x;
                        yStart = y;
                        for (int i = 0; i < simbolWords.length; i++) {
                            if (up) {
                                try {
                                    up = (char) crossword[x - i][y] == simbolWords[i];
                                    xEnd = x - i;
                                    yEnd = y;
                                    if (up && i + 1 == simbolWords.length) {
                                        isText = true;
                                        down = left = right = leftDown = leftUp = rightUp = rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    up = false;
                                }
                            }
                            if (down) {
                                try {
                                    down = (char) crossword[x + i][y] == simbolWords[i];
                                    xEnd = x + i;
                                    yEnd = y;
                                    if (down && i + 1 == simbolWords.length) {
                                        isText = true;
                                        left = right = leftDown = leftUp = rightUp = rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    down = false;
                                }
                            }
                            if (left) {
                                try {
                                    left = (char) crossword[x][y - i] == simbolWords[i];
                                    xEnd = x;
                                    yEnd = y - i;
                                    if (left && i + 1 == simbolWords.length) {
                                        isText = true;
                                        right = leftDown = leftUp = rightUp = rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    left = false;
                                }
                            }
                            if (right) {
                                try {
                                    right = (char) crossword[x][y + i] == simbolWords[i];
                                    xEnd = x;
                                    yEnd = y + i;
                                    if (right && i + 1 == simbolWords.length) {
                                        isText = true;
                                        leftDown = leftUp = rightUp = rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    right = false;
                                }
                            }
                            if (leftUp) {
                                try {
                                    leftUp = (char) crossword[x - i][y - i] == simbolWords[i];
                                    xEnd = x - i;
                                    yEnd = y - i;
                                    if (leftUp && i + 1 == simbolWords.length) {
                                        isText = true;
                                        leftDown = rightUp = rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    leftUp = false;
                                }
                            }
                            if (leftDown) {
                                try {
                                    leftDown = (char) crossword[x + i][y - i] == simbolWords[i];
                                    xEnd = x + i;
                                    yEnd = y - i;
                                    if (leftDown && i + 1 == simbolWords.length) {
                                        isText = true;
                                        rightUp = rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    leftDown = false;
                                }
                            }
                            if (rightUp) {
                                try {
                                    rightUp = (char) crossword[x - i][y + i] == simbolWords[i];
                                    xEnd = x - i;
                                    yEnd = y + i;
                                    if (rightUp && i + 1 == simbolWords.length) {
                                        isText = true;
                                        rightDown = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    rightUp = false;
                                }
                            }
                            if (rightDown) {
                                try {
                                    rightDown = (char) crossword[x + i][y + i] == simbolWords[i];
                                    xEnd = x + i;
                                    yEnd = y + i;
                                    if (rightDown && i + 1 == simbolWords.length) isText = true;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    rightDown = false;
                                }
                            }
                            if (isText) {
                                Word word = new Word(findWords);
                                word.setStartPoint(yStart, xStart);
                                word.setEndPoint(yEnd, xEnd);
                                list.add(word);
                                textFineshed = true;
                            }
                            if (textFineshed) break;
                            if (!(up || down || right || left || leftUp || leftDown || rightUp || rightDown)) {
                                isFirstSimbol = false;
                                up = down = left = right = leftDown = leftUp = rightUp = rightDown = true;
                                break;
                            }
                        }
                        if (textFineshed) break;
                    }
                    if (textFineshed) break;
                }
                if (textFineshed) break;
            }
        }
        return list;
    }
    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;
        public Word(String text) {
            this.text = text;
        }
        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }
        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }
        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
