package unal.edu.co.surtilandiapp.features.shopkeeper.products;

import java.util.List;

import unal.edu.co.surtilandiapp.core.data.entities.ProductStore;

/**
 * Created by USER on 16/10/2017.
 */

public class CategoryProduct {

    private String title;
    private List<ProductStore> tags;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductStore> getTags() {
        return tags;
    }

    public void setTags(List<ProductStore> tags) {
        this.tags = tags;
    }
}
