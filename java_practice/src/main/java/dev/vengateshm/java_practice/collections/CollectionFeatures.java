package dev.vengateshm.java_practice.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionFeatures {
    public static void main(String[] args) {
        List<String> list = Collections.nCopies(5, "Hello!");
        System.out.println(list);

        List<Integer> numbers = Arrays.asList(1, 3, 4, 5, 6, 2, 2, 3, 2, 1, 2);
        int frequency = Collections.frequency(numbers, 2);
        System.out.println(frequency);

        Map<Integer, Integer> countMap = numbers.stream().collect(Collectors.toMap(
                number -> number,
                number -> Collections.frequency(numbers, number),
                (existingValue, newValue) -> existingValue // To avoid duplicate key exception
        ));
        System.out.println(countMap);


        List<Integer> list1 = new ArrayList<>();
        Collections.addAll(list1, 1, 2, 3, 4, 5);
        List<Integer> list2 = new ArrayList<>();
        Collections.addAll(list2, 2, 3, 7, 8, 9, 10);
        boolean disjoint = Collections.disjoint(list1, list2);
        System.out.println(disjoint);

        Collections.singleton("Hello"); // Creates immutable set

        Collections.rotate(list1,list1.size());
        System.out.println(list1);
        Collections.rotate(list1,2);
        System.out.println(list1);
        Collections.rotate(list1,-2);
        System.out.println(list1);
    }
}
