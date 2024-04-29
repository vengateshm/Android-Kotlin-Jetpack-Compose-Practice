package dev.vengateshm.java_practice.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperators {
    public static void main(String[] args) {
        List<Integer> list = List.of(25, 15, 75, 35, 40, 5, 10, 55, 60);
        System.out.println(list.stream().takeWhile(n -> n < 30).toList());// Take elements until first element < 30
        System.out.println(list.stream().filter(n -> n < 30).toList());
        System.out.println(list.stream().dropWhile(n -> n < 30).toList());// Drop elements until first element < 30 and include all subsequent elements

        //Stream.ofNullable
        List<String> names = Arrays.asList("Ram", "Raja", null, "Vengatesh");

        List<String> filteredNames = names.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(filteredNames);

        List<String> filteredNames1 = names.stream().flatMap(Stream::ofNullable).collect(Collectors.toList());
        System.out.println(filteredNames1);

        //Stream.iterate
        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        //collectingAndThen
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 50000),
                new Employee("Bob", 65000),
                new Employee("Charlie", 78000),
                new Employee("Raj", 45000)
        );

        Long avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .boxed()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(Double::doubleValue),
                        Math::round
                ));
        System.out.println(avgSalary);

        //dropWhile, takeWhile
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9);

        List<Integer> takeDrop = numbers.stream()
                .dropWhile(n -> n < 3)
                .takeWhile(n -> n < 7)
                .collect(Collectors.toList());
        System.out.println(takeDrop);

        //teeing
        Map<String, Integer> minMaxMap = numbers.stream()
                .collect(
                        Collectors.teeing(
                                Collectors.maxBy(Integer::compareTo),
                                Collectors.minBy(Integer::compareTo),
                                (e1, e2) -> Map.of("max", e1.get(), "min", e2.get())
                        )
                );
        System.out.println(minMaxMap);

        //Stream.concat()
        int sum = Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 6)).mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        //Collectors.partitioningBy
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(evenOdd.get(Boolean.TRUE));
        System.out.println(evenOdd.get(Boolean.FALSE));

        //IntStream.range(), IntStream.rangeClosed()
        List<Integer> is1 = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> is2 = IntStream.rangeClosed(1,10).boxed().collect(Collectors.toList());
        System.out.println(is1);
        System.out.println(is2);

        List<String> fn = Arrays.asList("Bob", "Alice");
        List<String> ln = Arrays.asList("Smith", "Jones");

        List<String> res = IntStream.range(0, Math.min(fn.size(), ln.size()))
                .mapToObj(i -> fn.get(i) + " " + ln.get(i))
                .collect(Collectors.toList());
        System.out.println(res);
    }
}
