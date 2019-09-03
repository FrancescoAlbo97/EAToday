package com.eatoday.model;

public class Recipe {
    private static int counter;
    private int id;
    private String name;
    private String time;
    private String difficult;
    private String price;
    private String type;
    private String description;
    private String imageUrl;

    public Recipe(String name) {
        this.id = setId();
        this.name = name;
        this.time = null;
        this.difficult = null;
        this.price = null;
        this.type = null;
        this.description = null;
    }

    public Recipe(String name, String time, String difficult, String price, String type, String description, String imageUrl) {
        this.id = setId();
        this.name = name;
        this.time = time;
        this.difficult = difficult;
        this.price = price;
        this.type = type;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    private int setId() {
        this.id = counter;
        counter++;
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
