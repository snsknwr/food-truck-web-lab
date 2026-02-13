package com.foodtruck;

import java.util.HashMap;

public class Menu {
    private HashMap<String, MenuItem> items;

    public Menu() {
        items = new HashMap<>();

        // Salads (5)
        items.put("salad_greek", new Salad("Greek Salad", 7.50));
        items.put("salad_caesar", new Salad("Caesar Salad", 7.25));
        items.put("salad_garden", new Salad("Garden Salad", 6.75));
        items.put("salad_cobb", new Salad("Cobb Salad", 8.50));
        items.put("salad_spinach", new Salad("Spinach Salad", 7.75));

        // Sandwiches (5)
        items.put("sandwich_turkey", new Sandwich("Turkey Sandwich", 9.50));
        items.put("sandwich_veggie", new Sandwich("Veggie Sandwich", 8.75));
        items.put("sandwich_tuna", new Sandwich("Tuna Sandwich", 9.25));
        items.put("sandwich_blt", new Sandwich("BLT Sandwich", 9.00));
        items.put("sandwich_chicken", new Sandwich("Chicken Sandwich", 10.25));

        // Drinks (5)
        items.put("drink_water", new Drink("Water", 1.50));
        items.put("drink_lemonade", new Drink("Lemonade", 2.75));
        items.put("drink_icedtea", new Drink("Iced Tea", 2.50));
        items.put("drink_soda", new Drink("Soda", 2.25));
        items.put("drink_juice", new Drink("Orange Juice", 3.50));
    }

    public MenuItem getItem(String code) {
        return items.get(code);
    }
}
