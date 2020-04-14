package com.example.moussakapp;

import android.os.Bundle;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.Repositories.Repository;
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

        Recipe recipe=new Recipe("shkembe chorba","Tradicionna bulgarska shkembe chorba","https://www.fares-foods.com/Products/soup/shkembe_chorba.png",
                10000);

        System.out.println(recipe.getName());
        List<Recipe> recipeList=new ArrayList<>();

        //recipeList.add(recipe);



       try{
           Repository repo=new Repository(getApplicationContext());

           Ingredient ingredient=new Ingredient("kaima","1 panica");
//
//           repo.insertIngredient(ingredient);
           List<Ingredient> allIngredients= new ArrayList<>();
           allIngredients.add(ingredient);

           repo.insertRecipe(recipe,allIngredients);
           List<RecipeWithIngredients>  recipeWithIngredients1ist=repo.getAllRecipes();

           for (RecipeWithIngredients r:recipeWithIngredients1ist){
               System.out.println(r.recipe.getName()+" ingredients: "+r.ingredients.get(0));
           }

           for(Ingredient i:repo.getAllIngredients()){
               System.out.println("OUT OF THE BOX" + i.getName());
           }

       }catch(Exception e){
           System.out.println("shit happens");
           e.printStackTrace();
       }

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
