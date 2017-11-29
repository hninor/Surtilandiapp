package unal.edu.co.surtilandiapp.core.data.entities;

/**
 * Created by hnino on 24/11/2017.
 */

public class ProductStore {

    private boolean disponible;
    private String precio;
    private String nombre;
    private String categoria;
    private long timestamp;

    public ProductStore() {
    }

    public ProductStore(boolean disponible, String precio, long timestamp) {
        this.disponible = disponible;
        this.precio = precio;
        this.timestamp = timestamp;
    }

    public ProductStore(boolean disponible, String precio, String nombre, String categoria, long timestamp) {
        this.disponible = disponible;
        this.precio = precio;
        this.nombre = nombre;
        this.categoria = categoria;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
