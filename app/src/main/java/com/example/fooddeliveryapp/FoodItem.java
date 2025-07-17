package com.example.fooddeliveryapp;

public class FoodItem {
    private String restaurantName;
    private String foodDescription;
    private String imageUrl;
    private String rating;
    private String deliveryInfo;
    private String deliveryTime;

    public FoodItem() {
        // Required empty constructor for Firebase
    }

    public FoodItem(String restaurantName, String foodDescription, String imageUrl,
                    String rating, String deliveryInfo, String deliveryTime) {
        this.restaurantName = restaurantName;
        this.foodDescription = foodDescription;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
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
