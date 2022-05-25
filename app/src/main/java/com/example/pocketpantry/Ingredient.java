package com.example.pocketpantry;

import java.util.ArrayList;

public class Ingredient {
    private String name;
    private int recipeDbId;

    public Ingredient(String name, int recipeDbId) {
        this.name = name;
        this.recipeDbId = recipeDbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecipeDbId() {
        return recipeDbId;
    }

    public void setRecipeDbId(int recipeDbId) {
        this.recipeDbId = recipeDbId;
    }
}
