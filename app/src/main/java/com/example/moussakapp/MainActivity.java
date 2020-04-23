package com.example.moussakapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.Repositories.Repository;
import com.example.moussakapp.adapters.RecipeAdapter;
import com.example.moussakapp.fragments.AddRecipeDialogInterface;
import com.example.moussakapp.fragments.AddRecipeFragment;
import com.example.moussakapp.fragments.ViewRecipeDialogInterface;
import com.example.moussakapp.fragments.ViewRecipeFragment;
import com.example.moussakapp.holders.RecipeViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddRecipeDialogInterface, ViewRecipeDialogInterface {
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

        recipeAdapter = new RecipeAdapter(recipes,repository);

        recyclerView = findViewById(R.id.recipesList);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                boolean isCancelled = dX == 0 && !isCurrentlyActive;

                if (isCurrentlyActive) {
                    recipeAdapter.changeItemViewBgColor((RecipeViewHolder) viewHolder, Color.RED);
                }
                if (isCancelled) {
                    recipeAdapter.changeItemViewBgColor((RecipeViewHolder) viewHolder, Color.WHITE);
                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                recipeAdapter.changeItemViewBgColor((RecipeViewHolder) viewHolder, Color.WHITE);

                Toast.makeText(getApplicationContext(), recipes.get(position).getRecipe().getName() + " was deleted.", Toast.LENGTH_SHORT).show();

                try {
                    repository.deleteRecipe(repository.getAllRecipes().get(position).getRecipe());
                    recipes.remove(position);
                    recipeAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.recipeSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recipeAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void viewRecipeDialog(RecipeWithIngredients recipeWithIngredients) {
        FragmentManager fm = getSupportFragmentManager();
        ViewRecipeFragment viewRecipeFragment = ViewRecipeFragment.newInstance(recipeWithIngredients);  
        viewRecipeFragment.show(fm, "fragment_view_recipe");
    }
}
