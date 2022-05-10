package com.example.pocketpantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeView extends AppCompatActivity {
    private Button addButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        // pantry recyclerview, add and back button variables found by id
        addButton = findViewById(R.id.recipeAddButtton);
        backButton = findViewById(R.id.backButtonR);
        RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);

        // onClick listener for add button, change activity to recipe add view
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeView.this, RecipeAddView.class);
                startActivity(intent);
            }
        });

        // onClick listener for back button, change activity to home view
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}