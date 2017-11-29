package unal.edu.co.surtilandiapp.features.client.products;

import java.util.List;

/**
 * Created by USER on 16/10/2017.
 */

public class CategoryProductClient {

    private String title;
    private List<String> tags;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
