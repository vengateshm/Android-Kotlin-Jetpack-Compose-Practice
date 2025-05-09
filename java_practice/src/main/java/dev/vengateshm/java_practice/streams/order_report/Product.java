package dev.vengateshm.java_practice.streams.order_report;

import java.util.Objects;

class Product {
    private String productId;

    private String name;
    private String category;
    private boolean isOutOfStock;
    private double price;

    public Product(String productId) {
        this.productId = productId;
    }

    // Constructor, getters, setters
    public Product(String name, String category, boolean isOutOfStock) {
        this.name = name;
        this.category = category;
        this.isOutOfStock = isOutOfStock;
    }

    public Product(String productId, String name, String category, boolean isOutOfStock, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.isOutOfStock = isOutOfStock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isOutOfStock() {
        return isOutOfStock;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isOutOfStock() == product.isOutOfStock() && Double.compare(getPrice(), product.getPrice()) == 0 && Objects.equals(getProductId(), product.getProductId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getName(), getCategory(), isOutOfStock(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", isOutOfStock=" + isOutOfStock +
                ", price=" + price +
                '}';
    }
}
