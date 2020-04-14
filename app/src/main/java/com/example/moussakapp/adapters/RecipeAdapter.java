package com.example.moussakapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.R;
import com.example.moussakapp.holders.RecipeViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private List<RecipeWithIngredients> recipes;
    private Context context;

    public RecipeAdapter(List<RecipeWithIngredients> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(recipeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        final RecipeWithIngredients recipeController = recipes.get(position);

        holder.setRecipeTitle(recipeController.getRecipe().getName());
        for(Ingredient r : recipeController.getIngredients()){
            String ingredientsView=r.getName()+" ";
            holder.setRecipeIngredients(ingredientsView);
        }
        holder.setRecipeDescription(recipeController.getRecipe().getDescription());
        Picasso.get().load(recipeController.getRecipe().getImageUrl()).into(holder.getRecipeImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, recipeController.getRecipe().getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addNewRecipe(RecipeWithIngredients recipeWithIngredients) {
        recipes.add(recipeWithIngredients);
        notifyItemChanged(0);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
