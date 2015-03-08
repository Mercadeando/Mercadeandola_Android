package com.example.loginface.loginface;


import android.graphics.drawable.Drawable;

public class Product {

    public String title;
    public Drawable productImage;
    public String description;
    public double price;
    public boolean selected;
    public String color;

    public Product(String title, Drawable productImage, String description,
                   double price,String color) {
        this.title = title;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
        this.color = color;

    }

}