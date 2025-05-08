package dev.vengateshm.java_practice.streams.order_report;

import java.util.Objects;

class Product {
    private String productId;

    private String name;
    private String category;
    private boolean isOutOfStock;

    public Product(String productId) {
        this.productId = productId;
    }

    // Constructor, getters, setters
    public Product(String name, String category, boolean isOutOfStock) {
        this.name = name;
        this.category = category;
        this.isOutOfStock = isOutOfStock;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isOutOfStock() == product.isOutOfStock() && Objects.equals(getProductId(), product.getProductId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getName(), getCategory(), isOutOfStock());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", isOutOfStock=" + isOutOfStock +
                '}';
    }
}
