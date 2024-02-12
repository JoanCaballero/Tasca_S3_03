package org.example;

import org.example.Entities.Decoration;
import org.example.Entities.Flower;
import org.example.Entities.Product;
import org.example.Entities.Tree;
import org.example.Service.Program;
import org.example.txtConnection.TxtDAOImpl;

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
	private TxtDAOImpl txtDAOImpl;

    public FlowerShop(String name){
        this.name = name;
        this.productList = new ArrayList<>();
        this.ticketList = new ArrayList<>();
        this.totalEarnings = 0;
        this.totalStockValue = 0;
        this.productMap = new HashMap<>();
        initializeMap();
		txtDAOImpl = new TxtDAOImpl();
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
    	productMap.put(p.getType(), productMap.get(p.getType()) +1);
    }
    
    public void removeProductStock(Product p) {
    	if (productList.contains(p)) {
    		productList.remove(p);
    		totalStockValue -= p.getPrice();
        	productMap.put(p.getType(), productMap.get(p.getType()) -1);

    	}
    }
    
    public void productStockList() {
    	if (productList.size()==0) {
    		System.out.println("Todavia no hay productos en stock");
    	} else {
    		productList.forEach(p -> System.out.println(p.toString()));
    	}
    }
    
    public void stockListQuantitiesV2() {
    	productMap.forEach((key, value) -> System.out.println( key + " - " + value));
    }
    
    public void treeProductList() {
    	productList.stream().filter(p -> p instanceof Tree).forEach(System.out::println);
    }
    
    public void flowerProductList() {
    	productList.stream().filter(p -> p instanceof Flower).forEach(System.out::println);
    }
    
    public void decorationProductList() {
		productList.stream().filter(p -> p instanceof Decoration).forEach(System.out::println);
	}

	public void createTicket(){
		Ticket ticket = new Ticket();
		int opcio;
		do {
			opcio = Program.pedirInt("""
					1.- Añadir producto a la lista.
					2.- Eliminar producto de la lista.
					3.- Mostrar lista de productos seleccionados.
					4.- Finalizar compra.""");
			switch (opcio) {
				case 1 -> addTicketProduct(ticket);
				case 2 -> removeTicketProduct(ticket);
				case 3 -> showTicketProductList(ticket);
				case 4 -> finalizeTicket(ticket);
				default -> System.out.println("Valor introducido incorrectamente.");
			}
		}while(opcio!=4);
	}
	public void addTicketProduct(Ticket ticket) {
		System.out.println("A continuación te muestro el listado de productos a la venta:");
		productStockList();
		int indice = Program.searchProductId(Program.pedirInt("Añade el producto a tu compra mediante el ID."));
		Product p = productList.get(indice);
		if(indice != -1 && !ticket.getProducts().contains(p)){
			ticket.addProduct(p);
			System.out.println("Producto añadido correctamente.");
		}else{
			System.out.println("El producto no se ha podido añadir a la lista.");
		}
	}

	public void removeTicketProduct(Ticket ticket){
		int indice = Program.searchProductId(Program.pedirInt("Escribe el ID del producto que quieras eliminar de tu compra."));
		Product p = productList.get(indice);
		if(indice != -1 && ticket.getProducts().contains(p)) {
			ticket.removeProduct(p);
			System.out.println("Producto elminado correctamente.");
		}else{
			System.out.println("No se ha podido elminiar el producto.");
		}
	}

	public void showTicketProductList(Ticket ticket){
		ticket.getProducts().forEach(System.out::println);
	}

	public void finalizeTicket(Ticket ticket){
		for(Product p : ticket.getProducts()){
			removeProductStock(p);
		}
		if(!ticket.getProducts().isEmpty()) {
			addTicket(ticket);
			System.out.println("Compra realizada correctamente.");
			if (ticketList.size()==1) {
				txtDAOImpl.txtDbCreation();
				txtDAOImpl.insertTicket(ticket);
			} else {
				txtDAOImpl.insertTicket(ticket);
			}
		}
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
