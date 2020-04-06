package com.example.moussakapp;

import android.os.Bundle;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.adapters.RecipeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        System.out.println("hello from the other side");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.addNewRecipe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<Ingredient> ingredientList=new ArrayList<>();
        ingredientList.add(new Ingredient("chesun","3 lujici bash baq golemi"));
        Recipe recipe=new Recipe("skembke chorba","Tradicionna bulgarska shkembe chorba","https://www.fares-foods.com/Products/soup/shkembe_chorba.png",
                10000,ingredientList);

        System.out.println(recipe.getName());
        List<Recipe> recipeList=new ArrayList<>();

        recipeList.add(recipe);
        System.out.println(recipeList.get(0).getName());

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recipesList);
        RecipeAdapter recipeAdapter=new RecipeAdapter(recipeList);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
