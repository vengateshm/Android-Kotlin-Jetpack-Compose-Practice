package dev.vengateshm.java_practice.streams.order_report;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderReport {
    public static void main(String[] args) {
// Current time for testing purposes
        LocalDateTime now = LocalDateTime.now(java.time.ZoneId.of("Asia/Kolkata"));

        List<Order> orders = List.of(
                // Orders within the last 24 hours and above ₹500
                new Order(List.of(
                        new OrderItem(new Product("Laptop", "Electronics", false), 1),
                        new OrderItem(new Product("Keyboard", "Electronics", false), 2)
                ), 850, now.minusHours(20)),
                new Order(List.of(
                        new OrderItem(new Product("Book", "Books", false), 3),
                        new OrderItem(new Product("Pen", "Stationery", false), 5)
                ), 620, now.minusHours(10)),
                new Order(List.of(
                        new OrderItem(new Product("Mouse", "Electronics", true), 2)
                ), 550, now.minusHours(1)),
                new Order(List.of(
                        new OrderItem(new Product("Notebook", "Stationery", false), 4),
                        new OrderItem(new Product("Eraser", "Stationery", false), 1)
                ), 700, now.minusHours(23)),

                // Orders within the last 24 hours but below ₹500
                new Order(List.of(
                        new OrderItem(new Product("Cable", "Electronics", false), 1)
                ), 300, now.minusHours(15)),
                new Order(List.of(
                        new OrderItem(new Product("Pencil", "Stationery", false), 2)
                ), 450, now.minusHours(5)),

                // Orders older than 24 hours and above ₹500
                new Order(List.of(
                        new OrderItem(new Product("Monitor", "Electronics", false), 1)
                ), 900, now.minusDays(2)),
                new Order(List.of(
                        new OrderItem(new Product("Novel", "Books", false), 2)
                ), 580, now.minusDays(1).minusHours(5)),

                // Orders with out-of-stock items (within last 24 hours and above ₹500)
                new Order(List.of(
                        new OrderItem(new Product("Tablet", "Electronics", true), 1),
                        new OrderItem(new Product("Charger", "Electronics", false), 1)
                ), 700, now.minusHours(12)),
                new Order(List.of(
                        new OrderItem(new Product("T-shirt", "Apparel", true), 3),
                        new OrderItem(new Product("Socks", "Apparel", false), 2)
                ), 650, now.minusHours(8))
        );

        OrderReport generator = new OrderReport();
        Map<String, Integer> report = generator.generateOrderReportByCategoryAndQuantity(orders);

        System.out.println("Category-wise Sales Report (Last 24 Hours - Total Quantity Sold):");
        report.forEach((category, totalQuantity) -> System.out.println(category + ": " + totalQuantity));

        testStockIssue();
    }

    private static void testStockIssue() {
        Map<String, Integer> productStock = new HashMap<>();
        productStock.put("P001", 100);
        productStock.put("P002", 50);
        productStock.put("P003", 200);


        List<Order> orders = new ArrayList<>();
        orders.add(new Order("0001", Arrays.asList(
                new OrderItem(new Product("P001"), 30),
                new OrderItem(new Product("P002"), 60)
        )));
        orders.add(new Order("0002", Arrays.asList(
                new OrderItem(new Product("P003"), 50),
                new OrderItem(new Product("P002"), 40)
        )));
        orders.add(new Order("0003", Arrays.asList(
                new OrderItem(new Product("P001"), 120),
                new OrderItem(new Product("P003"), 30)
        )));

        List<String> problematicOrders = orders.stream()
                .filter(order -> order.getItems().stream()
                        .anyMatch(item -> productStock.get(item.getProduct().getProductId()) < item.getQuantity()))
                .map(Order::getOrderId)
                .collect(Collectors.toList());
        System.out.println("Problematic Orders : ");
        System.out.println(problematicOrders);
    }

    public Map<String, Integer> generateOrderReportByCategoryAndQuantity(List<Order> orders) {
        LocalDateTime twentyFourHrsAgo = LocalDateTime.now(ZoneId.of("Asia/Kolkata")).minusHours(24);
        return orders.stream()
                .filter(order -> order.getOrderDateTime().isAfter(twentyFourHrsAgo))
                .filter(order -> order.getTotalValue() > 500)
                .flatMap(order -> order.getItems().stream())
                .filter(orderItem -> !orderItem.getProduct().isOutOfStock())
                .collect(Collectors.groupingBy(
                        orderItem -> orderItem.getProduct().getCategory(),
                        Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}
