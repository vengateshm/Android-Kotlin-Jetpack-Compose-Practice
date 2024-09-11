package dev.vengateshm.java_practice.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import dev.vengateshm.java_practice.pojos.Customer;
import dev.vengateshm.java_practice.pojos.Order;
import dev.vengateshm.java_practice.pojos.Product;

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
        System.out.println("Average salary:");
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
        List<Integer> is2 = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        System.out.println(is1);
        System.out.println(is2);

        List<String> fn = Arrays.asList("Bob", "Alice");
        List<String> ln = Arrays.asList("Smith", "Jones");

        List<String> res = IntStream.range(0, Math.min(fn.size(), ln.size()))
                .mapToObj(i -> fn.get(i) + " " + ln.get(i))
                .collect(Collectors.toList());
        System.out.println(res);

        List<String> r = StreamZip.zip(fn.stream(), ln.stream(), (f, l) -> f + " " + l)
                .collect(Collectors.toList());
        System.out.println(r);

        int size = Stream.iterate(0, n -> n + 1)
                .limit(30)
                .skip(1)
                .collect(Collectors.partitioningBy(n -> n % 2 == 0))
//                .get(true)
                .get(false)
                .size();
        System.out.println(size);

        //Counting
        Long count = Arrays.asList(1, 2, 3, 4, 5, 6).stream().collect(Collectors.counting());
        System.out.println(count);

        //summarizingInt
        IntSummaryStatistics stats = Arrays.asList(1, 2, 3, 4, 5, 6).stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats);

        //mapping
        List<Integer> lengths = Arrays.asList("arabica", "robusta", "liberica", "excelsa")
                .stream()
                .collect(Collectors.mapping(String::length, Collectors.toList()));
        System.out.println(lengths);

        Integer totalLength = Arrays.asList("arabica", "robusta", "liberica", "excelsa")
                .stream()
                .collect(Collectors.mapping(String::length, Collectors.summingInt(Integer::intValue)));
        System.out.println(totalLength);

        Set<String> emails = Arrays.asList(
                        new Order("1", 200.0, new Customer("a@b.c")),
                        new Order("2", 300.0, new Customer("x@y.z")))
                .stream()
                .collect(Collectors.mapping(order -> order.getCustomer().getEmail(), Collectors.toSet()));
        System.out.println(emails);

        Product p1 = new Product("1", "p1");
        Product p2 = new Product("2", "p2");
        Product p3 = new Product("3", "p3");
        List<Product> order1Products = new ArrayList<>();
        order1Products.add(p1);
        order1Products.add(p2);
        List<Product> order2Products = new ArrayList<>();
        order1Products.add(p1);
        order1Products.add(p2);
        order1Products.add(p3);

        Set<String> products = Arrays.asList(
                        new Order("1", 200.0, new Customer("a@b.c"), order1Products),
                        new Order("2", 300.0, new Customer("x@y.z"), order2Products))
                .stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.mapping(Product::getName, Collectors.toSet()));
        System.out.println(products);

        //joining
        String joined = Arrays.asList("arabica", "robusta", "liberica", "excelsa")
                .stream()
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(joined);

        //groupingBy
        Map<Integer, Long> groupByLength = Arrays.asList("arabica", "robusta", "liberica", "excelsa")
                .stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(groupByLength);

        //filtering
        List<Order> highValueOrders = Arrays.asList(
                        new Order("1", 200.0, new Customer("a@b.c")),
                        new Order("2", 300.0, new Customer("x@y.z")),
                        new Order("3", 1000.0, new Customer("v@c.z")))
                .stream()
                .collect(Collectors.filtering(order -> order.getTotalAmount() > 500.0, Collectors.toList()));
        highValueOrders.forEach(System.out::println);


    }
}
