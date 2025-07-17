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

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder> {

    private final Context context;
    private final List<FoodItem> foodList;

    public FoodItemAdapter(Context context, List<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem item = foodList.get(position);
        holder.name.setText(item.getRestaurantName());
        holder.description.setText(item.getFoodDescription());
        holder.rating.setText(item.getRating());
        holder.deliveryInfo.setText(item.getDeliveryInfo());
        holder.deliveryTime.setText(item.getDeliveryTime());
        
        Glide.with(context)
                .load(item.getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, rating, deliveryInfo, deliveryTime;
        ImageView image;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.restaurantName);
            image = itemView.findViewById(R.id.restaurantImage);
        }
    }
}
