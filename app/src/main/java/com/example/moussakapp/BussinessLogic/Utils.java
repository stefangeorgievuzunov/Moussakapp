package com.example.moussakapp.BussinessLogic;

import android.content.Context;

import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Repositories.RecipeRepository;

public class Utils {
    public static void insertRecipe(Context context, Recipe recipe) {
        RecipeRepository recipeRepository = new RecipeRepository(context);
        recipeRepository.insertRecipe(recipe);
    }
}
