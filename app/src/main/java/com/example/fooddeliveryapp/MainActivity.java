package com.example.fooddeliveryapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fooddeliveryapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("The British Restaurant", "Burger - Chicken - Wings", R.drawable.restaurant1, "4.7", "Free Delivery", "20 min"));
        foodItems.add(new FoodItem("Pizza Planet", "Pizza - Italian - Pasta", R.drawable.restaurant2, "4.5", "Free Delivery", "25 min"));
        foodItems.add(new FoodItem("Burger King", "Burger - Fries - Shake", R.drawable.restaurant3, "4.8", "Free Delivery", "15 min"));
        foodItems.add(new FoodItem("Sushi Bar", "Sushi - Rolls", R.drawable.restaurant4, "4.6", "Free Delivery", "30 min"));
        foodItems.add(new FoodItem("Spicy Grill", "BBQ - Wings - Grilled", R.drawable.restaurant5, "4.9", "Free Delivery", "18 min"));

        FoodItemAdapter adapter = new FoodItemAdapter(MainActivity.this, foodItems);
        binding.restaurantsRecyclerView.setAdapter(adapter);
    }
}
