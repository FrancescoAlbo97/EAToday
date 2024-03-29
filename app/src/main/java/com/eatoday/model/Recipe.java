package com.eatoday.model;

import com.eatoday.util.Constant;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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

    public Recipe(String name, String time, String difficulty, String type, String description, ArrayList<Ingredient> ingredients ) throws UnsupportedEncodingException {
        this.id = setId();
        this.name = name;
        this.time = time  + "  min";
        this.difficulty = difficulty;
        this.type = type;
        this.description = description;
        this.imageUrl = createImageUrlByName(name);
        this.ingredients = ingredients;
        this.price = setPrice(ingredients);
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

    private String setPrice(ArrayList<Ingredient> ingredients){
        float sum = 0;
        for (int i=0; i < ingredients.size(); i++){
            String s = ingredients.get(i).getPrice().replaceAll("€","").trim();
            sum += Float.parseFloat(s);
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(sum) + " €";
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

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
