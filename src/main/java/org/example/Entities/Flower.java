package org.example.Entities;

public class Flower extends Product{

    private String color;

    public Flower(String name, double price, String color) {
        super(name, price);
        this.color = color;
        super.setType("Flower");
    }

    public Flower(int id, String name, double price, String color){
        super(id, name, price);
        this.color = color;
        super.setType("Flower");
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String toString(){
        return "ID: " + this.getId() + "\nName: " + this.getName() + "\nPrice: " +
                this.getPrice() + "\nColor: " + this.getColor();
    }
}
