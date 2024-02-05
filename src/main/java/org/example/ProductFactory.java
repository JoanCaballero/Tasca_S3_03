package org.example;

import org.example.Entities.Decoration;
import org.example.Entities.Flower;
import org.example.Entities.Product;
import org.example.Entities.Tree;

public class ProductFactory {

    public <T> Product createProduct(String type, String name, double price, T value){
        Product product = null;
        return switch (type) {
            case "Tree" -> new Tree(name, price, (double) value);
            case "Flower" -> new Flower(name, price, (String) value);
            case "Decoration" -> new Decoration(name, price, (boolean) value);
            default -> product;
        };
    }
}
