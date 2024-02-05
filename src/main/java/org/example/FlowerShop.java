package org.example;

import org.example.Entities.Product;
import org.example.Entities.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowerShop {
    private String name;
    private List<Product> productList;
    private List<Ticket> ticketList;
    private double totalEarnings;
    private double totalStockValue;
    private Map<String, Integer> productMap;

    public FlowerShop(String name){
        this.name = name;
        this.productList = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        this.totalEarnings = 0;
        this.totalStockValue = 0;
        this.productMap = new HashMap<>();
        initializeMap();
    }

    public void initializeMap(){
        productMap.put("Tree", 0);
        productMap.put("Flower", 0);
        productMap.put("Decoration", 0);
    }

    public void add(){

    }
}
