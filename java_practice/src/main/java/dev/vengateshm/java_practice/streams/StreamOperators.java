package dev.vengateshm.java_practice.streams;

import java.util.List;

public class StreamOperators {
    public static void main(String[] args) {
        List<Integer> list = List.of(25, 15, 75, 35, 40, 5, 10, 55, 60);
        System.out.println(list.stream().takeWhile(n -> n < 30).toList());// Take elements until first element < 30
        System.out.println(list.stream().filter(n -> n < 30).toList());
        System.out.println(list.stream().dropWhile(n -> n < 30).toList());// Drop elements until first element < 30 and include all subsequent elements
    }
}
