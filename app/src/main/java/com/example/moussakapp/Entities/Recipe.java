package com.example.moussakapp.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public long recipeId;
    private String name;
    private String description;
    private String imageUrl;
    private int addedOn;

    public Recipe( String name, String description, String imageUrl, int addedOn) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.addedOn = addedOn;
    }



    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    @ColumnInfo(name = "recipe_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ColumnInfo(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ColumnInfo(name = "added_on")
    public int getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(int addedOn) {
        this.addedOn = addedOn;
    }

}
