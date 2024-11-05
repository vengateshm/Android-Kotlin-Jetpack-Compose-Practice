package dev.vengateshm.java_practice.programs;

import java.util.Scanner;

public class CountNoOfWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string");
        String s = sc.nextLine();
        System.out.println("No of words " + countNoOfWords(s));
    }

    public static int countNoOfWords(String s) {
        if (s.trim().isEmpty()) return 0;
        int count = 0;

        if (s.charAt(0) != ' ') {
            count++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) != ' ') {
                count++;
            }
        }

        return count;
    }
}
