package com.example.pocketpantry;

import java.util.ArrayList;

public class Recipe {

    private ArrayList<Ingredient> ingredients;
    private int servingSize;
    private int dbId;
    private String name;

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public Recipe(int dbId, ArrayList<Ingredient> ingredients, int servingSize, String name) {
        this.ingredients = ingredients;
        this.servingSize = servingSize;
        this.dbId = dbId;
        this.name = name;

    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }
}
