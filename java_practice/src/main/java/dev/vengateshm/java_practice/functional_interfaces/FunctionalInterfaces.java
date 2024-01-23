package dev.vengateshm.java_practice.functional_interfaces;

import java.util.function.Consumer;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Consumer<String> c1 = s1 -> System.out.print(s1 + "C1| ");
        Consumer<String> c2 = s2 -> System.out.print(s2 + "C2| ");
        c1.andThen(c2).andThen(c1).accept("Globe | ");
        System.out.println();
        c1.accept("Gold | ");
    }
}
