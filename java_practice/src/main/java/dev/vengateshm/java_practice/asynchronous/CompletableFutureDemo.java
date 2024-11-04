package dev.vengateshm.java_practice.asynchronous;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    public CompletableFuture<EmployeeData> getEmployeeData() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getEmployeeData() " + Thread.currentThread().getName());
            return EmployeeDataList.getEmployeeDataList()
                    .stream()
                    .filter(e -> e.getId().equals("100"))
                    .findAny().orElse(null);
        });
    }

    public CompletableFuture<Integer> getRating(EmployeeData employeeData) {
        System.out.println("getRating() " + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> {
            return employeeData.getRating();
        });
    }

    public CompletableFuture<Map<String, Long>> groupEmployeeByGender() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("groupEmployeeByGender() " + Thread.currentThread().getName());
            return EmployeeDataList.getEmployeeDataList()
                    .stream()
                    .collect(Collectors.groupingBy(EmployeeData::getGender, Collectors.counting()));
        });
    }

    public CompletableFuture<List<String>> getAllEmails() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getAllEmails() " + Thread.currentThread().getName());
            return EmployeeDataList.getEmployeeDataList()
                    .stream()
                    .map(EmployeeData::getEmail)
                    .collect(Collectors.toList());
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureDemo cfd = new CompletableFutureDemo();
        CompletableFuture<Integer> results = cfd.getEmployeeData().thenCompose(cfd::getRating);
        System.out.println(results.get());

        CompletableFuture<String> stringCompletableFuture = cfd.groupEmployeeByGender().thenCombine(cfd.getAllEmails(), (map, list) -> map + " " + list);
        System.out.println(stringCompletableFuture.get());

        CompletableFuture<String> weatherFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("weatherFuture() " + Thread.currentThread().getName());
            simulateDelay(2); // 2 seconds delay
            return "Weather: Sunny, 25°C";
        });

        CompletableFuture<String> newsFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("newsFuture() " + Thread.currentThread().getName());
            simulateDelay(3); // 3 seconds delay
            return "News: Stock markets are up!";
        });

        CompletableFuture<String> stockPriceFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("stockPriceFuture() " + Thread.currentThread().getName());
            simulateDelay(1); // 1 second delay
            return "Stock Price: AAPL - $150.00";
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(weatherFuture, newsFuture, stockPriceFuture);
        allOf.thenRun(() -> {
            System.out.println("All Futures Completed");
            System.out.println(weatherFuture.join());
            System.out.println(newsFuture.join());
            System.out.println(stockPriceFuture.join());
        }).join();

        CompletableFuture<String> newsFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("newsFuture() " + Thread.currentThread().getName());
            simulateDelay(3); // 3 seconds delay
            return "News: Stock markets are down!";
        });

        CompletableFuture<String> stockPriceFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("stockPriceFuture() " + Thread.currentThread().getName());
            simulateDelay(1); // 1 second delay
            return "Stock Price: AAPL - $160.00";
        });

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(newsFuture1, stockPriceFuture1);
        anyOf.thenAccept(result -> {
            System.out.println("Any Futures Completed");
            System.out.println(result);
        }).join();

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
                    gracefullyShutdown();
                    return "String 1";
                })
                .exceptionally(ex -> {
                    return "Exception getting String 1";
                });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            gracefullyShutdown();
            return "String 2";
        });

        CompletableFuture<String> thenned = cf1.thenCombine(cf2, (r1, r2) -> {
            return r1 + " " + r2;
        }).handle((result, ex) -> {
            if (ex != null) {
                return "Exception: " + ex.getMessage();
            }
            return result;
        });

        System.out.println(thenned.get());
    }

    private static void gracefullyShutdown() {
        throw new RuntimeException("something failed");
    }

    private static void simulateDelay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
