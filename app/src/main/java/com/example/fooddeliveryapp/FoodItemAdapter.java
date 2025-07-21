package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodItemAdapter adapter;
    private List<FoodItem> foodItemList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView); // Make sure this ID exists in your activity_main.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodItemList = new ArrayList<>();
        adapter = new FoodItemAdapter(this, foodItemList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        fetchFoodItemsFromFirestore();
    }

    private void fetchFoodItemsFromFirestore() {
        db.collection("foodItems")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            foodItemList.clear();
                            for (DocumentSnapshot document : snapshot.getDocuments()) {
                                FoodItem item = document.toObject(FoodItem.class);
                                if (item != null) {
                                    foodItemList.add(item);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        Log.e("FirestoreError", "Error getting documents: ", task.getException());
                    }
                });
    }
}
