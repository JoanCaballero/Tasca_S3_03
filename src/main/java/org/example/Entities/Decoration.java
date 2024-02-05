package org.example.Entities;

public class Decoration extends Product{

    private boolean wood;

    public Decoration(String name, double price, boolean wood) {
        super(name, price);
        this.wood = wood;
        super.setType("Decoration");
    }

    public Decoration(int id, String name, double price, boolean wood){
        super(id, name, price);
        this.wood = wood;
        super.setType("Decoration");
    }

    public boolean isWood(){
        return this.wood;
    }

    public void setWood(boolean wood){
        this.wood = wood;
    }

    public String toString(){
        String material = wood ? "\nMade of: Wood" : "\nMade of: Plastic";
        return "ID: " + this.getId() + "\nName: " + this.getName() + "\nPrice: " +
                this.getPrice() + material;
    }
}
