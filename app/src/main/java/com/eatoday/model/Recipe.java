package com.eatoday.model;

import com.eatoday.util.Constant;

import java.util.ArrayList;

public class Recipe {
    private static int counter;
    private int id;
    private String name;
    private String time;
    private String difficulty;
    private String price;
    private String type;
    private String description;
    private String imageUrl;
    private ArrayList<Ingredient> ingredients;

    public Recipe(String name) {
        this.id = setId();
        this.name = name;
        this.time = null;
        this.difficulty = null;
        this.price = null;
        this.type = null;
        this.description = null;
        this.imageUrl = createImageUrlByName(name);
    }

    public Recipe(String name, String time, String difficulty, String type, String description, ArrayList<Ingredient> ingredients ) {
        this.id = setId();
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
        this.type = type;
        this.description = description;
        this.imageUrl = createImageUrlByName(name);
        this.ingredients = ingredients;
        //TODO Prezzo calcolato su ingredienti
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    private String createImageUrlByName(String name){
        String url = Constant.IMAGES_PATH + name.replaceAll(" ","_") + Constant.PNG;
        return  url;
    }
}
