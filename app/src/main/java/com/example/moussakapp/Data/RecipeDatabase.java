package com.example.moussakapp.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.moussakapp.Daoes.IngredientDao;
import com.example.moussakapp.Daoes.RecipeDao;
import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;

    @Database(entities = {Recipe.class, Ingredient.class}, version = 1,exportSchema = false)
        public abstract class RecipeDatabase extends RoomDatabase{
        public abstract RecipeDao recipeDao();
        public abstract IngredientDao ingredientDao();
    }