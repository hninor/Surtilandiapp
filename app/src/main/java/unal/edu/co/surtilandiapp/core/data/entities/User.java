package unal.edu.co.surtilandiapp.core.data.entities;

/**
 * Created by hnino on 29/11/2017.
 */

public class User {

    private String rol;
    private Store store;
    private String tienda;

    public User() {
    }

    public User(String rol, Store store) {
        this.rol = rol;
        this.store = store;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User(String rol, Store store, String tienda) {
        this.rol = rol;
        this.store = store;
        this.tienda = tienda;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }
}
