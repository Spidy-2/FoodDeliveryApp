package com.example.fooddeliveryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fooddeliveryapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private List<FoodItem> foodItemList;
    private FoodItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loadUserProfile();

        foodItemList = new ArrayList<>();
        adapter = new FoodItemAdapter(this, foodItemList);
        binding.restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.restaurantsRecyclerView.setAdapter(adapter);

        fetchDataFromFirestore();
    }

    @SuppressLint("SetTextI18n")
    private void loadUserProfile() {
        String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String email = documentSnapshot.getString("email");
                        binding.userEmailText.setText(getString(R.string.logged_in_as) + email);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Failed to load user info", Toast.LENGTH_SHORT).show());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchDataFromFirestore() {
        db.collection("FoodItems")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    foodItemList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String restaurantName = doc.getString("restaurantName");
                        String foodDescription = doc.getString("foodDescription");
                        String imageUrl = doc.getString("imageUrl");
                        String rating = doc.getString("rating");
                        String deliveryInfo = doc.getString("deliveryInfo");
                        String deliveryTime = doc.getString("deliveryTime");

                        FoodItem foodItem = new FoodItem(
                                restaurantName, foodDescription, imageUrl, rating, deliveryInfo, deliveryTime
                        );
                        foodItemList.add(foodItem);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Failed to load food items", Toast.LENGTH_SHORT).show());
    }
}
