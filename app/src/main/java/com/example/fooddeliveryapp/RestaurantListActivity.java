package com.example.fooddeliveryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter;
    private final List<Restaurant> restaurantList = new ArrayList<>();
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        RecyclerView restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        restaurantAdapter = new RestaurantAdapter(this, restaurantList);
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        db = FirebaseFirestore.getInstance();
        fetchRestaurantsFromFirestore();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchRestaurantsFromFirestore() {
        CollectionReference restaurantRef = db.collection("restaurants");

        restaurantRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                restaurantList.clear();
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    String name = doc.getString("name");
                    String description = doc.getString("description");
                    String imageUrl = doc.getString("imageUrl");

                    Restaurant restaurant = new Restaurant(name, description, imageUrl);
                    restaurantList.add(restaurant);
                }
                restaurantAdapter.notifyDataSetChanged();
            } else {
                Log.e("Firestore", "Error getting documents: ", task.getException());
                Toast.makeText(this, "Failed to load restaurants", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
