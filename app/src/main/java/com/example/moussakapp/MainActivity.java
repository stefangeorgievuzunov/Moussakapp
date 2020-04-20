package com.example.moussakapp;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements AddRecipeDialogInterface {
    private Repository repository;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recyclerView;
    private List<RecipeWithIngredients> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        repository = new Repository(getApplicationContext());

        try {
            recipes = repository.getAllRecipes();
        } catch (Exception e) {
            System.out.println("shit happens");
            e.printStackTrace();
        }

        recipeAdapter = new RecipeAdapter(recipes);

        recyclerView = findViewById(R.id.recipesList);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                try {
                    repository.deleteRecipe(repository.getAllRecipes().get(position).getRecipe());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                recipes.remove(position);
                recipeAdapter.notifyDataSetChanged();
            }
        });

        helper.attachToRecyclerView(recyclerView);
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
        RecipeWithIngredients newRecipe = new RecipeWithIngredients();
        newRecipe.setRecipe(recipe);
        newRecipe.setIngredients(ingredientList);

        repository.insertRecipe(recipe, ingredientList);
        recipeAdapter.addNewRecipe(newRecipe);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
