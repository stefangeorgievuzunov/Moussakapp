package com.example.moussakapp.Entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded
    private Recipe recipe;
    @Relation(parentColumn = "recipeId",entityColumn = "ingredientId")
    private List<Ingredient> ingredients;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
