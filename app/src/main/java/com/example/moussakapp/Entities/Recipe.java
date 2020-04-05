package com.example.moussakapp.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.time.LocalDate;
import java.util.List;

@Entity (tableName = "recipes")
public class Recipe {
    private Integer recipeId;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDate addedOn;
    private List<Ingredient> ingredientList;

    public Recipe(Integer recipeId, String name, String description, String imageUrl, LocalDate addedOn, List<Ingredient> ingredientList) {
        this.recipeId = recipeId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.addedOn = addedOn;
        this.ingredientList = ingredientList;
    }

    @PrimaryKey
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
    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
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
