/**********************************************************************
 PRESENTER Class
 This class handles interactions between Models and Views.

 To Do:
 1. Functions in the Presenter interface pertaining to recipes are
 stubs.
 **********************************************************************/
package com.example.pocketpantry;

import android.content.Context;
import java.util.ArrayList;

public class Presenter implements Contract.Presenter{
    //This object gives us access to the model
    private Context context;

    //Non-default constructor initializes databaseHelper object with context.
    //'context' is the Activity where 'this' was created
    public Presenter(Context context) {
        this.context = context;
    }
    //stub function
    @Override
    public void onClickCreateRecipe(ArrayList<String> ingredients, int servingSize, String name) {
        RecipeDatabaseHelper databaseHelper = new RecipeDatabaseHelper(context);
        databaseHelper.addOne(ingredients, servingSize, name);
    }

    //stub function
    @Override
    public void onClickReadRecipe(int _id) {

    }
    //stub function
    @Override
    public void onClickUpdateRecipe(Recipe recipe) {

    }

    @Override
    public void onClickDeleteRecipe(int _id) {

    }

    //stub function
    @Override
    public ArrayList<Recipe> showAllRecipes() {
        ArrayList<Recipe> list = new ArrayList<Recipe>();
        return list;
    }
    //calls databaseHelper.addOne, return true if successful
    @Override
    public boolean onClickCreatePantryItem(String name, int quantity, float weight) {
        PantryDatabaseHelper databaseHelper = new PantryDatabaseHelper(context);
        return databaseHelper.addOne(name, quantity, weight);
    }
    //calls databaseHelper.updateItem
    @Override
    public void onClickUpdatePantryItem(PantryItem pantryItem) {
        PantryDatabaseHelper databaseHelper = new PantryDatabaseHelper(context);
        databaseHelper.updateItem(pantryItem);
    }
    //calls databaseHelper.deleteItem
    @Override
    public void onClickDeletePantryItem(int _id) {
        PantryDatabaseHelper databaseHelper = new PantryDatabaseHelper(context);
        databaseHelper.deleteItem(_id);
    }
    //calls databaseHelper.getAll, returns ArrayList of PantryItems
    @Override
    public ArrayList<PantryItem> showAllPantryItems() {
        PantryDatabaseHelper databaseHelper = new PantryDatabaseHelper(context);
        return databaseHelper.getAll();
    }
}
