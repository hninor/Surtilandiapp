package unal.edu.co.surtilandiapp.core.data.entities;

/**
 * Created by hnino on 25/11/2017.
 */

public class Location {

    private double lat;
    private double lng;


    public Location() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
