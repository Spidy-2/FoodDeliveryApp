package com.example.fooddeliveryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.databinding.FoodItemLayoutBinding;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private final Context context;
    private final List<FoodItem> foodItemList;


    public FoodItemAdapter(Context context, List<FoodItem> foodItemList) {
        this.context = context;
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        FoodItemLayoutBinding binding = FoodItemLayoutBinding.inflate(inflater, parent, false);
        return new FoodItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        FoodItemLayoutBinding binding = holder.binding;

        binding.restaurantName.setText(foodItem.getRestaurantName());
        binding.restaurantDetails.setText(foodItem.getFoodDescription());
        binding.restaurantRating.setText(foodItem.getRating());
        binding.restaurantDelivery.setText(foodItem.getDeliveryInfo());
        binding.restaurantTime.setText(foodItem.getDeliveryTime());
        binding.restaurantImage.setImageResource(foodItem.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {

        FoodItemLayoutBinding binding;

        public FoodItemViewHolder(@NonNull FoodItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
