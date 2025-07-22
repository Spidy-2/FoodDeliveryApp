package com.example.fooddeliveryapp;

public class Restaurant {
    private String name;
    private String description;
    private String imageUrl;
    private double rating;
    private String deliveryTime;
    private boolean isVegOnly;

    public Restaurant(String name, String description, String imageUrl) {
    }

    public Restaurant(String name, String description, String imageUrl, double rating, String deliveryTime, boolean isVegOnly) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.isVegOnly = isVegOnly;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public boolean isVegOnly() {
        return isVegOnly;
    }
}
