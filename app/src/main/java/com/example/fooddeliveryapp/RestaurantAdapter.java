package com.example.fooddeliveryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private final Context context;
    private final List<Restaurant> restaurantList;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.nameTextView.setText(restaurant.getName());
        holder.descriptionTextView.setText(restaurant.getDescription());

        Glide.with(context)
                .load(restaurant.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView;
        ImageView imageView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantNameTextView);
            descriptionTextView = itemView.findViewById(R.id.restaurantDescriptionTextView);
            imageView = itemView.findViewById(R.id.restaurantImageView);
        }
    }
}
