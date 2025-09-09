package com.example.listycity;

public class City {
    private String name;
    private String country;

    public City(String name) {
        this.name = name;
        this.country = "N/A";
    }
    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getCityName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City: " + name + "\nCountry: " + country;
    }
}
