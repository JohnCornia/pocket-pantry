/**********************************************************************

 DatabaseHelper
 This class creates a database which allows the user to perform
 CRUD operations (Create, Read, Update, Delete)

 It Implements Contract interface using the nested DatabaseModel interface
 which contains all the methods that are necessary to Add, Update, Delete
 items from the database


 TO DO:
 The database still needs refactoring to allow us to perform
 CRUD operations with receipts as well

 **********************************************************************/

package com.example.pocketpantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PantryDatabaseHelper extends SQLiteOpenHelper implements Contract.Model {
    public static final String TABLE_NAME = "PANTRY_TABLE";
    public static final String COLUMN_PANTRY_ITEM_NAME = "PANTRY_ITEM_NAME";
    public static final String COLUMN_PANTRY_ITEM_QUANTITY = "PANTRY_ITEM_QUANTITY";
    public static final String COLUMN_PANTRY_ITEM_WEIGHT = "PANTRY_ITEM_WEIGHT";
    public static final String COLUMN_ID = "ID";

    public PantryDatabaseHelper(@Nullable Context context) {
        //This is a non default constructor for creating management database
        super(context, "pantry.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* Create method
            This method creates the database and is only called once
            when the database is created for the first time
         */
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PANTRY_ITEM_NAME + " TEXT, " + COLUMN_PANTRY_ITEM_QUANTITY + " INT, " + COLUMN_PANTRY_ITEM_WEIGHT + " FLOAT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Used when database needs to be upgraded and new features need to be added
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //This function adds data to the database, in this case pantry items
    public boolean addOne(String name, int quantity, float weight){
        //getWritable used to insert, update or delete records
        SQLiteDatabase db = this.getWritableDatabase();
        //Creates a map of values where the column names are used as the keys
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PANTRY_ITEM_NAME, name);
        cv.put(COLUMN_PANTRY_ITEM_QUANTITY, quantity);
        cv.put(COLUMN_PANTRY_ITEM_WEIGHT, weight);

        long insert = db.insert(TABLE_NAME, null, cv);

        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //Pull all data from the database
    public ArrayList<PantryItem> getAll() {
        ArrayList<PantryItem> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + TABLE_NAME;

        //getReadable helps select items from the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int _id = cursor.getInt(0);
                String pantryItemName = cursor.getString(1);
                int pantryItemQuantity = cursor.getInt(2);
                float pantryItemWeight = cursor.getFloat(3);

                PantryItem pantryItem = new PantryItem(_id, pantryItemName, pantryItemQuantity, pantryItemWeight);
                returnList.add(pantryItem);
            } while(cursor.moveToNext());
        }
        else{}

        cursor.close();
        db.close();
        return returnList;
    }

    @Override
    public void updateItem(PantryItem pantryItem) {
        //Update Pantry Items
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        //This will need refactoring keys when using receipts
        cv.put(COLUMN_PANTRY_ITEM_NAME, pantryItem.getName());
        cv.put(COLUMN_PANTRY_ITEM_QUANTITY, pantryItem.getQuantity());
        cv.put(COLUMN_PANTRY_ITEM_WEIGHT, pantryItem.getWeight());

        long update = db.update(TABLE_NAME, cv, "COLUMN_ID=?", new String[] {String.valueOf(pantryItem.get_id())});
    }

    @Override
    public void deleteItem(int _id) {
        //Delete Pantry Items
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "COLUMN_ID=?", new String[] {String.valueOf(_id)});
    }


}
