package org.example;

import org.example.Entities.Product;
import org.example.Entities.Tree;

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
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public double getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public double getTotalStockValue() {
		return totalStockValue;
	}

	public void setTotalStockValue(double totalStockValue) {
		this.totalStockValue = totalStockValue;
	}

	public Map<String, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<String, Integer> productMap) {
		this.productMap = productMap;
	}

	public void initializeMap(){
        productMap.put("Tree", 0);
        productMap.put("Flower", 0);
        productMap.put("Decoration", 0);
    }

    public void addProductStock(Product p){
    	productList.add(p);
    	totalStockValue += p.getPrice();
    	// productMap.put 
    }
    
    public void removeProductStock(Product p) {
    	if (productList.contains(p)) {
    		productList.remove(p);
    		totalStockValue -= p.getPrice();
    		// productMap
    	}
    }
    
    public void productStockList() {
    	productList.stream().forEach(p -> System.out.println(p.toString()));
    }
    
    public void treeProductList() {
    	productList.stream().filter(p -> p instanceof Tree).forEach(System.out::println);
    }
    
    
}
