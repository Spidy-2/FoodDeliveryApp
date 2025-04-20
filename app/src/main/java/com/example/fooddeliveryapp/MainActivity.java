package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FoodItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewFoodItems = findViewById(R.id.restaurantsRecyclerView);
        EditText editTextSearch = findViewById(R.id.searchBar);

        ArrayList<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Rose Garden Restaurant", "Burger - Chicken - Riche - Wings", R.drawable.burger_restaurant, "4.7", "Free", "20 min"));
        foodItems.add(new FoodItem("Another Restaurant", "Pizza - Pasta - Italian", R.drawable.pizza_restaurant, "4.5", "$2.99", "30 min"));

        recyclerViewFoodItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodItemAdapter(this, foodItems);
        recyclerViewFoodItems.setAdapter(adapter);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        adapter.setOnItemClickListener(position -> {

            if(position == 1){
                Toast.makeText(MainActivity.this, "Opening Pizza Details", Toast.LENGTH_SHORT).show();
            }

        });
    }
}

