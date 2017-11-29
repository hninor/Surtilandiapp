package unal.edu.co.surtilandiapp.core.data.entities;

import java.util.HashMap;

/**
 * Created by hnino on 29/11/2017.
 */

public class Product {

    private String name;
    private HashMap<String, Boolean> categories;

    public Product() {
    }

    public Product(String name, HashMap<String, Boolean> categories) {
        this.name = name;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Boolean> getCategories() {
        return categories;
    }

    public void setCategories(HashMap<String, Boolean> categories) {
        this.categories = categories;
    }
}
