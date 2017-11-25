package unal.edu.co.surtilandiapp.core.data.entities;

/**
 * Created by hnino on 24/11/2017.
 */

public class ProductStore {

    private boolean disponible;
    private String precio;
    private long timestamp;

    public ProductStore() {
    }

    public ProductStore(boolean disponible, String precio, long timestamp) {
        this.disponible = disponible;
        this.precio = precio;
        this.timestamp = timestamp;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
