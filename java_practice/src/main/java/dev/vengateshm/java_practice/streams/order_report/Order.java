package dev.vengateshm.java_practice.streams.order_report;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

class Order {
    private String orderId;
    private List<OrderItem> items;
    private double totalValue;
    private LocalDateTime orderDateTime; // Added order placement timestamp
    private Customer customer;

    public Order(String orderId, List<OrderItem> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public Order(List<OrderItem> items, double totalValue, LocalDateTime orderDateTime) {
        this.items = items;
        this.totalValue = totalValue;
        this.orderDateTime = orderDateTime;
    }

    public Order(String orderId, List<OrderItem> items, Customer customer) {
        this.orderId = orderId;
        this.items = items;
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(getTotalValue(), order.getTotalValue()) == 0 && Objects.equals(getOrderId(), order.getOrderId()) && Objects.equals(getItems(), order.getItems()) && Objects.equals(getOrderDateTime(), order.getOrderDateTime()) && Objects.equals(getCustomer(), order.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getItems(), getTotalValue(), getOrderDateTime(), getCustomer());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", items=" + items +
                ", totalValue=" + totalValue +
                ", orderDateTime=" + orderDateTime +
                ", customer=" + customer +
                '}';
    }
}
