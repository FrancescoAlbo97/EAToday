package com.eatoday.model;

import java.text.DecimalFormat;

public class Ingredient {

    private int id;
    private String name;
    private String unit;
    private String availability;
    private String price;
    private String store;

    public Ingredient(String id, String name, String unit, String availability, String price, String store) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.unit = unit;
        this.availability = availability;
        this.price = setPriceValue(price);
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String setPriceValue(String price) {
        float p = Float.parseFloat(price);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(p) + " €";
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return name + " " + store;
    }

    public String getDescriptionToString(){
        return "Al costo di " + price + "\nper "+ unit + " di " + name + "\npresso il " + store;
    }
}
