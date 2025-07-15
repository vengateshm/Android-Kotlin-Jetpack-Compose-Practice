package dev.vengateshm.java_practice.streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collector;
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
        Stream.iterate(1, n -> n + 1).limit(10).forEach(System.out::println);

        System.out.println("Fibonacci series:");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]}).limit(10).map(f -> f[0]).forEach(System.out::println);
        System.out.println();

        System.out.println("Compound interest:");
        Stream.iterate(1000.0, amt -> amt * 1.05).limit(10).forEach(System.out::println);
        System.out.println();

        System.out.println("Next 7 days:");
        Stream.iterate(LocalDate.now(), date -> date.plusDays(1)).limit(7).forEach(System.out::println);
        System.out.println();

        System.out.println("Random numbers:");
        Stream.generate(() -> new Random().nextInt(9000) + 1000).limit(5).forEach(System.out::println);
        System.out.println();

        //collectingAndThen
        List<Employee> employees = Arrays.asList(new Employee("Alice", 50000, "HR"), new Employee("Bob", 65000, "Finance"), new Employee("Charlie", 78000, "HR"), new Employee("Raj", 45000, "Technology"));

        Long avgSalary = employees.stream().mapToDouble(Employee::getSalary).boxed().collect(Collectors.collectingAndThen(Collectors.averagingDouble(Double::doubleValue), Math::round));
        System.out.println("Average salary:");
        System.out.println(avgSalary);

        //dropWhile, takeWhile
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 9);

        List<Integer> takeDrop = numbers.stream().dropWhile(n -> n < 3).takeWhile(n -> n < 7).collect(Collectors.toList());
        System.out.println(takeDrop);

        //teeing
        Map<String, Integer> minMaxMap = numbers.stream().collect(Collectors.teeing(Collectors.maxBy(Integer::compareTo), Collectors.minBy(Integer::compareTo), (e1, e2) -> Map.of("max", e1.get(), "min", e2.get())));
        System.out.println(minMaxMap);

        Map<String, Double> sumAvg = numbers.stream().collect(Collectors.teeing(Collectors.summingDouble(Integer::doubleValue), Collectors.averagingDouble(Integer::doubleValue), (sum, avg) -> Map.of("Total", sum, "Average", avg)));
        System.out.println("Sum and Average:");
        System.out.println(sumAvg);
        System.out.println();

        Map<String, Double> sumProduct = numbers.stream().collect(Collectors.teeing(Collectors.summingDouble(Integer::doubleValue), Collectors.reducing(1.0, Integer::doubleValue, (a, b) -> a * b), (sum, product) -> Map.of("Sum", sum, "Product", product)));
        System.out.println("Sum and Product:");
        System.out.println(sumProduct);
        System.out.println();

        Map<String, Integer> firstLast = numbers.stream().collect(Collector.of(() -> new int[2], (arr, val) -> {
            if (arr[0] == 0 && arr[1] == 0) arr[0] = val;
            arr[1] = val;
        }, (a, b) -> b, arr -> Map.of("First", arr[0], "Last", arr[1])));

        System.out.println("First and Last Item:");
        System.out.println(firstLast);
        System.out.println();

        Map<String, Long> countDistinct = numbers.stream().collect(Collectors.teeing(Collectors.counting(), Collectors.mapping(Integer::intValue, Collectors.collectingAndThen(Collectors.toSet(), set -> (long) set.size())), (count, distinct) -> Map.of("Count", count, "Distinct Count", distinct)));

        System.out.println("Count and Distinct Count:");
        System.out.println(countDistinct);
        System.out.println();

        //Stream.concat()
        int sum = Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 6)).mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        //Collectors.partitioningBy
        Map<Boolean, List<Integer>> evenOdd = numbers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(evenOdd.get(Boolean.TRUE));
        System.out.println(evenOdd.get(Boolean.FALSE));

        Map<Boolean, Double> salaryAveragePartition = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 50000, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Salary average partition");
        System.out.println(salaryAveragePartition);
        System.out.println();

        Map<Boolean, List<String>> salaryNamePartition = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 50000, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("Salary name partition");
        System.out.println(salaryNamePartition);
        System.out.println();

        Map<Boolean, Long> salaryCountPartition = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 50000, Collectors.counting()));
        System.out.println("Salary count partition");
        System.out.println(salaryCountPartition);
        System.out.println();

        //IntStream.range(), IntStream.rangeClosed()
        List<Integer> is1 = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        List<Integer> is2 = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        System.out.println(is1);
        System.out.println(is2);

        List<String> fn = Arrays.asList("Bob", "Alice");
        List<String> ln = Arrays.asList("Smith", "Jones");

        List<String> res = IntStream.range(0, Math.min(fn.size(), ln.size())).mapToObj(i -> fn.get(i) + " " + ln.get(i)).collect(Collectors.toList());
        System.out.println(res);

        List<String> r = StreamZip.zip(fn.stream(), ln.stream(), (f, l) -> f + " " + l).collect(Collectors.toList());
        System.out.println(r);

        int size = Stream.iterate(0, n -> n + 1).limit(30).skip(1).collect(Collectors.partitioningBy(n -> n % 2 == 0))
//                .get(true)
                .get(false).size();
        System.out.println(size);

        //Counting
        Long count = Arrays.asList(1, 2, 3, 4, 5, 6).stream().collect(Collectors.counting());
        System.out.println(count);

        //summarizingInt
        IntSummaryStatistics stats = Arrays.asList(1, 2, 3, 4, 5, 6).stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats);

        //mapping
        List<Integer> lengths = Arrays.asList("arabica", "robusta", "liberica", "excelsa").stream().collect(Collectors.mapping(String::length, Collectors.toList()));
        System.out.println(lengths);

        Integer totalLength = Arrays.asList("arabica", "robusta", "liberica", "excelsa").stream().collect(Collectors.mapping(String::length, Collectors.summingInt(Integer::intValue)));
        System.out.println(totalLength);

        Set<String> emails = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c")), new Order("2", 300.0, new Customer("x@y.z"))).stream().collect(Collectors.mapping(order -> order.getCustomer().getEmail(), Collectors.toSet()));
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

        Set<String> products = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c"), order1Products), new Order("2", 300.0, new Customer("x@y.z"), order2Products)).stream().flatMap(order -> order.getProducts().stream()).collect(Collectors.mapping(Product::getName, Collectors.toSet()));
        System.out.println(products);

        //joining
        String joined = Arrays.asList("arabica", "robusta", "liberica", "excelsa").stream().collect(Collectors.joining(", ", "{", "}"));
        System.out.println(joined);

        //groupingBy
        Map<Integer, Long> groupByLength = Arrays.asList("arabica", "robusta", "liberica", "excelsa").stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(groupByLength);

        Map<String, Double> totalSalaryByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.reducing(0.0, Employee::getSalary, Double::sum)));
        System.out.println("Total salary by department:");
        System.out.println(totalSalaryByDepartment);
        System.out.println();

        Map<String, List<String>> nameListByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println("Name list by department:");
        System.out.println(nameListByDepartment);
        System.out.println();

        Map<String, List<String>> depToNamesVanilla = new HashMap<>();
        for (Employee e : employees) {
            depToNamesVanilla.computeIfAbsent(e.getDepartment(), department -> new ArrayList<>()).add(e.getName());
        }
        System.out.println("Name list by department vanilla:");
        System.out.println(depToNamesVanilla);
        System.out.println();

        //filtering
        List<Order> highValueOrders = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c")), new Order("2", 300.0, new Customer("x@y.z")), new Order("3", 1000.0, new Customer("v@c.z"))).stream().collect(Collectors.filtering(order -> order.getTotalAmount() > 500.0, Collectors.toList()));
        highValueOrders.forEach(System.out::println);

        //toMap
        Map<String, Integer> mapByLength = Arrays.asList("apple", "banana", "cherry").stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(mapByLength);

        Map<String, Double> orderIdAmount = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c"), order1Products), new Order("2", 300.0, new Customer("x@y.z"), order2Products)).stream().collect(Collectors.toMap(Order::getId, Order::getTotalAmount));
        System.out.println(orderIdAmount);

        //toConcurrentMap
        Map<String, Double> orderIdAmountConcurrentMap = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c"), order1Products), new Order("2", 300.0, new Customer("x@y.z"), order2Products)).stream().collect(Collectors.toConcurrentMap(Order::getId, Order::getTotalAmount));
        System.out.println(orderIdAmountConcurrentMap);

        //reducing
        Integer fact = Arrays.asList(1, 2, 3, 4, 5).stream().collect(Collectors.reducing(1, (integer, integer2) -> integer * integer2));
        System.out.println(fact);

        Double totalSales = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c"), order1Products), new Order("2", 300.0, new Customer("x@y.z"), order2Products)).stream().map(Order::getTotalAmount).collect(Collectors.reducing(0.0, Double::sum));
        System.out.println(totalSales);

        //flatMapping
        List<Product> products1 = Arrays.asList(new Order("1", 200.0, new Customer("a@b.c"), order1Products), new Order("2", 300.0, new Customer("x@y.z"), order2Products)).stream().collect(Collectors.flatMapping(order -> order.getProducts().stream(), Collectors.toList()));
        System.out.println(products1);

        //summarizingDouble
        DoubleSummaryStatistics doubleSummaryStatistics = Arrays.asList(1.5, 2.5, 3.5, 4.5, 5.5).stream().collect(Collectors.summarizingDouble(Double::doubleValue));
        System.out.println(doubleSummaryStatistics);

        //summarizingLong
        LongSummaryStatistics longSummaryStatistics = Arrays.asList(10L, 20L, 30L, 40L, 50L).stream().collect(Collectors.summarizingLong(Long::longValue));
        System.out.println(longSummaryStatistics);

        //groupingByConcurrent
        ConcurrentMap<Integer, List<String>> lengthNameConcurrentMap = Arrays.asList("apple", "banana", "cherry", "date").stream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(lengthNameConcurrentMap);


    }
}
