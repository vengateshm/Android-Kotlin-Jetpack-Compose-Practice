package dev.vengateshm.java_practice.streams;

import java.util.stream.LongStream;

public class FactorialStream {
    public static void main(String[] args) {
        System.out.println(factorial(10));
    }

    public static long factorial(long num) {
        return LongStream.rangeClosed(1, num).reduce(1, (a, b) -> a * b);
    }
}
