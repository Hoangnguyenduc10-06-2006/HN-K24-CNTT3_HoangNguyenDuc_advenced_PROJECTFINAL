package model;

public class Product {
    private String name;
    private double price;
    private int stock;
    private int category_id;
    private String brand;
    private String status;
    private int storage;
    private String color;
    private String description;

    public Product(String name, double price, int stock,int category_id, String brand, String status, int storage, String color, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category_id =category_id;
        this.brand = brand;
        this.status = status;
        this.storage = storage;
        this.color = color;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory(int category) {
        this.category_id = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category_id='" + category_id + '\'' +
                ", brand='" + brand + '\'' +
                ", status='" + status + '\'' +
                ", storage='" + storage + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
