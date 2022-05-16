package com.example.pocketpantry;

import java.util.ArrayList;

public class Recipe {

    private ArrayList<PantryItem> ingredients;
    private int servingSize;

    public Recipe(ArrayList<PantryItem> ingredients, int servingSize) {
        this.ingredients = ingredients;
        this.servingSize = servingSize;
    }

    public ArrayList<PantryItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<PantryItem> ingredients) {
        this.ingredients = ingredients;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }
}
