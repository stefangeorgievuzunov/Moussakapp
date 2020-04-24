package com.example.moussakapp.Daoes;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;

import java.util.List;

import static androidx.room.ForeignKey.*;

@Dao
public abstract class RecipeDao {

    public  void insert(RecipeWithIngredients recipeWithIngredients){
        long id=insertRecipe(recipeWithIngredients.getRecipe());
        recipeWithIngredients.getIngredients().forEach(i->i.setRecipeId(id));
        insertAll(recipeWithIngredients.getIngredients());
    }

    public void delete(RecipeWithIngredients recipeWithIngredients){
        delete(recipeWithIngredients.getRecipe(),recipeWithIngredients.getIngredients());
    }

    @Insert
    abstract  void insertAll(List<Ingredient> ingredients);
    @Insert
    abstract long insertRecipe(Recipe recipe);

    @Transaction
    @Delete
    abstract void delete(Recipe recipe,List<Ingredient> ingredients);

    @Transaction
    @Query("SELECT * FROM Recipe")
     public abstract List<RecipeWithIngredients> loadAll();
}
