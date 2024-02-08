package org.example;

import org.example.Entities.Product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private static int idCounter = 1;
    private final int id;
    private List<Product> products;

    private double price;
    private String productString;

    public Ticket(int id, String productString, double price){
        this.id = id;
        idCounter = ++id;
        this.productString = productString;
        this.price = price;
    }

    public Ticket(){
        id = idCounter;
        idCounter++;
        products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getPrice() {
        return price;
    }



    public void addProduct(Product p){
        products.add(p);
        price += p.getPrice();
    }

    public void removeProduct(Product p){
        if(products.contains(p)){
            products.remove(p);
            price-= p.getPrice();
        }
    }

    public String toString() {

        if(products == null){
            return "ID = " + this.id + " | [" + this.productString + "] | TOTAL: " +
                    ((float) Math.round(this.price * 100) / 100) + "€";
        }else {
            return TicketStringBuilder();
        }
    }

    public String TicketStringBuilder(){
        StringBuilder stringBuilder = new StringBuilder();
        setProductString();
        stringBuilder.append("ID = ").append(this.id).append(" | [").append(this.productString);

        stringBuilder.append("] | TOTAL: ").append(((float) Math.round(this.price * 100) / 100)).append("€");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        stringBuilder.append(" Fecha de la compra: ").append(simpleDateFormat.format(timestamp));
//        stringBuilder.append(timestamp);
        return stringBuilder.toString();
    }

    public void setProductString(){
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < products.size(); i++) {
                stringBuilder.append(products.get(i).getName());
                if (i < products.size() - 1) {
                    stringBuilder.append(", ");
                }
       }

     productString = stringBuilder.toString();
    }
}
