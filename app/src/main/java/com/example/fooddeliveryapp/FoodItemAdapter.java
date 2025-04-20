package com.example.fooddeliveryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private final Context context;
    private ArrayList<FoodItem> foodItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item_layout, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.restaurantNameTextView.setText(foodItem.getRestaurantName());
        holder.foodDescriptionTextView.setText(foodItem.getFoodDescription());
        holder.foodImageView.setImageResource(foodItem.getImageResourceId());
        holder.ratingTextView.setText(foodItem.getRating());
        holder.deliveryInfoTextView.setText(foodItem.getDeliveryInfo());
        holder.deliveryTimeTextView.setText(foodItem.getDeliveryTime());

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImageView;
        TextView restaurantNameTextView;
        TextView foodDescriptionTextView;
        TextView ratingTextView;
        TextView deliveryInfoTextView;
        TextView deliveryTimeTextView;
        View itemView;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            restaurantNameTextView = itemView.findViewById(R.id.restaurantNameTextView);
            foodDescriptionTextView = itemView.findViewById(R.id.foodDescriptionTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            deliveryInfoTextView = itemView.findViewById(R.id.deliveryInfoTextView);
            deliveryTimeTextView = itemView.findViewById(R.id.deliveryTimeTextView);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String query) {
        query = query.toLowerCase();
        ArrayList<FoodItem> filteredList = new ArrayList<>();
        for (FoodItem item : foodItems) {
            if (item.getRestaurantName().toLowerCase().contains(query) ||
                    item.getFoodDescription().toLowerCase().contains(query)) {
                filteredList.add(item);
            }
        }
        foodItems = filteredList;
        notifyDataSetChanged();
    }
}

