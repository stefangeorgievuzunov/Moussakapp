package com.example.moussakapp.Daoes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moussakapp.Entities.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {
    @Insert
    void insertIngredients(List<Ingredient> ingredientList);

    @Query("SELECT * FROM Ingredient")
    List<Ingredient> getAllIngredietns();

    @Delete
    void deleteIngredients(List<Ingredient> ingredientList);

}
