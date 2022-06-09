package com.example.pocketpantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IngredientsDatabaseHelper extends SQLiteOpenHelper implements Contract.Model.IngredientsModel{

    public static final String TABLE_NAME = "INGREDIENTS_TABLE";
    public static final String COLUMN_ID = "RECIPE_ID";
    public static final String COLUMN_INGREDIENT_NAME = "INGREDIENT_NAME";

    public IngredientsDatabaseHelper(@Nullable Context context) {
        //This is a non default constructor for creating management database
        super(context, "ingredients.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createIngredientTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER, " + COLUMN_INGREDIENT_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createIngredientTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Used when database needs to be upgraded and new features need to be added
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addIngredients(ArrayList<String> ingredients, int recipeId) {

        SQLiteDatabase IngredientDb = this.getWritableDatabase();

        for (int i = 0; i < ingredients.size(); i++) {
            ContentValues IngredientCv = new ContentValues();

            IngredientCv.put(COLUMN_ID, recipeId);
            IngredientCv.put(COLUMN_INGREDIENT_NAME, ingredients.get(i));

            IngredientDb.insert(TABLE_NAME, null, IngredientCv);
        }
    }

    @Override
    public ArrayList<Ingredient> getRecipeIngredients(int _id) {

        ArrayList<Ingredient> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_NAME + "WHERE \"COLUMN_ID=?\"";

        //getReadable helps select items from the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, new String[]{String.valueOf(_id)});
        //iterate through the list of ingredients to find all rows with the given id
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                Ingredient ingredient = new Ingredient(name, id);
                returnList.add(ingredient);
            } while(cursor.moveToNext());
        }
        else{}
        cursor.close();
        db.close();
        return returnList;
    }

    //fix this next and add comments to everything
    public void upDateIngredients(Recipe recipe){
        ArrayList<String> ingredientNames = new ArrayList<String>();
        deleteIngredients(recipe.getDbId());
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++){
            ingredientNames.add(ingredients.get(i).getName());
        }
        addIngredients(ingredientNames, recipe.getDbId());
    }

    public void deleteIngredients(int _id){
        //Delete all Ingredients
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "COLUMN_ID=?", new String[] {String.valueOf(_id)});
    }
}
