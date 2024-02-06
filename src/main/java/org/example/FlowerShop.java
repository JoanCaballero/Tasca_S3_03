package org.example;

import org.example.Entities.Product;

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

    public void addStock(Product product){
        productList.add(product);
        totalStockValue+= product.getPrice();
        productMap.put(product.getType(), productMap.get(product.getType()) +1);
    }

    public void removeStock(Product product){
        if(productList.contains(product)) {
            productList.remove(product);
            totalStockValue -= product.getPrice();
            productMap.put(product.getType(), productMap.get(product.getType()) - 1);
        }
    }

    public void showStock(){
        productList.forEach(System.out::println);
    }

    public void addTicket(Ticket ticket){
        ticketList.add(ticket);
        totalEarnings += ticket.getPrice();
    }

    public void removeTicket(Ticket ticket){
        if(ticketList.contains(ticket)) {
            ticketList.remove(ticket);
            totalEarnings -= ticket.getPrice();
        }
    }

    public void showTickets(){
        ticketList.forEach(System.out::println);
    }
}
