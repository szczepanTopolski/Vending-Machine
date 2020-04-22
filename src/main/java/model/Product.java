package model;

public class Product {

    private String name;
    private int amount;
    private float price;

    public Product(String name, int amount, float price){
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }
}
