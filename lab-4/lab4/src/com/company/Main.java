package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    static Map<Integer, Integer[]> indexes = new HashMap<>();
    static Map<Integer, Boolean> errors = new HashMap<>();

    public static void main(String[] args) {
        String infByte1 = "10100110";
        String infByte2 = "011100111011";

        putIn();

        System.out.println("\nЗадание 1");
        bitPariteta(infByte1);
        System.out.println("\nЗадание 2");
        codeHemminga(infByte1);
        System.out.println("\nЗадание 3");
        findIncorrect(infByte2);
    }

    public static void putIn() {
        indexes.put(1, new Integer[]{1, 3, 5, 7, 9, 11});
        indexes.put(2, new Integer[]{2, 3, 6, 7, 10, 11});
        indexes.put(4, new Integer[]{4, 5, 6, 7, 12});
        indexes.put(8, new Integer[]{8, 9, 10, 11, 12});

        errors.put(1, false);
        errors.put(2, false);
        errors.put(4, false);
        errors.put(8, false);
    }

    public static void bitPariteta(String in) {
        boolean res = false;
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == '1') {
                res = !res;
            }
        }
        System.out.println("Бит паритета = " + (res ? 1 : 0));
    }

    public static void codeHemminga(String in) {
        char[] res = new char[]{
                0, 0,
                in.charAt(0),
                0,
                in.charAt(1), in.charAt(2), in.charAt(3),
                0,
                in.charAt(4), in.charAt(5), in.charAt(6), in.charAt(7),
        };

        for (int i : indexes.keySet()) {
            boolean fig = false;
            for (int v : indexes.get(i)) {
                if (res[v - 1] == '1') {
                    fig = !fig;
                }
            }
            res[i - 1] = fig ? '1' : '0';
        }

        System.out.println("Код Хэмминга = " + Arrays.toString(res));
    }


    public static void findIncorrect(String in) {
        Set<Integer> errorIndex = new HashSet<>();
        char[] res = in.toCharArray();
        for (int i : indexes.keySet()) {
            boolean fig = false;
            for (int v : indexes.get(i)) {
                if (res[v - 1] == '1') {
                    fig = !fig;
                }
            }
            char r = fig ? '1' : '0';
            errors.put(i, r != res[i - 1]);
        }
        for (int i : errors.keySet()) {
            if (errors.get(i)) {
                errorIndex.addAll(Arrays.asList(indexes.get(i)));
            }
        }
        for (int i : errors.keySet()) {
            if (!errors.get(i)) {
                errorIndex.removeAll(Arrays.asList(indexes.get(i)));
            }
        }
        System.out.println("Бит с ошибкой = " + errorIndex);
    }
}






