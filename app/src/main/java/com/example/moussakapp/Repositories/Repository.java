package com.example.moussakapp.Repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.moussakapp.Data.RecipeDatabase;
import com.example.moussakapp.Entities.RecipeWithIngredients;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {
    private final String DB_NAME = "db_moussakapp";

    private  RecipeDatabase recipeDatabase;

    public Repository(Context context) {
        recipeDatabase = Room.databaseBuilder(context, RecipeDatabase.class, DB_NAME).build();
    }

    public  void insertRecipe(RecipeWithIngredients recipeWithIngredients){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().insert(recipeWithIngredients);
                return null;
            }
        }.execute();
    }

    public void deleteRecipe(final RecipeWithIngredients recipeWithIngredients){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().delete(recipeWithIngredients);
                return null;
            }
        }.execute();

    }

    public List<RecipeWithIngredients> getAllRecipes()throws ExecutionException, InterruptedException{
        return new AsyncTask<Void, Void,List<RecipeWithIngredients>>(){
            @Override
            protected List<RecipeWithIngredients> doInBackground(Void... voids) {
                return recipeDatabase.recipeDao().loadAll();
            }
        }.execute().get();
    }
}
