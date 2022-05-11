/**********************************************************************
CONTRACT Interface
 This program uses Model View Presenter (MVP) architecture. The purpose
 of this interface is to establish how communication will occur between
 Views-Presenter as well as Presenter-Models.

 This interface contains all the abstract methods of the program. These
 methods will be implemented by the Models, Views, and Presenter
 classes.

 Models:
 DatabaseHelper

 Views:
 MainActivity, PantryView, PantryAddView, and PantryRecyclerView

 Presenter:
 Presenter

 To Do:
 1. The Views need to be refactored and the View interface needs to be
 built out with abstract functions.
 2. Functions in the Presenter interface pertaining to recipes are
 stubs.
 4. Views for Recipes need to be created
 5. DatabaseHelper's functions need to be refactored to perform
 database operations for pantry items as well as recipe items
**********************************************************************/

package com.example.pocketpantry;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface Contract {
    interface Model{
        //Function headers for database operations, see DatabaseHelper class
        // for definitions
        interface PantryModel {
            boolean addOne(String name, int quantity, float weight);

            List<PantryItem> getAll();

            void updateItem(PantryItem pantryItem);

            void deleteItem(int _id);
        }
        interface RecipeModel {
            boolean addOne(ArrayList<String> ingredients, int servingSize, String name);
            List<PantryItem> getAll();

            void updateItem(Recipe recipe);

            void deleteItem(int _id);
        }
    }

    interface View{}

    interface Presenter{
        //Event handlers for recipes view.
        //These are all stub functions, they do not yet have definitions
        void onClickCreateRecipe(ArrayList<String> ingredients, int servingSize, String name);
        void onClickReadRecipe(int _id);
        void onClickUpdateRecipe(Recipe recipe);
        void onClickDeleteRecipe(int _id);
        ArrayList<Recipe> showAllRecipes();

        //Event handlers for pantry view, see Presenter class for definitions
        boolean onClickCreatePantryItem(String name, int quantity,
                                        float weight);
        void onClickUpdatePantryItem(PantryItem pantryItem);
        void onClickDeletePantryItem(int _id);
        ArrayList<PantryItem> showAllPantryItems();
    }
}
