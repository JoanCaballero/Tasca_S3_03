package org.example.Entities;

public class Tree extends Product{

    private double height;

    public Tree(String name, double price, double height) {
        super(name, price);
        this.height = height;
        super.setType("Tree");
    }

    public Tree(int id, String name, double price, double height){
        super(id, name, price);
        this.height = height;
        super.setType("Tree");
    }

    public double getHeight(){
        return this.height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public String toString(){
        return "ID: " + this.getId() + "\nName: " + this.getName() + "\nPrice: " +
                this.getPrice() + "\nHeight: " + this.getHeight();
    }
}
