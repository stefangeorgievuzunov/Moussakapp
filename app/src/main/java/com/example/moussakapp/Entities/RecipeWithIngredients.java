package com.example.moussakapp.Entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(parentColumn = "recipeId",entityColumn = "ingredientId")
    public List<Ingredient> ingredients;
}
