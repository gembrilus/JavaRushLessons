package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        int[][] crossword2 = new int[][]{
                {'a', 'm', 'a', 'r', 'e', 'e'},
                {'m', 's', 'a', 'm', 'e', 'm'},
                {'e', 'a', 'a', 'r', 'o', 'o'},
                {'m', 'm', 'p', 'h', 'o', 'h'},
                {'h', 'r', 'e', 'm', 'o', 'h'}
        };
        detectAllWords(crossword2, "home", "same", "homer").forEach(System.out::println);

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        int height = crossword.length;
        int width = crossword[0].length;
        for (String word : words) {
            int start_x = 0;
            int start_y = 0;
            int end_x = 0;
            int end_y = 0;
            if (word.length() == 0) continue;
            int c = word.charAt(0);

            outer:
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (c != crossword[i][j]) continue;
                    start_y = i;
                    start_x = j;
                    int[] lastChar = findLast(i, j, word.substring(1), crossword);
                    if (lastChar == null) continue;
                    end_y = lastChar[0];
                    end_x = lastChar[1];
                    break outer;
                }
            }
            Word w = new Word(word);
            w.setStartPoint(start_x, start_y);
            w.setEndPoint(end_x, end_y);
            result.add(w);
        }
        return result;
    }

    private static int[] findLast(int x, int y, String word, int[][] a) {
        int c = word.charAt(0);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 &&
                        y + j >= 0 &&
                        x + i < a.length &&
                        y + j < a[0].length) {
                    if ((i == 0 && j == 0) || a[x + i][y + j] != c) continue;

                    int dx = x + i, dy = y + j;
                    for (int p = 0; p < word.length(); p++) {
                        if (dx >= 0 &&
                                dy >= 0 &&
                                dx < a.length &&
                                dy < a[0].length &&
                                a[dx][dy] == word.charAt(p)) {
                            if (p == word.length() - 1) return new int[]{dx, dy};
                        }
                        else break;
                        dx += i;
                        dy += j;

                    }
                }
            }
        }
        return null;
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
