package dev.vengateshm.java_practice.streams.order_report;

import java.util.Arrays;
import java.util.List;

public class DynamicPricing {
    public static void main(String[] args) {
        Product laptop = new Product("P001", "Laptop", "Electronics", false, 1200.0);
        Product phone = new Product("P002", "Phone", "Electronics", false, 700.0);
        Product mouse = new Product("P003", "Mouse", "Accessories", false, 400.0);

        Customer regular = new Customer("Regular");
        Customer vip = new Customer("VIP");
        Customer premiumVip = new Customer("Premium VIP");

        Order order1 = new Order("ORD001", Arrays.asList(
                new OrderItem(mouse, 2),
                new OrderItem(phone, 1)), regular);

        Order order2 = new Order("ORD002", Arrays.asList(
                new OrderItem(phone, 1),
                new OrderItem(laptop, 1)), vip);

        Order order3 = new Order("ORD003", Arrays.asList(
                new OrderItem(laptop, 1),
                new OrderItem(mouse, 1)), premiumVip);

        List<Order> orders = Arrays.asList(order1, order2, order3);

        orders.forEach(order -> {
            double total = DynamicPricing.calculateFinalOrderPrice(order);
            System.out.println("Order ID: " + order.getOrderId() + " | Segment: " + order.getCustomer().getSegment() + " | Final Price: ₹" + total);
        });
    }

    public static double calculateFinalOrderPrice(Order order) {
        return order.getItems().stream()
                .mapToDouble(orderItem -> {
                    double productPrice = orderItem.getProduct().getPrice();
                    double totalPrice = productPrice * orderItem.getQuantity();
                    switch (order.getCustomer().getSegment()) {
                        case "VIP":
                            if (productPrice > 500)
                                totalPrice *= 0.9;
                            break;
                        case "Premium VIP":
                            if (productPrice > 1000)
                                totalPrice *= 0.8;
                            break;
                    }

                    return totalPrice;
                }).sum();
    }
}
