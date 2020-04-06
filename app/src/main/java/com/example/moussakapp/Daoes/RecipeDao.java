package com.example.moussakapp.Daoes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moussakapp.Entities.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    //todo-Siyana maybe needed join to list ingredients as or list

    @Query("SELECT * FROM recipes ORDER BY addedOn desc")
    LiveData<List<Recipe>> getAll();

    @Query("SELECT * FROM recipes ORDER BY addedOn asc")
    LiveData<List<Recipe>> getByDateAsc();

    @Query("SELECT * FROM recipes ORDER BY name asc")
    LiveData<List<Recipe>> sortByNameAsc();

    @Query("SELECT * FROM recipes ORDER BY name desc")
    LiveData<List<Recipe>> sortByNameDesc();

    @Query("SELECT * FROM recipes WHERE recipeId = :recipeid")
    LiveData<Recipe> getRecipe(int recipeid);

    //todo-Siyana select all where name or ingredients contains string
    //todo-SIyana sort by most found in recipe
   // @Query("SELECT * FROM recipe WHERE")
    LiveData<List<Recipe>> findByIngredients(List<String> ingredients);

    @Insert
    void insertRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);
}
