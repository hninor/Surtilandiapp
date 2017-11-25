package unal.edu.co.surtilandiapp.core.data.entities;

import java.util.HashMap;

/**
 * Created by hnino on 24/11/2017.
 */

public class Category {

    private String name;
    private HashMap<String, Boolean> products;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Boolean> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Boolean> products) {
        this.products = products;
    }
}
