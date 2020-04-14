package com.example.moussakapp.Repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.moussakapp.Data.RecipeDatabase;
import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {
    private String DB_NAME = "db_recipe";

    private  RecipeDatabase recipeDatabase;

    public Repository(Context context) {
        recipeDatabase = Room.databaseBuilder(context, RecipeDatabase.class, DB_NAME).build();
    }

    public  void insertRecipe(final Recipe recipe,final List<Ingredient> ingredients) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().insertRecipe(recipe,ingredients);
                return null;
            }
        }.execute();
    }

    public  void insertIngredient(final Ingredient ingredient){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.ingredientDao().insertIngredient(ingredient);
                return null;
            }
        }.execute();
    }

    public  List<Ingredient> getAllIngredients()throws ExecutionException, InterruptedException{
       return new AsyncTask<Void, Void, List<Ingredient>>() {
            @Override
            protected List<Ingredient> doInBackground(Void... voids) {
                return recipeDatabase.ingredientDao().getAll();
            }
        }.execute().get();
    }
    public List<RecipeWithIngredients> getAllRecipes()throws ExecutionException, InterruptedException{
        return new AsyncTask<Void, Void,List<RecipeWithIngredients>>(){
            @Override
            protected List<RecipeWithIngredients> doInBackground(Void... voids) {
                return recipeDatabase.recipeDao().getAll();
            }
        }.execute().get();
    }
}
