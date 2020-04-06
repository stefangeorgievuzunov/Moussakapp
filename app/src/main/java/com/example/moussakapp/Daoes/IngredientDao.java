package com.example.moussakapp.Daoes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moussakapp.Entities.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {
    @Query("SELECT * FROM  ingredients")
    LiveData<List<Ingredient>> getAll();

    @Insert
    void insert(Ingredient ingridient);
}
