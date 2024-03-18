package models;

public class ProductModel {
    protected String name;
    protected String size;
    protected double price;
    protected int stockKey;

    public ProductModel(String name, String size, double price, int stockKey) {
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

    public int getStockKey() {
        return stockKey;
    }

    public void setStockKey(int stockKey) {
        this.stockKey = stockKey;
    }

}
