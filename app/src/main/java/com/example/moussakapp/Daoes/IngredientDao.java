package com.example.moussakapp.Daoes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.moussakapp.Entities.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {
    @Query("SELECT * FROM  ingredients")
    List<Ingredient> getAll();

    @Insert
    void insertIngredient(Ingredient ingredient);
}
