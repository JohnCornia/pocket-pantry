package com.example.pocketpantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RecipeDatabaseHelper extends SQLiteOpenHelper implements Contract.Model.RecipeModel{
    public static final String TABLE_NAME = "RECIPE_TABLE";
    public static final String COLUMN_SERVING_SIZE = "SERVING_SIZE";
    public static final String COLUMN_RECIPE_NAME = "RECIPE_NAME";
    public static final String COLUMN_ID = "ID";
    private static final String TAG = "Recipe Database Helper";
    private static Context context = null;

    public RecipeDatabaseHelper(@Nullable Context context) {
        //This is a non default constructor for creating management database
        super(context, "recipe.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* Create method
            This method creates the database and is only called once
            when the database is created for the first time
         */
        String createRecipeTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPE_NAME + " TEXT, " + COLUMN_SERVING_SIZE + " INT)";

        sqLiteDatabase.execSQL(createRecipeTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Used when database needs to be upgraded and new features need to be added
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //This function adds data to the database, in this case recipe items
    @Override
    public String addOne(ArrayList<String> ingredients, int servingSize, String name){
        //this is used to record whether or not the Recipe was successfully
        // written to the database
        long insert = -1;

        //getWritable used to insert, update or delete records
        SQLiteDatabase RecipeDb = this.getWritableDatabase();

        //add code here to double check that the same name isn't used twice

        //if same name is used, re-prompt for input on user's end
        Cursor exists = RecipeDb.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_RECIPE_NAME}, "COLUMN_RECIPE_NAME=?", new String[]{name}, null, null, null, null);

        if(name == exists.getString(0)){
            throw new SQLiteException("A recipe for " + name + " already exists. Please choose another name.");
        }
        else {
            ContentValues RecipeCv = new ContentValues();
            RecipeCv.put(COLUMN_SERVING_SIZE, servingSize);
            RecipeCv.put(COLUMN_RECIPE_NAME, name);

            insert = RecipeDb.insert(TABLE_NAME, null, RecipeCv);

            Cursor cursor = RecipeDb.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_RECIPE_NAME}, "COLUMN_RECIPE_NAME=?", new String[]{name}, null, null, null, null);
            Integer recipeRowID = new Integer(cursor.getString(0));
            cursor.close();

            Log.d(TAG, "Recipe Row ID " + recipeRowID);
            IngredientsDatabaseHelper ingredientsDatabaseHelper = new IngredientsDatabaseHelper(context);
            ingredientsDatabaseHelper.addIngredients(ingredients, recipeRowID);

        }
        exists.close();
        if (insert == -1){
            return "Success! " + name + " was added to your Recipes";
        }
        else {
            return "Sorry, we were unable to add " + name + " to your Recipes";
        }
    }

    @Override
    public ArrayList<Recipe> getAll() {
        ArrayList<Recipe> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_NAME;
        IngredientsDatabaseHelper ingredientsDatabaseHelper = new IngredientsDatabaseHelper(context);

        //getReadable helps select items from the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                int servingSize = cursor.getInt(2);
                ArrayList<Ingredient> ingredients = ingredientsDatabaseHelper.getRecipeIngredients(_id);
                Recipe recipe = new Recipe(_id, ingredients, servingSize, name);
                returnList.add(recipe);
            } while(cursor.moveToNext());
        }
        else{}

        cursor.close();
        db.close();
        return returnList;
    }

    @Override
    public boolean updateItem(Recipe recipe) {
        boolean successfulUpdate = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //This will need refactoring keys when using receipts
        cv.put(COLUMN_RECIPE_NAME, recipe.getName());
        cv.put(COLUMN_SERVING_SIZE, recipe.getServingSize());

        IngredientsDatabaseHelper ingredientsDatabaseHelper = new IngredientsDatabaseHelper(context);
        ingredientsDatabaseHelper.upDateIngredients(recipe);

        long update = db.update(TABLE_NAME, cv, "COLUMN_ID=?", new String[] {String.valueOf(recipe.getDbId())});

        if (update == 1){
            successfulUpdate = true;
        }
        else{}

        return successfulUpdate;
    }

    @Override
    public void deleteItem(int _id) {
//Delete Pantry Items
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "COLUMN_ID=?", new String[] {String.valueOf(_id)});
        IngredientsDatabaseHelper ingredientsDatabaseHelper = new IngredientsDatabaseHelper(context);
        ingredientsDatabaseHelper.deleteIngredients(_id);
    }
}
