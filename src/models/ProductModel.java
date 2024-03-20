package models;

import java.math.BigDecimal;

public class ProductModel {
    private String name;
    private String size;
    private double price;
    private BigDecimal stockKey;

    public ProductModel(String name, String size, double price, BigDecimal stockKey) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.stockKey = stockKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BigDecimal getStockKey() {
        return stockKey;
    }

    public void setStockKey(BigDecimal stockKey) {
        this.stockKey = stockKey;
    }

}
