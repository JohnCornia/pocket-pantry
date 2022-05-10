/**********************************************
 *
 * Main activity is the first view seen in the app
 * the Pantry button takes you directly to the pantry view,
 * using intent and onClick listener
 *
 **********************************************/
package com.example.pocketpantry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // variable for button
    private Button pantryButton, recipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variable for button find by id
        pantryButton = findViewById(R.id.mainPantryButton);
        recipeButton = findViewById(R.id.mainRecipeButton);

        // onClick listener for pantry button, change activity to pantry view
        pantryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PantryView.class);
                startActivity(intent);
            }
        });

        // onClick listener for recipe button, change activity to recipe view
        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecipeView.class);
                startActivity(intent);
            }
        });
    }
}