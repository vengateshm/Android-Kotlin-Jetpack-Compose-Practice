package dev.vengateshm.java_practice.streams;

import java.util.stream.IntStream;

public class StreamPrime {
    public static void main(String[] args) {
        System.out.println(isPrime(1));
        System.out.println(isPrime(2));
        System.out.println(isPrime(3));
        System.out.println(isPrime(5));
        System.out.println(isPrime(7));
        System.out.println(isPrime(9));
        System.out.println(isPrime(29));
        System.out.println(isPrime(30));
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;

        return IntStream.rangeClosed(2, (int) Math.sqrt(num)).noneMatch(n -> num % n == 0);
    }
}
