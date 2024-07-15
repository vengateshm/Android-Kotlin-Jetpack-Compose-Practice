package dev.vengateshm.java_practice.streams;

import java.util.Arrays;

public class CountWordsInString {
    public static void main(String[] args) {
        String s = "hello java! 17";

        System.out.println(Arrays.stream(s.split("\\s+")).count());
    }
}
