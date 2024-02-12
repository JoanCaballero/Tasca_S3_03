package org.example;

import org.example.Entities.Decoration;
import org.example.Entities.Flower;
import org.example.Entities.Product;
import org.example.Entities.Tree;

public class ProductFactory {
    // Al final si que he afegit aquesta classe perque parlant amb l'Oriol aquesta classe facilita de cara
    // a un possible futur del programa afegir altres tipus de productes sense alterar la resta del programa

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
