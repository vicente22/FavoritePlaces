package cl.vicentepc.miappprueba3.models;

import java.io.Serializable;

public class Place implements Serializable{

    private String rute, address, description, date, key_place;

    public Place() {
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey_place() {
        return key_place;
    }

    public void setKey_place(String key_place) {
        this.key_place = key_place;
    }
}
