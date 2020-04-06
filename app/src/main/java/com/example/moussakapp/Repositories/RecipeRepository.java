package com.example.moussakapp.Repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.moussakapp.Data.RecipeDatabase;
import com.example.moussakapp.Entities.Recipe;

public class RecipeRepository {
    private String DB_NAME = "db_recipe";

    private RecipeDatabase recipeDatabase;
    public RecipeRepository(Context context) {
        recipeDatabase = Room.databaseBuilder(context, RecipeDatabase.class, DB_NAME).build();
    }

    public void insertRecipe(final Recipe recipe) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().insertRecipe(recipe);
                return null;
            }
        }.execute();
    }
}
