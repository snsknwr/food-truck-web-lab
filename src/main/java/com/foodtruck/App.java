package com.foodtruck;

import static spark.Spark.*;

public class App {

    private static final String STYLES = String.join("\n",
        "<style>",
        "  @import url('https://fonts.googleapis.com/css2?family=Sora:wght@300;400;500;600;700&family=JetBrains+Mono:wght@400;500&display=swap');",
        "",
        "  :root {",
        "    --bg: #ffffff;",
        "    --bg-soft: #f8f8fa;",
        "    --surface: #ffffff;",
        "    --surface-hover: #f4f4f6;",
        "    --border: #e8e8ec;",
        "    --border-light: #f0f0f3;",
        "    --text: #0c0c0e;",
        "    --text-secondary: #6e6e78;",
        "    --text-tertiary: #9d9da7;",
        "    --brand: #3d5afe;",
        "    --brand-soft: #3d5afe12;",
        "    --brand-hover: #304ffe;",
        "    --green: #00c853;",
        "    --green-soft: #00c85312;",
        "    --red: #ff1744;",
        "    --red-soft: #ff174412;",
        "    --radius: 20px;",
        "    --radius-sm: 14px;",
        "  }",
        "",
        "  * { margin: 0; padding: 0; box-sizing: border-box; }",
        "",
        "  html { scroll-behavior: smooth; }",
        "",
        "  body {",
        "    font-family: 'Sora', -apple-system, sans-serif;",
        "    background: var(--bg);",
        "    color: var(--text);",
        "    min-height: 100vh;",
        "    overflow-x: hidden;",
        "    font-weight: 400;",
        "    -webkit-font-smoothing: antialiased;",
        "  }",
        "",
        "  .bg-glow {",
        "    position: fixed;",
        "    top: -40%;",
        "    left: 50%;",
        "    transform: translateX(-50%);",
        "    width: 900px;",
        "    height: 900px;",
        "    background: radial-gradient(circle, #3d5afe08 0%, transparent 70%);",
        "    pointer-events: none;",
        "    z-index: 0;",
        "  }",
        "",
        "  .container {",
        "    position: relative;",
        "    z-index: 1;",
        "    max-width: 960px;",
        "    margin: 0 auto;",
        "    padding: 48px 24px 96px;",
        "  }",
        "",
        "  /* ── NAV ── */",
        "  .nav {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    align-items: center;",
        "    padding: 20px 32px;",
        "    position: sticky;",
        "    top: 0;",
        "    z-index: 100;",
        "    background: rgba(255,255,255,0.8);",
        "    backdrop-filter: blur(16px);",
        "    -webkit-backdrop-filter: blur(16px);",
        "    border-bottom: 1px solid var(--border-light);",
        "  }",
        "",
        "  .nav .logo {",
        "    font-weight: 700;",
        "    font-size: 18px;",
        "    letter-spacing: -0.5px;",
        "    color: var(--text);",
        "  }",
        "",
        "  .nav .logo span {",
        "    color: var(--brand);",
        "  }",
        "",
        "  .nav .nav-link {",
        "    font-size: 13px;",
        "    font-weight: 500;",
        "    color: var(--text-secondary);",
        "    text-decoration: none;",
        "    padding: 8px 20px;",
        "    border-radius: 100px;",
        "    border: 1px solid var(--border);",
        "    transition: all 0.2s ease;",
        "  }",
        "",
        "  .nav .nav-link:hover {",
        "    color: var(--text);",
        "    border-color: var(--text);",
        "  }",
        "",
        "  /* ── HEADER ── */",
        "  .header {",
        "    text-align: center;",
        "    margin-bottom: 64px;",
        "    animation: fadeDown 0.7s cubic-bezier(.16,1,.3,1);",
        "  }",
        "",
        "  .badge {",
        "    display: inline-flex;",
        "    align-items: center;",
        "    gap: 8px;",
        "    font-size: 12px;",
        "    font-weight: 500;",
        "    letter-spacing: 0.5px;",
        "    color: var(--brand);",
        "    background: var(--brand-soft);",
        "    padding: 7px 18px;",
        "    border-radius: 100px;",
        "    margin-bottom: 28px;",
        "  }",
        "",
        "  .badge::before {",
        "    content: '';",
        "    width: 6px; height: 6px;",
        "    background: var(--brand);",
        "    border-radius: 50%;",
        "    animation: blink 2s ease-in-out infinite;",
        "  }",
        "",
        "  .header h1 {",
        "    font-size: clamp(36px, 7vw, 64px);",
        "    font-weight: 700;",
        "    letter-spacing: -2px;",
        "    line-height: 1.05;",
        "    color: var(--text);",
        "    margin-bottom: 16px;",
        "  }",
        "",
        "  .header h1 span {",
        "    background: linear-gradient(135deg, var(--brand) 0%, #7c4dff 100%);",
        "    -webkit-background-clip: text;",
        "    -webkit-text-fill-color: transparent;",
        "    background-clip: text;",
        "  }",
        "",
        "  .header .subtitle {",
        "    font-size: 17px;",
        "    font-weight: 300;",
        "    color: var(--text-secondary);",
        "    letter-spacing: -0.2px;",
        "  }",
        "",
        "  /* ── CATEGORY ── */",
        "  .category {",
        "    margin-bottom: 48px;",
        "    animation: fadeUp 0.6s cubic-bezier(.16,1,.3,1) both;",
        "  }",
        "  .category:nth-child(2) { animation-delay: 0.1s; }",
        "  .category:nth-child(3) { animation-delay: 0.2s; }",
        "  .category:nth-child(4) { animation-delay: 0.3s; }",
        "",
        "  .category-label {",
        "    font-size: 11px;",
        "    font-weight: 600;",
        "    letter-spacing: 1.5px;",
        "    text-transform: uppercase;",
        "    color: var(--text-tertiary);",
        "    margin-bottom: 16px;",
        "    padding-left: 6px;",
        "  }",
        "",
        "  .cards {",
        "    display: grid;",
        "    grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));",
        "    gap: 10px;",
        "  }",
        "",
        "  .card {",
        "    position: relative;",
        "    background: var(--bg);",
        "    border: 1.5px solid var(--border);",
        "    border-radius: var(--radius-sm);",
        "    padding: 18px 22px;",
        "    cursor: pointer;",
        "    transition: all 0.2s cubic-bezier(.16,1,.3,1);",
        "    display: flex;",
        "    justify-content: space-between;",
        "    align-items: center;",
        "    user-select: none;",
        "  }",
        "",
        "  .card:hover {",
        "    background: var(--surface-hover);",
        "    border-color: #d0d0d8;",
        "    transform: translateY(-1px);",
        "    box-shadow: 0 4px 16px rgba(0,0,0,0.04);",
        "  }",
        "",
        "  .card.selected {",
        "    border-color: var(--brand);",
        "    background: var(--brand-soft);",
        "    box-shadow: 0 0 0 3px #3d5afe14;",
        "  }",
        "",
        "  .card .name {",
        "    font-size: 15px;",
        "    font-weight: 500;",
        "    color: var(--text);",
        "    letter-spacing: -0.2px;",
        "  }",
        "",
        "  .card .price {",
        "    font-family: 'JetBrains Mono', monospace;",
        "    font-size: 13px;",
        "    font-weight: 500;",
        "    color: var(--text-secondary);",
        "    white-space: nowrap;",
        "    margin-left: 16px;",
        "  }",
        "",
        "  .card.selected .price { color: var(--brand); }",
        "",
        "  .card .check {",
        "    position: absolute;",
        "    top: -7px; right: -7px;",
        "    width: 22px; height: 22px;",
        "    background: var(--brand);",
        "    border-radius: 50%;",
        "    display: flex; align-items: center; justify-content: center;",
        "    font-size: 11px;",
        "    color: #fff;",
        "    opacity: 0;",
        "    transform: scale(0);",
        "    transition: all 0.3s cubic-bezier(.34,1.56,.64,1);",
        "    box-shadow: 0 2px 8px rgba(61,90,254,0.3);",
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
        "    animation: fadeUp 0.6s cubic-bezier(.16,1,.3,1) 0.4s both;",
        "  }",
        "",
        "  .submit-btn {",
        "    font-family: 'Sora', sans-serif;",
        "    font-size: 14px;",
        "    font-weight: 600;",
        "    letter-spacing: 0.5px;",
        "    color: #fff;",
        "    background: var(--brand);",
        "    border: none;",
        "    border-radius: 100px;",
        "    padding: 16px 48px;",
        "    cursor: pointer;",
        "    transition: all 0.25s cubic-bezier(.16,1,.3,1);",
        "    box-shadow: 0 4px 20px rgba(61,90,254,0.25);",
        "  }",
        "",
        "  .submit-btn:hover {",
        "    transform: translateY(-2px);",
        "    box-shadow: 0 8px 32px rgba(61,90,254,0.35);",
        "    background: var(--brand-hover);",
        "  }",
        "",
        "  .submit-btn:active { transform: translateY(0) scale(0.98); }",
        "",
        "  .submit-btn:disabled {",
        "    opacity: 0.35;",
        "    cursor: not-allowed;",
        "    transform: none;",
        "    box-shadow: none;",
        "  }",
        "",
        "  .submit-btn:disabled:hover { transform: none; box-shadow: none; background: var(--brand); }",
        "",
        "  .selection-hint {",
        "    font-family: 'JetBrains Mono', monospace;",
        "    font-size: 12px;",
        "    color: var(--text-tertiary);",
        "    margin-top: 14px;",
        "    transition: color 0.2s ease;",
        "  }",
        "",
        "  /* ── ORDER / RECEIPT ── */",
        "  .order-page {",
        "    max-width: 540px;",
        "    margin: 0 auto;",
        "  }",
        "",
        "  .receipt-card {",
        "    background: var(--bg);",
        "    border-radius: var(--radius);",
        "    padding: 40px 36px;",
        "    border: 1.5px solid var(--border);",
        "    box-shadow: 0 8px 40px rgba(0,0,0,0.04);",
        "    animation: fadeUp 0.5s cubic-bezier(.16,1,.3,1) 0.15s both;",
        "  }",
        "",
        "  .receipt-title {",
        "    font-size: 28px;",
        "    font-weight: 700;",
        "    letter-spacing: -1px;",
        "    text-align: center;",
        "    margin-bottom: 4px;",
        "  }",
        "",
        "  .receipt-title span { color: var(--brand); }",
        "",
        "  .receipt-subtitle {",
        "    font-family: 'JetBrains Mono', monospace;",
        "    font-size: 11px;",
        "    letter-spacing: 1px;",
        "    text-transform: uppercase;",
        "    text-align: center;",
        "    color: var(--text-tertiary);",
        "    margin-bottom: 32px;",
        "  }",
        "",
        "  .line-item {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    align-items: center;",
        "    padding: 14px 0;",
        "    border-bottom: 1px solid var(--border-light);",
        "    animation: slideIn 0.4s cubic-bezier(.16,1,.3,1) both;",
        "  }",
        "",
        "  .line-item:nth-child(1) { animation-delay: 0.25s; }",
        "  .line-item:nth-child(2) { animation-delay: 0.35s; }",
        "  .line-item:nth-child(3) { animation-delay: 0.45s; }",
        "",
        "  .line-item .item-name {",
        "    font-size: 15px;",
        "    font-weight: 500;",
        "    letter-spacing: -0.2px;",
        "  }",
        "",
        "  .line-item .item-cat {",
        "    font-family: 'JetBrains Mono', monospace;",
        "    font-size: 10px;",
        "    letter-spacing: 1px;",
        "    text-transform: uppercase;",
        "    color: var(--text-tertiary);",
        "    display: block;",
        "    margin-top: 3px;",
        "  }",
        "",
        "  .line-item .item-price {",
        "    font-family: 'JetBrains Mono', monospace;",
        "    font-size: 14px;",
        "    font-weight: 500;",
        "    color: var(--text-secondary);",
        "  }",
        "",
        "  .divider {",
        "    border: none;",
        "    border-top: 1.5px solid var(--border);",
        "    margin: 24px 0 16px;",
        "  }",
        "",
        "  .summary-line {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    padding: 6px 0;",
        "    font-family: 'JetBrains Mono', monospace;",
        "    font-size: 13px;",
        "    color: var(--text-tertiary);",
        "  }",
        "",
        "  .summary-line.discount {",
        "    color: var(--green);",
        "    font-weight: 500;",
        "  }",
        "",
        "  .total-line {",
        "    display: flex;",
        "    justify-content: space-between;",
        "    align-items: center;",
        "    padding: 24px 0 0;",
        "    margin-top: 16px;",
        "    border-top: 2px solid var(--text);",
        "  }",
        "",
        "  .total-line .label {",
        "    font-size: 13px;",
        "    font-weight: 600;",
        "    letter-spacing: 1px;",
        "    text-transform: uppercase;",
        "  }",
        "",
        "  .total-line .amount {",
        "    font-size: 28px;",
        "    font-weight: 700;",
        "    letter-spacing: -1px;",
        "    color: var(--text);",
        "  }",
        "",
        "  .discount-badge {",
        "    text-align: center;",
        "    margin-top: 20px;",
        "  }",
        "",
        "  .discount-badge span {",
        "    font-size: 12px;",
        "    font-weight: 500;",
        "    color: var(--green);",
        "    background: var(--green-soft);",
        "    padding: 6px 16px;",
        "    border-radius: 100px;",
        "  }",
        "",
        "  .actions {",
        "    display: flex;",
        "    gap: 10px;",
        "    margin-top: 32px;",
        "    animation: fadeUp 0.5s cubic-bezier(.16,1,.3,1) 0.55s both;",
        "  }",
        "",
        "  .actions a, .actions button {",
        "    flex: 1;",
        "    text-align: center;",
        "    font-family: 'Sora', sans-serif;",
        "    font-size: 13px;",
        "    font-weight: 500;",
        "    text-decoration: none;",
        "    padding: 14px 24px;",
        "    border-radius: 100px;",
        "    transition: all 0.2s cubic-bezier(.16,1,.3,1);",
        "    cursor: pointer;",
        "  }",
        "",
        "  .actions .btn-primary {",
        "    background: var(--text);",
        "    color: #fff;",
        "    border: 1.5px solid var(--text);",
        "  }",
        "",
        "  .actions .btn-primary:hover {",
        "    transform: translateY(-2px);",
        "    box-shadow: 0 6px 24px rgba(0,0,0,0.15);",
        "  }",
        "",
        "  .actions .btn-ghost {",
        "    border: 1.5px solid var(--border);",
        "    color: var(--text-secondary);",
        "    background: transparent;",
        "  }",
        "",
        "  .actions .btn-ghost:hover {",
        "    border-color: var(--text-secondary);",
        "    color: var(--text);",
        "    transform: translateY(-2px);",
        "  }",
        "",
        "  /* ── ERROR ── */",
        "  .error-card {",
        "    text-align: center;",
        "    padding: 64px 40px;",
        "    background: var(--bg);",
        "    border-radius: var(--radius);",
        "    border: 1.5px solid var(--border);",
        "    animation: shake 0.5s ease-out;",
        "  }",
        "",
        "  .error-icon { font-size: 40px; margin-bottom: 20px; }",
        "",
        "  .error-card h2 {",
        "    font-size: 22px;",
        "    font-weight: 700;",
        "    letter-spacing: -0.5px;",
        "    color: var(--red);",
        "    margin-bottom: 8px;",
        "  }",
        "",
        "  .error-card p {",
        "    color: var(--text-secondary);",
        "    font-size: 15px;",
        "    font-weight: 300;",
        "    margin-bottom: 28px;",
        "  }",
        "",
        "  .error-card a {",
        "    font-family: 'Sora', sans-serif;",
        "    font-size: 13px;",
        "    font-weight: 500;",
        "    color: var(--brand);",
        "    text-decoration: none;",
        "    border: 1.5px solid var(--brand);",
        "    padding: 12px 28px;",
        "    border-radius: 100px;",
        "    transition: all 0.2s ease;",
        "  }",
        "",
        "  .error-card a:hover {",
        "    background: var(--brand);",
        "    color: #fff;",
        "  }",
        "",
        "  /* ── PRINT ── */",
        "  @media print {",
        "    .nav, .actions, .bg-glow, .badge { display: none !important; }",
        "    .receipt-card { border: none; box-shadow: none; padding: 0; }",
        "    body { background: #fff; }",
        "    .header { margin-bottom: 24px; }",
        "    .header h1 { font-size: 24px; }",
        "    .container { padding: 0; }",
        "  }",
        "",
        "  /* ── KEYFRAMES ── */",
        "  @keyframes fadeDown {",
        "    from { opacity: 0; transform: translateY(-16px); }",
        "    to { opacity: 1; transform: translateY(0); }",
        "  }",
        "",
        "  @keyframes fadeUp {",
        "    from { opacity: 0; transform: translateY(20px); }",
        "    to { opacity: 1; transform: translateY(0); }",
        "  }",
        "",
        "  @keyframes slideIn {",
        "    from { opacity: 0; transform: translateX(-10px); }",
        "    to { opacity: 1; transform: translateX(0); }",
        "  }",
        "",
        "  @keyframes blink {",
        "    0%, 100% { opacity: 1; }",
        "    50% { opacity: 0.3; }",
        "  }",
        "",
        "  @keyframes shake {",
        "    0%, 100% { transform: translateX(0); }",
        "    20% { transform: translateX(-6px); }",
        "    40% { transform: translateX(6px); }",
        "    60% { transform: translateX(-3px); }",
        "    80% { transform: translateX(3px); }",
        "  }",
        "",
        "  /* ── RESPONSIVE ── */",
        "  @media (max-width: 640px) {",
        "    .container { padding: 32px 16px 64px; }",
        "    .receipt-card { padding: 28px 20px; }",
        "    .cards { grid-template-columns: 1fr; }",
        "    .actions { flex-direction: column; }",
        "    .nav { padding: 16px 20px; }",
        "  }",
        "</style>"
    );

    private static final String SCRIPT = String.join("\n",
        "<script>",
        "  document.addEventListener('DOMContentLoaded', () => {",
        "    document.querySelectorAll('.category').forEach(cat => {",
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
        "        hint.style.color = sum > 20 ? '#00c853' : '';",
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

    private static final String PRINT_SCRIPT = String.join("\n",
        "<script>",
        "  document.addEventListener('DOMContentLoaded', () => {",
        "    const printBtn = document.getElementById('print-btn');",
        "    if (printBtn) {",
        "      printBtn.addEventListener('click', (e) => {",
        "        e.preventDefault();",
        "        window.print();",
        "      });",
        "    }",
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
            html.append("<title>Night Bite</title>");
            html.append(STYLES);
            html.append("</head><body>");
            html.append("<div class='bg-glow'></div>");

            // Nav
            html.append("<nav class='nav'>");
            html.append("<div class='logo'>night<span>bite</span></div>");
            html.append("<a class='nav-link' href='/'>Menu</a>");
            html.append("</nav>");

            html.append("<div class='container'>");

            // Header
            html.append("<div class='header'>");
            html.append("<div class='badge'>Open Now</div>");
            html.append("<h1>Build your <span>perfect meal</span></h1>");
            html.append("<p class='subtitle'>Pick one from each category. We'll handle the rest.</p>");
            html.append("</div>");

            // Form
            html.append("<form action='/order' method='get'>");

            // Salads
            html.append("<div class='category' data-group='salad'>");
            html.append("<input type='hidden' name='salad' value=''>");
            html.append("<div class='category-label'>Greens</div>");
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
            html.append("<div class='category-label'>Sandwiches</div>");
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
            html.append("<div class='category-label'>Drinks</div>");
            html.append("<div class='cards'>");
            html.append(menuCard("drink_water", "Still Water", 1.50));
            html.append(menuCard("drink_lemonade", "Fresh Lemonade", 2.75));
            html.append(menuCard("drink_icedtea", "Iced Tea", 2.50));
            html.append(menuCard("drink_soda", "Cola", 2.25));
            html.append(menuCard("drink_juice", "Orange Juice", 3.50));
            html.append("</div></div>");

            // Submit
            html.append("<div class='submit-wrap'>");
            html.append("<button class='submit-btn' type='submit' disabled>View Order</button>");
            html.append("<div class='selection-hint'>3 selections remaining</div>");
            html.append("</div>");

            html.append("</form>");
            html.append("</div>");
            html.append(SCRIPT);
            html.append("</body></html>");
            return html.toString();
        });

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
            html.append("<div class='bg-glow'></div>");

            html.append("<nav class='nav'>");
            html.append("<div class='logo'>night<span>bite</span></div>");
            html.append("<a class='nav-link' href='/'>Back to Menu</a>");
            html.append("</nav>");

            html.append("<div class='container order-page'>");

            html.append("<div class='header'>");
            html.append("<div class='badge'>Order Summary</div>");
            html.append("<h1>Your <span>order</span></h1>");
            html.append("</div>");

            html.append("<div class='receipt-card'>");

            html.append(lineItem("Greens", salad.getName(), salad.getPrice()));
            html.append(lineItem("Sandwich", sandwich.getName(), sandwich.getPrice()));
            html.append(lineItem("Drink", drink.getName(), drink.getPrice()));

            html.append("<hr class='divider'>");

            html.append("<div class='summary-line'><span>Subtotal</span><span>").append(money(subtotal)).append("</span></div>");

            if (discount > 0) {
                html.append("<div class='summary-line discount'><span>10% Discount</span><span>\u2212").append(money(discount)).append("</span></div>");
            }

            html.append("<div class='total-line'>");
            html.append("<span class='label'>Total</span>");
            html.append("<span class='amount'>").append(money(total)).append("</span>");
            html.append("</div>");

            if (discount > 0) {
                html.append("<div class='discount-badge'><span>You saved ").append(money(discount)).append("</span></div>");
            }

            html.append("</div>");

            html.append("<div class='actions'>");
            html.append("<a class='btn-ghost' href='/'>Edit Order</a>");
            html.append("<a class='btn-primary' href='/receipt?salad=").append(saladCode)
                .append("&sandwich=").append(sandwichCode)
                .append("&drink=").append(drinkCode).append("'>Full Receipt</a>");
            html.append("</div>");

            html.append("</div>");
            html.append("</body></html>");
            return html.toString();
        });

        get("/receipt", (req, res) -> {
            res.type("text/html");

            String saladCode = req.queryParams("salad");
            String sandwichCode = req.queryParams("sandwich");
            String drinkCode = req.queryParams("drink");

            if (saladCode == null || sandwichCode == null || drinkCode == null ||
                saladCode.equals("") || sandwichCode.equals("") || drinkCode.equals("")) {
                return errorPage("Missing info", "We lost your order details. Try again.");
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
            html.append("<div class='bg-glow'></div>");

            html.append("<nav class='nav'>");
            html.append("<div class='logo'>night<span>bite</span></div>");
            html.append("<a class='nav-link' href='/'>New Order</a>");
            html.append("</nav>");

            html.append("<div class='container order-page'>");

            html.append("<div class='header'>");
            html.append("<div class='badge'>Receipt</div>");
            html.append("<h1>Thank <span>you</span></h1>");
            html.append("</div>");

            html.append("<div class='receipt-card'>");
            html.append("<div class='receipt-title'>night<span>bite</span></div>");
            html.append("<div class='receipt-subtitle'>Fresh eats \u2022 NYC</div>");

            html.append(lineItem("Greens", salad.getName(), salad.getPrice()));
            html.append(lineItem("Sandwich", sandwich.getName(), sandwich.getPrice()));
            html.append(lineItem("Drink", drink.getName(), drink.getPrice()));

            html.append("<hr class='divider'>");

            html.append("<div class='summary-line'><span>Subtotal</span><span>").append(money(subtotal)).append("</span></div>");

            if (discount > 0) {
                html.append("<div class='summary-line discount'><span>10% Discount</span><span>\u2212").append(money(discount)).append("</span></div>");
                html.append("<div class='summary-line'><span>After Discount</span><span>").append(money(afterDiscount)).append("</span></div>");
            }

            html.append("<div class='summary-line'><span>Tax (8.875%)</span><span>").append(money(tax)).append("</span></div>");

            html.append("<div class='total-line'>");
            html.append("<span class='label'>Final Total</span>");
            html.append("<span class='amount'>").append(money(finalTotal)).append("</span>");
            html.append("</div>");

            if (discount > 0) {
                html.append("<div class='discount-badge'><span>You saved ").append(money(discount)).append("</span></div>");
            }

            html.append("</div>");

            html.append("<div class='actions'>");
            html.append("<a class='btn-ghost' href='/'>New Order</a>");
            html.append("<button class='btn-primary' id='print-btn' type='button'>Print Receipt</button>");
            html.append("</div>");

            html.append("</div>");
            html.append(PRINT_SCRIPT);
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
             + "<div class='bg-glow'></div>"
             + "<nav class='nav'>"
             + "<div class='logo'>night<span>bite</span></div>"
             + "<a class='nav-link' href='/'>Menu</a>"
             + "</nav>"
             + "<div class='container order-page'>"
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
