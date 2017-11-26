package unal.edu.co.surtilandiapp.core.data.entities;

/**
 * Created by hnino on 25/11/2017.
 */

public class Store {

    private String name;
    private String description;
    private String open;
    private String close;
    private String phone;
    private String email;
    private Location location;;
    public Store() {
    }

    public Store(String name, String description, String open, String close, String phone, String email, Location location) {
        this.name = name;
        this.description = description;
        this.open = open;
        this.close = close;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
