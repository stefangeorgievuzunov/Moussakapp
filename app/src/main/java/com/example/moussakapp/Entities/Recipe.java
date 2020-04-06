package com.example.moussakapp.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity (tableName = "recipes")
public class Recipe {
    private Integer recipeId;
    private String name;
    private String description;
    private String imageUrl;
    private int addedOn;
    private List<Ingredient> ingredientList;

    public Recipe( String name, String description, String imageUrl, int addedOn, List<Ingredient> ingredientList) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.addedOn = addedOn;
        this.ingredientList = ingredientList;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
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

    @Relation(parentColumn = "recipeId",entityColumn = "ingredientId")
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
