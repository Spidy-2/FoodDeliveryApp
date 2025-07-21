package com.example.fooddeliveryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FoodItemAdapter foodItemAdapter;
    private final List<FoodItem> foodItemList = new ArrayList<>();
    private TextView userEmailText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Link UI elements
        userEmailText = findViewById(R.id.userEmailText);
        RecyclerView restaurantsRecyclerView = findViewById(R.id.restaurantsRecyclerView);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup adapter
        foodItemAdapter = new FoodItemAdapter(this, foodItemList);
        restaurantsRecyclerView.setAdapter(foodItemAdapter);

        // Optional loading message
        userEmailText.setText("Fetching food items...");

        // Fetch from Firestore
        fetchFoodItemsFromFirestore();
    }

    @SuppressLint("SetTextI18n")
    private void fetchFoodItemsFromFirestore() {
        CollectionReference restaurantRef = db.collection("restaurants");

        restaurantRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                foodItemList.clear(); // Clear old data
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    String name = doc.getString("name");
                    String description = doc.getString("description");
                    String imageUrl = doc.getString("imageUrl");

                    FoodItem foodItem = new FoodItem(name, description, imageUrl);
                    foodItemList.add(foodItem);
                }
                foodItemAdapter.notifyDataSetChanged();
                userEmailText.setText("Fetched " + foodItemList.size() + " items!");
            } else {
                Log.e("FirestoreError", "Error getting documents: ", task.getException());
                Toast.makeText(MainActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
                userEmailText.setText("Failed to load items");
            }
        });
    }
}
