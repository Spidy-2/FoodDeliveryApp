package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private RecyclerView restaurantsRecyclerView;
    private RestaurantAdapter restaurantAdapter;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private TextView userEmailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Link UI elements
        userEmailText = findViewById(R.id.userEmailText);
        restaurantsRecyclerView = findViewById(R.id.restaurantsRecyclerView);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter(this, restaurantList);
        restaurantsRecyclerView.setAdapter(restaurantAdapter);

        // Show a fake loading text (optional)
        userEmailText.setText("Fetching food items...");

        // Fetch data from Firestore
        fetchRestaurantsFromFirestore();
    }

    private void fetchRestaurantsFromFirestore() {
        CollectionReference restaurantRef = db.collection("restaurants");

        restaurantRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                restaurantList.clear(); // Clear old data
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    String name = doc.getString("name");
                    String description = doc.getString("description");
                    String imageUrl = doc.getString("imageUrl");

                    Restaurant restaurant = new Restaurant(name, description, imageUrl);
                    restaurantList.add(restaurant);
                }
                restaurantAdapter.notifyDataSetChanged();
                userEmailText.setText("Fetched " + restaurantList.size() + " items!");
            } else {
                Log.e("FirestoreError", "Error getting documents: ", task.getException());
                Toast.makeText(MainActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
                userEmailText.setText("Failed to load items");
            }
        });
    }
}
