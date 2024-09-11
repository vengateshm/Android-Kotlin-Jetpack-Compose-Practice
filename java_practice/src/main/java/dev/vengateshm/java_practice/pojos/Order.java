package dev.vengateshm.java_practice.pojos;

import java.util.List;

public class Order {
    private String id;
    private double totalAmount;
    private Customer customer;
    private List<Product> products;

    public Order(String id, double totalAmount, Customer customer) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.customer = customer;
    }

    public Order(String id, double totalAmount, Customer customer, List<Product> products) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
