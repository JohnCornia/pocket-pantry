package com.example.pocketpantry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RecipeAddView extends AppCompatActivity {

    private Button addButton, backButton;
    private static final String TAG = "RecipeAddView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add_view);

        // add button, back button found by id
        backButton = findViewById(R.id.backButtonR2);

        // onClick listener for back button, change activity to home view
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeAddView.this, RecipeView.class);
                startActivity(intent);
            }
        });
    }
}