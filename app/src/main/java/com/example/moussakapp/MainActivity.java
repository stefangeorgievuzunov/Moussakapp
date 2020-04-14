package com.example.moussakapp;

import android.os.Bundle;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.Repositories.Repository;
import com.example.moussakapp.adapters.RecipeAdapter;
import com.example.moussakapp.fragments.AddRecipeDialogInterface;
import com.example.moussakapp.fragments.AddRecipeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddRecipeDialogInterface {
    private  Repository repository;
    RecipeAdapter recipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository=new Repository(getApplicationContext());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.addNewRecipe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                AddRecipeFragment addContactFragment = AddRecipeFragment.newInstance();
                addContactFragment.show(fm, "fragment_add_recipe");
            }
        });

       try{
           RecyclerView recyclerView=findViewById(R.id.recipesList);
           recipeAdapter=new RecipeAdapter(repository.getAllRecipes());
           recyclerView.setAdapter(recipeAdapter);
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
       }catch(Exception e){
           System.out.println("shit happens");
           e.printStackTrace();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishAddDialog(Recipe recipe, List<Ingredient> ingredientList) {
        RecipeWithIngredients newRecipe=new RecipeWithIngredients();
        newRecipe.setRecipe(recipe);
        newRecipe.setIngredients(ingredientList);

        repository.insertRecipe(recipe,ingredientList);
        recipeAdapter.addNewRecipe(newRecipe);
    }
}
