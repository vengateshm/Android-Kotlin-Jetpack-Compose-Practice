package dev.vengateshm.java_practice.null_handling;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import io.reactivex.rxjava3.annotations.NonNull;

public class OrderService {
    public Double processOrder(Order order) {
        if (order != null) {
            if (order.getCustomer() != null) {
                if (order.getCustomer().getName() != null
                        && !order.getCustomer().getName().isEmpty()) {
                    System.out.println("Customer Name: " + order.getCustomer().getName());
                    if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
                        double total = 0.0;
                        for (OrderItem item : order.getOrderItems()) {
                            if (item != null && item.getProduct() != null) {
                                total += item.getQuantity() * item.getProduct().getPrice();
                            }
                        }
                        return total;
                    }
                }
            }
        }
        return 0.0;
    }

    public Double processOrderWithOptional(@NonNull Order order) {
        Objects.requireNonNull(order, "Order cannot be null");
        Optional.ofNullable(order.getCustomer())
                .map(Customer::getName)
                .filter(name -> !name.isEmpty())
                .ifPresent(name -> System.out.println("Customer Name: " + name));
        return Optional.ofNullable(order.getOrderItems())
                .filter(items -> !items.isEmpty())
                .map(Collection::stream)
                .orElse(Stream.empty())
                .filter(Objects::nonNull)
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
    }
}
