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
    private PantryDatabaseHelper databaseHelper;

    //Non-default constructor initializes databaseHelper object with context.
    //'context' is the Activity where 'this' was created
    public Presenter(Context context) {
        this.databaseHelper = new PantryDatabaseHelper(context);
    }
    //stub function
    @Override
    public void onClickCreateRecipe(ArrayList<PantryItem> ingredients, int servingSize) {

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
        return databaseHelper.addOne(name, quantity, weight);
    }
    //calls databaseHelper.updateItem
    @Override
    public void onClickUpdatePantryItem(PantryItem pantryItem) {
        databaseHelper.updateItem(pantryItem);
    }
    //calls databaseHelper.deleteItem
    @Override
    public void onClickDeletePantryItem(int _id) {
        databaseHelper.deleteItem(_id);
    }
    //calls databaseHelper.getAll, returns ArrayList of PantryItems
    @Override
    public ArrayList<PantryItem> showAllPantryItems() {
        return databaseHelper.getAll();
    }
}
