package com.eatoday.model;

import java.util.ArrayList;

public class RecipeCollection {
    public static ArrayList<Recipe> recipesList;
    public static ArrayList<Recipe> startRecipesList;
    public static ArrayList<Ingredient> ingredientsList;

    public static ArrayList<Recipe> searchRecipesByName(String s){
        ArrayList<Recipe> arrayList = new ArrayList<>();
        for(Recipe recipe : recipesList){
            if(recipe.getName().substring(0,s.length()).equals(s)){
                arrayList.add(recipe);
            }
        }
        return arrayList;
    }
}
