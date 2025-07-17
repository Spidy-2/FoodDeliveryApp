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

    private Context context;
    private List<FoodItem> foodList;

    public FoodItemAdapter(Context context, List<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
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
            description = itemView.findViewById(R.id.restaurantDescription);
            rating = itemView.findViewById(R.id.ratingText);
            deliveryInfo = itemView.findViewById(R.id.deliveryInfo);
            deliveryTime = itemView.findViewById(R.id.deliveryTime);
            image = itemView.findViewById(R.id.restaurantImage);
        }
    }
}
