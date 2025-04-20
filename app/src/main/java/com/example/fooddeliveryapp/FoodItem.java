package com.example.fooddeliveryapp;

public class FoodItem {
    private final String restaurantName;
    private final String foodDescription;
    private final int imageResourceId;
    private final String rating;
    private final String deliveryInfo;
    private final String deliveryTime;

    public FoodItem(String restaurantName, String foodDescription, int imageResourceId, String rating, String deliveryInfo, String deliveryTime) {
        this.restaurantName = restaurantName;
        this.foodDescription = foodDescription;
        this.imageResourceId = imageResourceId;
        this.rating = rating;
        this.deliveryInfo = deliveryInfo;
        this.deliveryTime = deliveryTime;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getRating() {
        return rating;
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
}

