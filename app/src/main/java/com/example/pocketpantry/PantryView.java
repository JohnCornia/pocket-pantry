/**********************************************
 *
 * Pantry view shows the list of items you have added to your pantry.
 * It uses a recycler view to display all items
 *
 **********************************************/
package com.example.pocketpantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class PantryView extends AppCompatActivity implements PantryViewInterface{
    private Button addButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_view);

        // pantry recyclerview, add and back button variables found by id
        addButton = findViewById(R.id.pantryAddButtton);
        backButton = findViewById(R.id.backButton);
        RecyclerView recyclerView = findViewById(R.id.pantryRecyclerView);

        // set up adapter for pantry recycler view
        Presenter presenter = new Presenter(PantryView.this);
        PantryRecyclerViewAdapter adapter = new PantryRecyclerViewAdapter(this, presenter.showAllPantryItems(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // onClick listener for add button, change activity to pantry add view
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PantryView.this, PantryAddView.class);
                startActivity(intent);
            }
        });

        // onClick listener for back button, change activity to home view
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PantryView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(PantryView.this, PantryItemEdit.class);
        startActivity(intent);
    }
}
