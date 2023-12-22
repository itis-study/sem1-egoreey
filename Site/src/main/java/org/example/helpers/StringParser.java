package org.example.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringParser {
    public static String listIntToString(List<Integer> list) {
        StringBuilder s = new StringBuilder();
        for (int i: list)
            s.append(i).append(" ");
        return s.toString();
    }

    public static List<Integer> stringToListInt(String s) {
        List<Integer> result = new ArrayList<>();
        Scanner scan = new Scanner(s);
        while (scan.hasNextInt())
            result.add(scan.nextInt());
        scan.close();
        return result;
    }
}
