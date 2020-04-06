package com.example.moussakapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.R;
import com.example.moussakapp.holders.RecipeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder>{
    private List<Recipe> recipes;
    private Context context;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(recipeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        final Recipe recipe = recipes.get(position);

        holder.setRecipeTitle(recipe.getName());
        for(Ingredient r : recipe.getIngredientList()){
            String ingredientsView=r.getName()+" ";
            holder.setRecipeIngredients(ingredientsView);
        }
        holder.setRecipeDescription(recipe.getDescription());
        Picasso.get().load(recipe.getImageUrl()).into(holder.getRecipeImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, recipe.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
