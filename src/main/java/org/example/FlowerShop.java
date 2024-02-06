package org.example;

import org.example.Entities.Decoration;
import org.example.Entities.Flower;
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
    	productMap.put(p.getType(), (int)productMap.get(p.getType()) +1);
    }
    
    public void removeProductStock(Product p) {
    	if (productList.contains(p)) {
    		productList.remove(p);
    		totalStockValue -= p.getPrice();
        	productMap.put(p.getType(), (int)productMap.get(p.getType()) -1);

    	}
    }
    
    public void productStockList() {
    	if (productList.size()==0) {
    		System.out.println("Todavia no hay productos en stock");
    	} else {
    		productList.stream().forEach(p -> System.out.println(p.toString()));
    	}
    }
    
    public void stockListQuantitiesV1() {
    	int treeQuantities=0;
     	int flowerQuantities=0;
     	int decorationQuantities=0;
     	
     	if (productList.size()==0) {
    		System.out.println("Todavia no hay productos en stock");
    	} else {
	    	for (int i = 0; i<productList.size(); i++) {
	    		if (productList.get(i) instanceof Tree) {
	    			treeQuantities++;
	    		} else if (productList.get(i) instanceof Flower) {
	    			flowerQuantities++;
	    		} else {
	    			decorationQuantities++;
	    		}
	    	}
    	
	    	System.out.println("Arboles - " + treeQuantities + " | Flores - " + flowerQuantities + " | Decoracion - " + decorationQuantities);
	    }
    }
    
    public void stockListQuantitiesV2() {
    	productMap.forEach((key, value) -> System.out.println( key + " - " + value));;
    }
    
    public void treeProductList() {
    	productList.stream().filter(p -> p instanceof Tree).forEach(p -> System.out.println(p.toString()));
    }
    
    public void flowerProductList() {
    	productList.stream().filter(p -> p instanceof Flower).forEach(p -> System.out.println(p.toString()));
    }
    
    public void decorationProductList() {
    	productList.stream().filter(p -> p instanceof Decoration).forEach(p -> System.out.println(p.toString()));
    }
    
    
}
