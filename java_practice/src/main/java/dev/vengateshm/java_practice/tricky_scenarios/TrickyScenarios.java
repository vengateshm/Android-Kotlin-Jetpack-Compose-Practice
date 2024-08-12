package dev.vengateshm.java_practice.tricky_scenarios;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class TrickyScenarios {
    public static void main(String[] args) {
        var list = new ArrayList<Integer>();
        list.add(100);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(400);
        list.add(200);

        // Less performant
        list.stream().filter(i -> i > 100).forEach(System.out::println);
        // Better performance
        list.parallelStream().filter(i -> i > 100).forEach(System.out::println);

        var names = new ArrayList<String>();
        names.add("Hello");
        names.add(null);
        names.add("World!");

        // Throws null pointer exception
//        names.stream().map(String::toUpperCase).forEach(System.out::println);
        names.stream().filter(Objects::nonNull).map(String::toUpperCase).forEach(System.out::println);

        // Good
        Optional<String> name = Optional.ofNullable(getUserName());
        if (name.isPresent()) {
            String upperCase = name.get().toUpperCase();
            System.out.println(upperCase);
        }

        // Better
        Optional.ofNullable(getUserName())
                .map(String::toUpperCase)
                .ifPresent(System.out::println);

        // Eager - orElse
        findProfileById(1).orElse(createDefault());
        // Lazy - orElseGet
        findProfileById(1).orElseGet(() -> createDefault());

        // Close stream with resources
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
            lines.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getUserName() {
//        return null;
        return "Hola!";
    }

    private static Optional<String> findProfileById(int id) {
//        return Optional.ofNullable(null);
        return Optional.ofNullable("null");
    }

    private static String createDefault() {
        System.out.println("Create default profile called");
        return "Default";
    }
}
