package com.example.moussakapp.Daoes;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moussakapp.Entities.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipes")
    List<Recipe> getAll();

    //todo-me select all where name or ingredients contains string
   // @Query("SELECT * FROM recipe WHERE")
    List<Recipe> findByIngredient(String ingredient);

    @Insert
    void insert(Recipe recipe);
}
