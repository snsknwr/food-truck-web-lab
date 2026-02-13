package com.foodtruck;

public class Salad implements MenuItem {
    private String name;
    private double price;

    public Salad(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
