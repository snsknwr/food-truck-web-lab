package com.foodtruck;

import static spark.Spark.*;

public class App {

    private static final String STYLES = String.join("\n",
        "<style>",
        "  @import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=IBM+Plex+Mono:wght@400;600&family=Cormorant+Garamond:ital,wght@0,400;0,600;1,400&display=swap');",
        "",
        "  :root {",
        "    --bg: #0f0e0c;",
        "    --surface: #1a1815;",
        "    --surface-hover: #242119;",
        "    --amber: #ff6b35;",
        "    --amber-glow: #ff6b3544;",
        "    --gold: #e8c547;",
        "    --cream: #f5f0e8;",
        "    --cream-dim: #a69e91;",
        "    --green: #4ecb71;",
        "    --red: #ff4444;",
        "    --radius: 16px;",
        "  }",
        "",
        "  * { margin: 0; padding: 0; box-sizing: border-box; }",
        "",
        "  body {",
        "    font-family: 'Cormorant Garamond', Georgia, serif;",
        "    background: var(--bg);",
        "    color: var(--cream);",
        "    min-height: 100vh;",
        "    overflow-x: hidden;",
        "  }",
        "",
        "  body::before {",
        "    content: '';",
        "    position: fixed;",
        "    inset: 0;",
        "    background-image: url(\"data:image/svg+xml,%3Csvg viewBox='0 0 256 256' xmlns='http://www.w3.org/2000/svg'%3E%3Cfilter id='n'%3E%3CfeTurbulence type='fractalNoise' baseFrequency='0.9' numOctaves='4' stitchTiles='stitch'/%3E%3C/filter%3E%3Crect width='100%25' height='100%25' filter='url(%23n)' opacity='0.04'/%3E%3C/svg%3E\");",
        "    pointer-events: none;",
        "    z-index: 9999;",
        "  }",
        "",
        "  .grain {",
        "    position: fixed; inset: 0;",
        "    background: radial-gradient(ellipse at 20% 0%, #ff6b3508 0%, transparent 60%),",
        "                radial-gradient(ellipse at 80% 100%, #e8c54706 0%, transparent 60%);",
        "    pointer-events: none;",
        "    z-index: 0;",
        "  }",
        "",
        "  .container {",
        "    position: relative; z-index: 1;",
        "    max-width: 880px;",
        "    margin: 0 auto;",
        "    padding: 40px 24px 80px;",
        "  }",
        "",
        "  /* ── HEADER ── */",
        "  .header {",
        "    text-align: center;",
        "    margin-bottom: 56px;",
        "    animation: fadeDown 0.8s ease-out;",
        "  }",
        "",
        "  .header .tag {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 11px;",
        "    letter-spacing: 4px;",
        "    text-transform: uppercase;",
        "    color: var(--amber);",
        "    border: 1px solid var(--amber-glow);",
        "    display: inline-block;",
        "    padding: 6px 20px;",
        "    border-radius: 100px;",
        "    margin-bottom: 24px;",
        "    background: #ff6b3508;",
        "  }",
        "",
        "  .header h1 {",
        "    font-family: 'Bebas Neue', Impact, sans-serif;",
        "    font-size: clamp(52px, 10vw, 96px);",
        "    line-height: 0.9;",
        "    letter-spacing: 2px;",
        "    color: var(--cream);",
        "    margin-bottom: 12px;",
        "  }",
        "",
        "  .header h1 span {",
        "    color: var(--amber);",
        "    text-shadow: 0 0 40px var(--amber-glow);",
        "  }",
        "",
        "  .header p {",
        "    font-size: 18px;",
        "    color: var(--cream-dim);",
        "    font-style: italic;",
        "  }",
        "",
        "  /* ── CATEGORY ── */",
        "  .category {",
        "    margin-bottom: 48px;",
        "    animation: fadeUp 0.6s ease-out both;",
        "  }",
        "  .category:nth-child(1) { animation-delay: 0.2s; }",
        "  .category:nth-child(2) { animation-delay: 0.35s; }",
        "  .category:nth-child(3) { animation-delay: 0.5s; }",
        "",
        "  .category-label {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 11px;",
        "    letter-spacing: 3px;",
        "    text-transform: uppercase;",
        "    color: var(--cream-dim);",
        "    margin-bottom: 16px;",
        "    padding-left: 4px;",
        "  }",
        "",
        "  .cards {",
        "    display: grid;",
        "    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));",
        "    gap: 12px;",
        "  }",
        "",
        "  .card {",
        "    position: relative;",
        "    background: var(--surface);",
        "    border: 1.5px solid transparent;",
        "    border-radius: var(--radius);",
        "    padding: 20px 22px;",
        "    cursor: pointer;",
        "    transition: all 0.25s cubic-bezier(.4,0,.2,1);",
        "    display: flex;",
        "    justify-content: space-between;",
        "    align-items: center;",
        "    user-select: none;",
        "  }",
        "",
        "  .card:hover {",
        "    background: var(--surface-hover);",
        "    border-color: #ffffff10;",
        "    transform: translateY(-2px);",
        "    box-shadow: 0 8px 32px #00000040;",
        "  }",
        "",
        "  .card.selected {",
        "    border-color: var(--amber);",
        "    background: #ff6b350a;",
        "    box-shadow: 0 0 24px var(--amber-glow), inset 0 0 24px #ff6b3506;",
        "  }",
        "",
        "  .card .name {",
        "    font-family: 'Cormorant Garamond', serif;",
        "    font-size: 20px;",
        "    font-weight: 600;",
        "    color: var(--cream);",
        "  }",
        "",
        "  .card .price {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 15px;",
        "    color: var(--gold);",
        "    white-space: nowrap;",
        "    margin-left: 16px;",
        "  }",
        "",
        "  .card .check {",
        "    position: absolute;",
        "    top: -6px; right: -6px;",
        "    width: 24px; height: 24px;",
        "    background: var(--amber);",
        "    border-radius: 50%;",
        "    display: flex; align-items: center; justify-content: center;",
        "    font-size: 13px;",
        "    color: #fff;",
        "    opacity: 0;",
        "    transform: scale(0);",
        "    transition: all 0.25s cubic-bezier(.34,1.56,.64,1);",
        "  }",
        "",
        "  .card.selected .check {",
        "    opacity: 1;",
        "    transform: scale(1);",
        "  }",
        "",
        "  /* ── SUBMIT ── */",
        "  .submit-wrap {",
        "    text-align: center;",
        "    margin-top: 48px;",
        "    animation: fadeUp 0.6s ease-out 0.65s both;",
        "  }",
        "",
        "  .submit-btn {",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 22px;",
        "    letter-spacing: 3px;",
        "    color: #fff;",
        "    background: var(--amber);",
        "    border: none;",
        "    border-radius: 100px;",
        "    padding: 16px 56px;",
        "    cursor: pointer;",
        "    transition: all 0.3s ease;",
        "    box-shadow: 0 4px 24px var(--amber-glow);",
        "    position: relative;",
        "    overflow: hidden;",
        "  }",
        "",
        "  .submit-btn::after {",
        "    content: '';",
        "    position: absolute; inset: 0;",
        "    background: linear-gradient(120deg, transparent 30%, #ffffff20 50%, transparent 70%);",
        "    transform: translateX(-100%);",
        "    transition: transform 0.5s ease;",
        "  }",
        "",
        "  .submit-btn:hover {",
        "    transform: translateY(-2px) scale(1.02);",
        "    box-shadow: 0 8px 40px #ff6b3566;",
        "  }",
        "",
        "  .submit-btn:hover::after { transform: translateX(100%); }",
        "",
        "  .submit-btn:disabled {",
        "    opacity: 0.3;",
        "    cursor: not-allowed;",
        "    transform: none;",
        "    box-shadow: none;",
        "  }",
        "",
        "  .submit-btn:disabled:hover { transform: none; box-shadow: none; }",
        "  .submit-btn:disabled:hover::after { transform: translateX(-100%); }",
        "",
        "  .selection-hint {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 12px;",
        "    color: var(--cream-dim);",
        "    margin-top: 12px;",
        "    opacity: 0.6;",
        "  }",
        "",
        "  /* ── ORDER / RECEIPT PAGE ── */",
        "  .order-page {",
        "    max-width: 520px;",
        "    margin: 0 auto;",
        "  }",
        "",
        "  .receipt-card {",
        "    background: var(--surface);",
        "    border-radius: var(--radius);",
        "    padding: 40px 36px;",
        "    border: 1px solid #ffffff08;",
        "    box-shadow: 0 16px 64px #00000060;",
        "    animation: fadeUp 0.5s ease-out 0.2s both;",
        "  }",
        "",
        "  .receipt-title {",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 42px;",
        "    letter-spacing: 2px;",
        "    text-align: center;",
        "    margin-bottom: 8px;",
        "    color: var(--cream);",
        "  }",
        "",
        "  .receipt-subtitle {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 11px;",
        "    letter-spacing: 3px;",
        "    text-transform: uppercase;",
        "    text-align: center;",
        "    color: var(--cream-dim);",
        "    margin-bottom: 32px;",
        "  }",
        "",
        "  .line-item {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    align-items: baseline;",
        "    padding: 12px 0;",
        "    border-bottom: 1px dashed #ffffff10;",
        "    animation: slideIn 0.4s ease-out both;",
        "  }",
        "",
        "  .line-item:nth-child(1) { animation-delay: 0.3s; }",
        "  .line-item:nth-child(2) { animation-delay: 0.4s; }",
        "  .line-item:nth-child(3) { animation-delay: 0.5s; }",
        "",
        "  .line-item .item-name {",
        "    font-size: 18px;",
        "    font-weight: 600;",
        "  }",
        "",
        "  .line-item .item-cat {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 10px;",
        "    letter-spacing: 2px;",
        "    text-transform: uppercase;",
        "    color: var(--cream-dim);",
        "    display: block;",
        "    margin-top: 2px;",
        "  }",
        "",
        "  .line-item .item-price {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 16px;",
        "    color: var(--gold);",
        "  }",
        "",
        "  .divider {",
        "    border: none;",
        "    border-top: 1px solid #ffffff12;",
        "    margin: 20px 0;",
        "  }",
        "",
        "  .summary-line {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    padding: 6px 0;",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 14px;",
        "    color: var(--cream-dim);",
        "  }",
        "",
        "  .summary-line.discount { color: var(--green); }",
        "",
        "  .total-line {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    padding: 20px 0 0;",
        "    margin-top: 12px;",
        "    border-top: 2px solid var(--amber);",
        "  }",
        "",
        "  .total-line .label {",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 28px;",
        "    letter-spacing: 2px;",
        "  }",
        "",
        "  .total-line .amount {",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 28px;",
        "    letter-spacing: 1px;",
        "    color: var(--amber);",
        "    text-shadow: 0 0 20px var(--amber-glow);",
        "  }",
        "",
        "  .discount-badge {",
        "    text-align: center;",
        "    margin-top: 20px;",
        "    animation: pulse 2s ease-in-out infinite;",
        "  }",
        "",
        "  .discount-badge span {",
        "    font-family: 'IBM Plex Mono', monospace;",
        "    font-size: 11px;",
        "    letter-spacing: 2px;",
        "    text-transform: uppercase;",
        "    color: var(--green);",
        "    border: 1px solid #4ecb7133;",
        "    padding: 6px 16px;",
        "    border-radius: 100px;",
        "    background: #4ecb7108;",
        "  }",
        "",
        "  .actions {",
        "    display: flex;",
        "    gap: 12px;",
        "    margin-top: 32px;",
        "    animation: fadeUp 0.5s ease-out 0.7s both;",
        "  }",
        "",
        "  .actions a {",
        "    flex: 1;",
        "    text-align: center;",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 16px;",
        "    letter-spacing: 2px;",
        "    text-decoration: none;",
        "    padding: 14px 24px;",
        "    border-radius: 100px;",
        "    transition: all 0.25s ease;",
        "  }",
        "",
        "  .actions .btn-primary {",
        "    background: var(--amber);",
        "    color: #fff;",
        "    box-shadow: 0 4px 20px var(--amber-glow);",
        "  }",
        "",
        "  .actions .btn-primary:hover {",
        "    transform: translateY(-2px);",
        "    box-shadow: 0 6px 30px #ff6b3566;",
        "  }",
        "",
        "  .actions .btn-ghost {",
        "    border: 1px solid #ffffff15;",
        "    color: var(--cream-dim);",
        "  }",
        "",
        "  .actions .btn-ghost:hover {",
        "    border-color: var(--cream-dim);",
        "    color: var(--cream);",
        "    transform: translateY(-2px);",
        "  }",
        "",
        "  /* ── ERROR ── */",
        "  .error-card {",
        "    text-align: center;",
        "    padding: 60px 40px;",
        "    background: var(--surface);",
        "    border-radius: var(--radius);",
        "    border: 1px solid #ff444420;",
        "    animation: shake 0.5s ease-out;",
        "  }",
        "",
        "  .error-icon {",
        "    font-size: 48px;",
        "    margin-bottom: 20px;",
        "  }",
        "",
        "  .error-card h2 {",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 32px;",
        "    letter-spacing: 2px;",
        "    color: var(--red);",
        "    margin-bottom: 12px;",
        "  }",
        "",
        "  .error-card p {",
        "    color: var(--cream-dim);",
        "    font-size: 16px;",
        "    margin-bottom: 24px;",
        "  }",
        "",
        "  .error-card a {",
        "    font-family: 'Bebas Neue', sans-serif;",
        "    font-size: 16px;",
        "    letter-spacing: 2px;",
        "    color: var(--amber);",
        "    text-decoration: none;",
        "    border: 1px solid var(--amber-glow);",
        "    padding: 12px 32px;",
        "    border-radius: 100px;",
        "    transition: all 0.25s ease;",
        "  }",
        "",
        "  .error-card a:hover {",
        "    background: var(--amber);",
        "    color: #fff;",
        "  }",
        "",
        "  /* ── KEYFRAMES ── */",
        "  @keyframes fadeDown {",
        "    from { opacity: 0; transform: translateY(-20px); }",
        "    to { opacity: 1; transform: translateY(0); }",
        "  }",
        "",
        "  @keyframes fadeUp {",
        "    from { opacity: 0; transform: translateY(24px); }",
        "    to { opacity: 1; transform: translateY(0); }",
        "  }",
        "",
        "  @keyframes slideIn {",
        "    from { opacity: 0; transform: translateX(-12px); }",
        "    to { opacity: 1; transform: translateX(0); }",
        "  }",
        "",
        "  @keyframes pulse {",
        "    0%, 100% { opacity: 1; }",
        "    50% { opacity: 0.6; }",
        "  }",
        "",
        "  @keyframes shake {",
        "    0%, 100% { transform: translateX(0); }",
        "    20% { transform: translateX(-8px); }",
        "    40% { transform: translateX(8px); }",
        "    60% { transform: translateX(-4px); }",
        "    80% { transform: translateX(4px); }",
        "  }",
        "",
        "  /* ── RESPONSIVE ── */",
        "  @media (max-width: 600px) {",
        "    .container { padding: 24px 16px 60px; }",
        "    .receipt-card { padding: 28px 20px; }",
        "    .cards { grid-template-columns: 1fr; }",
        "    .actions { flex-direction: column; }",
        "  }",
        "</style>"
    );

    private static final String SCRIPT = String.join("\n",
        "<script>",
        "  document.addEventListener('DOMContentLoaded', () => {",
        "    document.querySelectorAll('.category').forEach(cat => {",
        "      const group = cat.dataset.group;",
        "      const hidden = cat.querySelector('input[type=hidden]');",
        "      cat.querySelectorAll('.card').forEach(card => {",
        "        card.addEventListener('click', () => {",
        "          cat.querySelectorAll('.card').forEach(c => c.classList.remove('selected'));",
        "          card.classList.add('selected');",
        "          hidden.value = card.dataset.value;",
        "          checkReady();",
        "        });",
        "      });",
        "    });",
        "",
        "    function checkReady() {",
        "      const inputs = document.querySelectorAll('input[type=hidden]');",
        "      const allPicked = [...inputs].every(i => i.value !== '');",
        "      const btn = document.querySelector('.submit-btn');",
        "      const hint = document.querySelector('.selection-hint');",
        "      btn.disabled = !allPicked;",
        "      if (allPicked) {",
        "        const prices = document.querySelectorAll('.card.selected .price');",
        "        let sum = 0;",
        "        prices.forEach(p => sum += parseFloat(p.textContent.replace('$', '')));",
        "        hint.textContent = 'Subtotal: $' + sum.toFixed(2) + (sum > 20 ? ' \\u2014 10% discount applies!' : '');",
        "        hint.style.color = sum > 20 ? '#4ecb71' : '';",
        "      } else {",
        "        const remaining = [...inputs].filter(i => i.value === '').length;",
        "        hint.textContent = remaining + ' selection' + (remaining > 1 ? 's' : '') + ' remaining';",
        "        hint.style.color = '';",
        "      }",
        "    }",
        "    checkReady();",
        "  });",
        "</script>"
    );

    public static void main(String[] args) {

        port(4567);

        Menu menu = new Menu();

        get("/", (req, res) -> {
            res.type("text/html");

            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html><html lang='en'><head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            html.append("<title>Night Bite \u2014 Street Food</title>");
            html.append(STYLES);
            html.append("</head><body>");
            html.append("<div class='grain'></div>");
            html.append("<div class='container'>");

            // Header
            html.append("<div class='header'>");
            html.append("<div class='tag'>Est. 2025 \u2022 NYC</div>");
            html.append("<h1>NIGHT <span>BITE</span></h1>");
            html.append("<p>Fresh eats from the curb to your hands</p>");
            html.append("</div>");

            // Form
            html.append("<form action='/order' method='get'>");

            // Salads
            html.append("<div class='category' data-group='salad'>");
            html.append("<input type='hidden' name='salad' value=''>");
            html.append("<div class='category-label'>\u2022 Pick Your Greens</div>");
            html.append("<div class='cards'>");
            html.append(menuCard("salad_greek", "Greek Salad", 7.50));
            html.append(menuCard("salad_caesar", "Caesar Salad", 7.25));
            html.append(menuCard("salad_garden", "Garden Salad", 6.75));
            html.append(menuCard("salad_cobb", "Cobb Salad", 8.50));
            html.append(menuCard("salad_spinach", "Spinach Salad", 7.75));
            html.append("</div></div>");

            // Sandwiches
            html.append("<div class='category' data-group='sandwich'>");
            html.append("<input type='hidden' name='sandwich' value=''>");
            html.append("<div class='category-label'>\u2022 Choose Your Stack</div>");
            html.append("<div class='cards'>");
            html.append(menuCard("sandwich_turkey", "Turkey Club", 9.50));
            html.append(menuCard("sandwich_veggie", "Veggie Wrap", 8.75));
            html.append(menuCard("sandwich_tuna", "Tuna Melt", 9.25));
            html.append(menuCard("sandwich_blt", "Classic BLT", 9.00));
            html.append(menuCard("sandwich_chicken", "Chicken Parm", 10.25));
            html.append("</div></div>");

            // Drinks
            html.append("<div class='category' data-group='drink'>");
            html.append("<input type='hidden' name='drink' value=''>");
            html.append("<div class='category-label'>\u2022 Grab A Drink</div>");
            html.append("<div class='cards'>");
            html.append(menuCard("drink_water", "Still Water", 1.50));
            html.append(menuCard("drink_lemonade", "Fresh Lemonade", 2.75));
            html.append(menuCard("drink_icedtea", "Iced Tea", 2.50));
            html.append(menuCard("drink_soda", "Cola", 2.25));
            html.append(menuCard("drink_juice", "Orange Juice", 3.50));
            html.append("</div></div>");

            // Submit
            html.append("<div class='submit-wrap'>");
            html.append("<button class='submit-btn' type='submit' disabled>VIEW ORDER</button>");
            html.append("<div class='selection-hint'>3 selections remaining</div>");
            html.append("</div>");

            html.append("</form>");
            html.append("</div>");
            html.append(SCRIPT);
            html.append("</body></html>");
            return html.toString();
        });

        // Order summary page
        get("/order", (req, res) -> {
            res.type("text/html");

            String saladCode = req.queryParams("salad");
            String sandwichCode = req.queryParams("sandwich");
            String drinkCode = req.queryParams("drink");

            if (saladCode == null || sandwichCode == null || drinkCode == null ||
                saladCode.equals("") || sandwichCode.equals("") || drinkCode.equals("")) {
                return errorPage("Hold up", "You need to pick one from each category before ordering.");
            }

            MenuItem salad = menu.getItem(saladCode);
            MenuItem sandwich = menu.getItem(sandwichCode);
            MenuItem drink = menu.getItem(drinkCode);

            if (salad == null || sandwich == null || drink == null) {
                return errorPage("Something broke", "One of your picks didn't match anything on the menu.");
            }

            double subtotal = salad.getPrice() + sandwich.getPrice() + drink.getPrice();
            double discount = subtotal > 20.0 ? subtotal * 0.10 : 0;
            double total = subtotal - discount;

            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html><html lang='en'><head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            html.append("<title>Your Order \u2014 Night Bite</title>");
            html.append(STYLES);
            html.append("</head><body>");
            html.append("<div class='grain'></div>");
            html.append("<div class='container order-page'>");

            html.append("<div class='header'>");
            html.append("<div class='tag'>Order Summary</div>");
            html.append("<h1>YOUR <span>ORDER</span></h1>");
            html.append("</div>");

            html.append("<div class='receipt-card'>");

            // Items
            html.append(lineItem("Greens", salad.getName(), salad.getPrice()));
            html.append(lineItem("Stack", sandwich.getName(), sandwich.getPrice()));
            html.append(lineItem("Drink", drink.getName(), drink.getPrice()));

            html.append("<hr class='divider'>");

            // Summary
            html.append("<div class='summary-line'><span>Subtotal</span><span>").append(money(subtotal)).append("</span></div>");

            if (discount > 0) {
                html.append("<div class='summary-line discount'><span>10% Discount</span><span>\u2212").append(money(discount)).append("</span></div>");
            }

            // Total
            html.append("<div class='total-line'>");
            html.append("<span class='label'>TOTAL</span>");
            html.append("<span class='amount'>").append(money(total)).append("</span>");
            html.append("</div>");

            if (discount > 0) {
                html.append("<div class='discount-badge'><span>You saved ").append(money(discount)).append("</span></div>");
            }

            html.append("</div>"); // receipt-card

            // Actions
            html.append("<div class='actions'>");
            html.append("<a class='btn-ghost' href='/'>Back to Menu</a>");
            html.append("<a class='btn-primary' href='/receipt?salad=").append(saladCode)
                .append("&sandwich=").append(sandwichCode)
                .append("&drink=").append(drinkCode).append("'>Full Receipt</a>");
            html.append("</div>");

            html.append("</div>");
            html.append("</body></html>");
            return html.toString();
        });

        // Full receipt with tax
        get("/receipt", (req, res) -> {
            res.type("text/html");

            String saladCode = req.queryParams("salad");
            String sandwichCode = req.queryParams("sandwich");
            String drinkCode = req.queryParams("drink");

            if (saladCode == null || sandwichCode == null || drinkCode == null ||
                saladCode.equals("") || sandwichCode.equals("") || drinkCode.equals("")) {
                return errorPage("Missing info", "We lost your order details somewhere. Try again.");
            }

            MenuItem salad = menu.getItem(saladCode);
            MenuItem sandwich = menu.getItem(sandwichCode);
            MenuItem drink = menu.getItem(drinkCode);

            if (salad == null || sandwich == null || drink == null) {
                return errorPage("Something broke", "One of your picks didn't match anything on the menu.");
            }

            double subtotal = salad.getPrice() + sandwich.getPrice() + drink.getPrice();
            double discount = subtotal > 20.0 ? subtotal * 0.10 : 0;
            double afterDiscount = subtotal - discount;
            double tax = afterDiscount * 0.08875;
            double finalTotal = afterDiscount + tax;

            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html><html lang='en'><head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            html.append("<title>Receipt \u2014 Night Bite</title>");
            html.append(STYLES);
            html.append("</head><body>");
            html.append("<div class='grain'></div>");
            html.append("<div class='container order-page'>");

            html.append("<div class='header'>");
            html.append("<div class='tag'>Thank You</div>");
            html.append("<h1>YOUR <span>RECEIPT</span></h1>");
            html.append("</div>");

            html.append("<div class='receipt-card'>");
            html.append("<div class='receipt-title'>NIGHT BITE</div>");
            html.append("<div class='receipt-subtitle'>Fresh Eats \u2022 NYC</div>");

            // Items
            html.append(lineItem("Greens", salad.getName(), salad.getPrice()));
            html.append(lineItem("Stack", sandwich.getName(), sandwich.getPrice()));
            html.append(lineItem("Drink", drink.getName(), drink.getPrice()));

            html.append("<hr class='divider'>");

            html.append("<div class='summary-line'><span>Subtotal</span><span>").append(money(subtotal)).append("</span></div>");

            if (discount > 0) {
                html.append("<div class='summary-line discount'><span>10% Discount</span><span>\u2212").append(money(discount)).append("</span></div>");
                html.append("<div class='summary-line'><span>After Discount</span><span>").append(money(afterDiscount)).append("</span></div>");
            }

            html.append("<div class='summary-line'><span>Tax (8.875%)</span><span>").append(money(tax)).append("</span></div>");

            // Final total
            html.append("<div class='total-line'>");
            html.append("<span class='label'>FINAL TOTAL</span>");
            html.append("<span class='amount'>").append(money(finalTotal)).append("</span>");
            html.append("</div>");

            if (discount > 0) {
                html.append("<div class='discount-badge'><span>You saved ").append(money(discount)).append("</span></div>");
            }

            html.append("</div>"); // receipt-card

            // Actions
            html.append("<div class='actions'>");
            html.append("<a class='btn-ghost' href='/'>New Order</a>");
            html.append("<a class='btn-primary' href='javascript:window.print()'>Print Receipt</a>");
            html.append("</div>");

            html.append("</div>");
            html.append("</body></html>");
            return html.toString();
        });

        System.out.println("Server started at http://localhost:4567");
    }

    private static String menuCard(String code, String name, double price) {
        return "<div class='card' data-value='" + code + "'>"
             + "<span class='name'>" + name + "</span>"
             + "<span class='price'>" + money(price) + "</span>"
             + "<div class='check'>\u2713</div>"
             + "</div>";
    }

    private static String lineItem(String category, String name, double price) {
        return "<div class='line-item'>"
             + "<div><span class='item-name'>" + name + "</span>"
             + "<span class='item-cat'>" + category + "</span></div>"
             + "<span class='item-price'>" + money(price) + "</span>"
             + "</div>";
    }

    private static String errorPage(String title, String message) {
        return "<!DOCTYPE html><html lang='en'><head>"
             + "<meta charset='UTF-8'>"
             + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
             + "<title>Error \u2014 Night Bite</title>"
             + STYLES
             + "</head><body>"
             + "<div class='grain'></div>"
             + "<div class='container order-page'>"
             + "<div class='header'>"
             + "<h1>NIGHT <span>BITE</span></h1>"
             + "</div>"
             + "<div class='error-card'>"
             + "<div class='error-icon'>\u26A0</div>"
             + "<h2>" + title + "</h2>"
             + "<p>" + message + "</p>"
             + "<a href='/'>Back to Menu</a>"
             + "</div></div></body></html>";
    }

    private static String money(double value) {
        return String.format("$%.2f", value);
    }
}
