package unal.edu.co.surtilandiapp.core.data.entities;

/**
 * Created by Usuario on 27/11/2017.
 */

public class UserProfile {
    public UserProfile(String email, String telefono, String nombre) {
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
    }

    private String email;
    private String telefono;
    private String nombre;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
