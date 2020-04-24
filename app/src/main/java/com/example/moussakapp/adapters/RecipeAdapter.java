package com.example.moussakapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.R;

import com.example.moussakapp.Repositories.Repository;
import com.example.moussakapp.fragments.ViewRecipeDialogInterface;
import com.example.moussakapp.holders.RecipeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> implements Filterable {
    private List<RecipeWithIngredients> recipesList;
    private Repository repository;
    private ViewRecipeDialogInterface viewRecipeDialogInterface;

    public RecipeAdapter(List<RecipeWithIngredients> recipes, Repository repository) {
        this.repository = repository;
        recipesList = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewRecipeDialogInterface = (ViewRecipeDialogInterface) parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recipeView = inflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        final RecipeWithIngredients recipe = recipesList.get(position);

        holder.setRecipeTitle(recipe.getRecipe().getName());
        holder.setRecipeIngredients(getRecipeIngredients(recipe));
        holder.setRecipeDescription(recipe.getRecipe().getDescription());
        Picasso.get().load(recipe.getRecipe().getImageUrl()).into(holder.getRecipeImg());

        holder.itemView.setOnClickListener((View v) -> {
            viewRecipeDialogInterface.viewRecipeDialog(recipe);
        });
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    @Override
    public Filter getFilter() {
        return filterIt;
    }

    private Filter filterIt = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint == null || constraint.length() == 0) {
                recipesList = loadAllRecipes();
            } else {
                recipesList = loadSearch(constraint.toString());
            }
            FilterResults results = new FilterResults();
            results.values = recipesList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    };

    public void addNewRecipe(RecipeWithIngredients recipeWithIngredients) {
        recipeWithIngredients.getIngredients().forEach(i->System.out.println(recipeWithIngredients.getRecipe().getName()+"AFTER ->"+i.getName()));
        repository.insertRecipe(recipeWithIngredients);
        recipesList.add(recipeWithIngredients);
        //recipesList = loadAllRecipes();
        loadAllRecipes().forEach(r->r.getIngredients().forEach(i->System.out.println(r.getRecipe().getName()+"LATTEST->"+i.getName())));
    }

    public void deleteRecipe( RecipeWithIngredients recipeWithIngredients) {
        repository.deleteRecipe(recipeWithIngredients);
        recipesList = loadAllRecipes();
    }

    public void changeItemViewBgColor(@NonNull final RecipeViewHolder holder, final int color) {
        holder.getRecipeCardView().setCardBackgroundColor(color);
    }

    private List<RecipeWithIngredients> loadAllRecipes() {
        try {
            return repository.getAllRecipes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<RecipeWithIngredients> loadSearch(String search) {
        List<RecipeWithIngredients> returned = new ArrayList<>();
        List<String> searchedData = Arrays.asList(search.toLowerCase().split(",\\s*"));
        loadAllRecipes().forEach(r -> r.getIngredients().forEach(i -> {
            if (searchedData.contains(i.getName())) {
                returned.add(r);
            }
        }));
        return returned;
    }

    private String getRecipeIngredients(RecipeWithIngredients recipe) {
        return recipe.getIngredients().stream()
                .map(Ingredient::getName)
                .collect(Collectors.joining(" "));
    }
}
