package dev.vengateshm.java_practice.streams;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoStringEqual {
    public static void main(String[] args) {
        System.out.println(method1("Hello", "World"));
        System.out.println(method1("Hello", "Hello"));
        System.out.println(method2("Hello", "World"));
        System.out.println(method2("ABC", "ABC"));
    }

    private static boolean method1(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return IntStream.range(0, s1.length())
                .allMatch(idx -> s1.charAt(idx) == s2.charAt(idx));
    }

    private static boolean method2(String s1, String s2) {
        return s1.chars().boxed().peek(System.out::println).collect(Collectors.toList())
                .equals(s2.chars().boxed().collect(Collectors.toList()));
    }
}
