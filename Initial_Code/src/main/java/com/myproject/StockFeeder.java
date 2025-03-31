package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if(instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        for(Stock s : stockList) {
            if(s.getCode().equals(stock.getCode())) {
                stockList.remove(s);
                return;
            }
        }
        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        boolean stockExists = stockList.stream().anyMatch(s -> s.getCode().equals(code));
        if (!stockExists) {
            Logger.errorRegister(code);
            return;
        }
        if(viewers.containsKey(code)) {
            List<StockViewer> stockViewers = viewers.get(code);
            if(stockViewers.contains(stockViewer)) {
                Logger.errorRegister(code);
                return;
            }
            stockViewers.add(stockViewer);
        }else{
            List<StockViewer> stockViewers = new ArrayList<>();
            stockViewers.add(stockViewer);
            viewers.put(code, stockViewers);   
        }
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        if (!viewers.containsKey(code)) {
            Logger.errorUnregister(code);
            return;
        }

        List<StockViewer> stockViewers = viewers.get(code);
        stockViewers.remove(stockViewer);
        if (stockViewers.isEmpty()) {
            viewers.remove(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        String code = stockPrice.getCode();
        if (viewers.containsKey(code)) {
            for (StockViewer viewer : viewers.get(code)) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}
