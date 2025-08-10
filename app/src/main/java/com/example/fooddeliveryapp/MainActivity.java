package com.example.fooddeliveryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RestaurantAdapter restaurantAdapter;


    private final List<Restaurant> restaurantList = new ArrayList<>();
    private TextView userEmailText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userEmailText = findViewById(R.id.userEmailText);
       RecyclerView restaurantRecyclerView = findViewById(R.id.restaurantsRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter(this, restaurantList);
        restaurantRecyclerView.setAdapter(restaurantAdapter);


        fetchDummyRestaurants();
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    private void fetchDummyRestaurants() {
        restaurantList.clear();

        restaurantList.add(new Restaurant(
                "The British Bites",
                "Burgers, Chicken Wings, Fast Food",
                "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg"
        ));

        restaurantList.add(new Restaurant(
                "Spice Hub",
                "Indian Curries, Tandoori, Naan",
                "https://cdn.pixabay.com/photo/2017/06/02/18/24/indian-food-2367854_960_720.jpg"
        ));

        restaurantList.add(new Restaurant(
                "Green Bowl",
                "Healthy Salads & Vegan Meals",
                "https://cdn.pixabay.com/photo/2017/09/02/13/26/salad-2706849_960_720.jpg"
        ));

        restaurantList.add(new Restaurant(
                "Noodle Nest",
                "Chinese Noodles, Momos & More",
                "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665_960_720.jpg"
        ));

        restaurantList.add(new Restaurant(
                "Pizza Portal",
                "Woodfired Pizza & Garlic Bread",
                "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg"
        ));

        restaurantAdapter.notifyDataSetChanged();
        userEmailText.setText("Showing 5 dummy restaurants!");
    }
}
