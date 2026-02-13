package com.foodtruck;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        port(4567);

        Menu menu = new Menu();

        // Home page with 3 dropdowns
        get("/", (req, res) -> {
            res.type("text/html");

            String html = "<html><body>";
            html += "<h2>Select your food items:</h2>";

            html += "<form action='/order' method='get'>";

            // Salad
            html += "<p><b>Salad:</b> ";
            html += "<select name='salad' required>";
            html += "<option value='' disabled selected>-- Select a salad --</option>";
            html += "<option value='salad_greek'>Greek Salad ($7.50)</option>";
            html += "<option value='salad_caesar'>Caesar Salad ($7.25)</option>";
            html += "<option value='salad_garden'>Garden Salad ($6.75)</option>";
            html += "<option value='salad_cobb'>Cobb Salad ($8.50)</option>";
            html += "<option value='salad_spinach'>Spinach Salad ($7.75)</option>";
            html += "</select></p>";

            // Sandwich
            html += "<p><b>Sandwich:</b> ";
            html += "<select name='sandwich' required>";
            html += "<option value='' disabled selected>-- Select a sandwich --</option>";
            html += "<option value='sandwich_turkey'>Turkey Sandwich ($9.50)</option>";
            html += "<option value='sandwich_veggie'>Veggie Sandwich ($8.75)</option>";
            html += "<option value='sandwich_tuna'>Tuna Sandwich ($9.25)</option>";
            html += "<option value='sandwich_blt'>BLT Sandwich ($9.00)</option>";
            html += "<option value='sandwich_chicken'>Chicken Sandwich ($10.25)</option>";
            html += "</select></p>";

            // Drink
            html += "<p><b>Drink:</b> ";
            html += "<select name='drink' required>";
            html += "<option value='' disabled selected>-- Select a drink --</option>";
            html += "<option value='drink_water'>Water ($1.50)</option>";
            html += "<option value='drink_lemonade'>Lemonade ($2.75)</option>";
            html += "<option value='drink_icedtea'>Iced Tea ($2.50)</option>";
            html += "<option value='drink_soda'>Soda ($2.25)</option>";
            html += "<option value='drink_juice'>Orange Juice ($3.50)</option>";
            html += "</select></p>";

            html += "<button type='submit'>Calculate Total</button>";
            html += "</form>";

            html += "</body></html>";
            return html;
        });

        // Order page: show chosen items + prices + total + discount
        get("/order", (req, res) -> {
            res.type("text/html");

            String saladCode = req.queryParams("salad");
            String sandwichCode = req.queryParams("sandwich");
            String drinkCode = req.queryParams("drink");

            if (saladCode == null || sandwichCode == null || drinkCode == null ||
                saladCode.equals("") || sandwichCode.equals("") || drinkCode.equals("")) {

                return "<html><body>"
                        + "<h2>Error</h2>"
                        + "<p>You must select 1 salad, 1 sandwich, and 1 drink.</p>"
                        + "<p><a href='/'>Go back</a></p>"
                        + "</body></html>";
            }

            MenuItem salad = menu.getItem(saladCode);
            MenuItem sandwich = menu.getItem(sandwichCode);
            MenuItem drink = menu.getItem(drinkCode);

            if (salad == null || sandwich == null || drink == null) {
                return "<html><body>"
                        + "<h2>Error</h2>"
                        + "<p>One of your selections was not found in the menu.</p>"
                        + "<p><a href='/'>Go back</a></p>"
                        + "</body></html>";
            }

            double subtotal = salad.getPrice() + sandwich.getPrice() + drink.getPrice();

            // Option 2: 10% discount if subtotal is over $20
            double discount = 0;
            if (subtotal > 20.00) {
                discount = subtotal * 0.10;
            }
            double total = subtotal - discount;

            String html = "<html><body>";
            html += "<h2>Your Order</h2>";
            html += "<ul>";
            html += "<li>Salad: " + salad.getName() + " - " + money(salad.getPrice()) + "</li>";
            html += "<li>Sandwich: " + sandwich.getName() + " - " + money(sandwich.getPrice()) + "</li>";
            html += "<li>Drink: " + drink.getName() + " - " + money(drink.getPrice()) + "</li>";
            html += "</ul>";

            html += "<p><b>Subtotal:</b> " + money(subtotal) + "</p>";

            if (discount > 0) {
                html += "<p><b>10% Discount:</b> -" + money(discount) + "</p>";
            }

            html += "<p><b>Total Charge:</b> " + money(total) + "</p>";

            // Link to receipt page
            html += "<p><a href='/receipt?salad=" + saladCode + "&sandwich=" + sandwichCode + "&drink=" + drinkCode + "'>View Receipt</a></p>";
            html += "<p><a href='/'>Back to menu</a></p>";

            html += "</body></html>";
            return html;
        });

        // Option 3: Receipt page with tax (8.875% NYC style)
        get("/receipt", (req, res) -> {
            res.type("text/html");

            String saladCode = req.queryParams("salad");
            String sandwichCode = req.queryParams("sandwich");
            String drinkCode = req.queryParams("drink");

            if (saladCode == null || sandwichCode == null || drinkCode == null ||
                saladCode.equals("") || sandwichCode.equals("") || drinkCode.equals("")) {

                return "<html><body>"
                        + "<h2>Error</h2>"
                        + "<p>Missing order information.</p>"
                        + "<p><a href='/'>Go back</a></p>"
                        + "</body></html>";
            }

            MenuItem salad = menu.getItem(saladCode);
            MenuItem sandwich = menu.getItem(sandwichCode);
            MenuItem drink = menu.getItem(drinkCode);

            if (salad == null || sandwich == null || drink == null) {
                return "<html><body>"
                        + "<h2>Error</h2>"
                        + "<p>One of your selections was not found in the menu.</p>"
                        + "<p><a href='/'>Go back</a></p>"
                        + "</body></html>";
            }

            double subtotal = salad.getPrice() + sandwich.getPrice() + drink.getPrice();

            // 10% discount if over $20
            double discount = 0;
            if (subtotal > 20.00) {
                discount = subtotal * 0.10;
            }
            double afterDiscount = subtotal - discount;

            // 8.875% NYC tax
            double tax = afterDiscount * 0.08875;
            double finalTotal = afterDiscount + tax;

            String html = "<html><body>";
            html += "<h2>Receipt</h2>";
            html += "<hr>";
            html += "<table border='0' cellpadding='5'>";
            html += "<tr><td>" + salad.getName() + "</td><td align='right'>" + money(salad.getPrice()) + "</td></tr>";
            html += "<tr><td>" + sandwich.getName() + "</td><td align='right'>" + money(sandwich.getPrice()) + "</td></tr>";
            html += "<tr><td>" + drink.getName() + "</td><td align='right'>" + money(drink.getPrice()) + "</td></tr>";
            html += "<tr><td colspan='2'><hr></td></tr>";
            html += "<tr><td>Subtotal</td><td align='right'>" + money(subtotal) + "</td></tr>";

            if (discount > 0) {
                html += "<tr><td>10% Discount</td><td align='right'>-" + money(discount) + "</td></tr>";
            }

            html += "<tr><td>Tax (8.875%)</td><td align='right'>" + money(tax) + "</td></tr>";
            html += "<tr><td><b>Final Total</b></td><td align='right'><b>" + money(finalTotal) + "</b></td></tr>";
            html += "</table>";
            html += "<hr>";
            html += "<p><a href='/'>Back to menu</a></p>";

            html += "</body></html>";
            return html;
        });

        System.out.println("Server started at http://localhost:4567");
    }

    // Format money like $7.50
    private static String money(double value) {
        return String.format("$%.2f", value);
    }
}
