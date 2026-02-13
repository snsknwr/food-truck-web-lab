package com.foodtruck;

public class Sandwich implements MenuItem {
    private String name;
    private double price;

    public Sandwich(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}
