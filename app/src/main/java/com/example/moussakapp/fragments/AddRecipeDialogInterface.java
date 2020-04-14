package com.example.moussakapp.fragments;
import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;

import java.util.List;

public interface AddRecipeDialogInterface {
void onFinishAddDialog(Recipe recipe, List<Ingredient> ingredientList);
}
