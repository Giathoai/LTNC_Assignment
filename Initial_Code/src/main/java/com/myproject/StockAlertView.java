package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        double currentPrice = stockPrice.getAvgPrice();
        String stockCode = stockPrice.getCode();

        Double lastPrice = lastAlertedPrices.get(stockCode);

        if (lastPrice == null || Math.abs(currentPrice - lastPrice) >= 0.05) {
            if (currentPrice > alertThresholdHigh) {
                alertAbove(stockCode, currentPrice);
                lastAlertedPrices.put(stockCode, currentPrice);
            } else if (currentPrice < alertThresholdLow) {
                alertBelow(stockCode, currentPrice);
                lastAlertedPrices.put(stockCode, currentPrice);
            }
        }
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }
}
