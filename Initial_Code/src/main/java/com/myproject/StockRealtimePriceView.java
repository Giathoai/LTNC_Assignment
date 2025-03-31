package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        String stockCode = stockPrice.getCode();
        double newPrice = stockPrice.getAvgPrice();
        
        // Kiểm tra giá cũ
        Double lastPrice = lastPrices.get(stockCode);
        if (lastPrice == null || Double.compare(lastPrice, newPrice) != 0) {
            logPriceChange(stockCode, lastPrice, newPrice);
            lastPrices.put(stockCode, newPrice);
        }
    }

    private void logPriceChange(String stockCode, Double oldPrice, double newPrice) {
        if (oldPrice == null) {
            System.out.printf("Stock %s initialized at price: %.2f%n", stockCode, newPrice);
        } else {
            double change = newPrice - oldPrice;
            String direction = (change > 0) ? "↑ Increased" : "↓ Decreased";
            System.out.printf("Stock %s: %s from %.2f to %.2f (%.2f)%n",
                    stockCode, direction, oldPrice, newPrice, change);
        }
    }
}
