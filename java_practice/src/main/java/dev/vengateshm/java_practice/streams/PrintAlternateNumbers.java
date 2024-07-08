package dev.vengateshm.java_practice.streams;

import java.util.stream.IntStream;

public class PrintAlternateNumbers {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        IntStream.range(0, arr.length)
                .filter(i -> i % 2 == 0)
                .map(i -> arr[i])
                .forEach(System.out::println);
    }
}
