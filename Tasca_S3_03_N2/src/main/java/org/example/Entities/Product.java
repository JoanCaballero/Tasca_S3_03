package org.example.Entities;

public abstract class Product {
    protected static int idCounter = 1;
    protected int id;
    protected String name, type;
    protected double price;

    public Product(int id,String name, double price){
        this.id = id;
        idCounter = ++id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price) {
        id = idCounter;
        idCounter++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
    
    
}
