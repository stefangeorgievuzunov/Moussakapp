package com.example.moussakapp;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.Repositories.Repository;
import com.example.moussakapp.adapters.RecipeAdapter;
import com.example.moussakapp.fragments.AddRecipeDialogInterface;
import com.example.moussakapp.fragments.AddRecipeFragment;
import com.example.moussakapp.fragments.ViewRecipeDialogInterface;
import com.example.moussakapp.fragments.ViewRecipeFragment;
import com.example.moussakapp.holders.RecipeViewHolder;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Stetho.initializeWithDefaults(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.addNewRecipe);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(action -> {
            AddRecipeFragment addContactFragment = AddRecipeFragment.newInstance();
            addContactFragment.show(getSupportFragmentManager(), "fragment_add_recipe");
        });

        repository = new Repository(getApplicationContext());

        try {
            recipes = repository.getAllRecipes();
        } catch (Exception e) {
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
                if (isCurrentlyActive) {
                    recipeAdapter.changeItemViewBgColor((RecipeViewHolder) viewHolder, Color.RED);
                }
                if (dX == 0 && !isCurrentlyActive) {
                    recipeAdapter.changeItemViewBgColor((RecipeViewHolder) viewHolder, Color.WHITE);
                }
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                recipeAdapter.changeItemViewBgColor((RecipeViewHolder) viewHolder, Color.WHITE);

                try {
                    Toast.makeText(getApplicationContext(), recipes.get(position).getRecipe().getName() + " was deleted.", Toast.LENGTH_SHORT).show();
                    recipeAdapter.deleteRecipe(recipes.get(position));
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
        searchView.setQueryHint("salt, sugar, olive..");
        searchView.onActionViewExpanded();

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
        SearchView search=(SearchView) item.getActionView();
        search.setFocusable(true);
        search.requestFocusFromTouch();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishAddDialog(RecipeWithIngredients recipeWithIngredients) {
        recipeAdapter.addNewRecipe(recipeWithIngredients);
        Toast.makeText(getApplicationContext(), recipeWithIngredients.getRecipe().getName() + " was added to recipe list.", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void viewRecipeDialog(RecipeWithIngredients recipeWithIngredients) {
        ViewRecipeFragment viewRecipeFragment = ViewRecipeFragment.newInstance(recipeWithIngredients);
        viewRecipeFragment.show(getSupportFragmentManager(), "fragment_view_recipe");
    }
}
