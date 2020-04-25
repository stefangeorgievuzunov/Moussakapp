package com.example.moussakapp.fragments.entities;

import android.widget.ImageView;

import com.example.moussakapp.Data.resources.RecipeGallery;

public class RecipeImage {
    private ImageView imageView;
    private String imageUrl= RecipeGallery.selectImgPngDefault;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
