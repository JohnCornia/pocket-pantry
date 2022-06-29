package com.example.pocketpantry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeAddView extends AppCompatActivity {
    private Button addButton, backButton, addIngredient;
    private EditText recipeName, ingredient, servingSize;
    private ListView ingredientList;
    private ArrayList<String> ingredientArray;
    ArrayAdapter<String> arrayAdapter;
    private static final String TAG = "RecipeAddView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_add_view);

        // add button, back button found by id
        backButton = findViewById(R.id.backButtonR2);
        addButton = findViewById(R.id.addRecipeButton);
        addIngredient = findViewById(R.id.addIngrdientButton);
        recipeName = findViewById(R.id.recipeName);
        ingredient = findViewById(R.id.ingredient);
        servingSize = findViewById(R.id.servingSize);
        ingredientList = findViewById(R.id.ingredientList);

        ingredientArray = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(RecipeAddView.this, android.R.layout.simple_list_item_1, ingredientArray);
        ingredientList.setAdapter(arrayAdapter);

        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = ingredient.getText().toString();
                ingredientArray.add(value);
                arrayAdapter.notifyDataSetChanged();
            }
        });


        // onClick listener for back button, change activity to home view
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeAddView.this, RecipeView.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String servingSizeString = servingSize.getText().toString();
                int serving = Integer.parseInt(String.valueOf(servingSizeString));

                String nameString = recipeName.getText().toString();
                String name = (String.valueOf(nameString));

                Presenter presenter = new Presenter(RecipeAddView.this);

                presenter.onClickCreateRecipe(ingredientArray, serving, name);
            }
        });
    }
}