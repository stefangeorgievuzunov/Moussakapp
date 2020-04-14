package com.example.moussakapp.Daoes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;

import java.util.List;

@Dao
public interface RecipeDao {
    //recipes

    @Query("SELECT * FROM recipes ORDER BY addedOn desc")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM recipes ORDER BY addedOn asc")
    List<Recipe> getByDateAsc();

    @Query("SELECT * FROM recipes ORDER BY name asc")
    List<Recipe> sortByNameAsc();

    @Query("SELECT * FROM recipes ORDER BY name desc")
    List<Recipe> sortByNameDesc();

    @Query("SELECT * FROM recipes WHERE recipeId = :recipeid")
    Recipe getRecipe(int recipeid);

    @Transaction
    @Insert
    void insertRecipe(Recipe recipe, List<Ingredient> ingredients);

    @Transaction
    @Query("SELECT * FROM recipes ORDER BY addedOn desc")
    List<RecipeWithIngredients>getAll();

    @Delete
    void deleteRecipe(Recipe recipe);
}
