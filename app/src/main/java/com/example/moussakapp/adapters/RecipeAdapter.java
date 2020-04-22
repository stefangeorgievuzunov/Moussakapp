package com.example.moussakapp.adapters;

import android.content.Context;
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

import com.example.moussakapp.fragments.ViewRecipeDialogInterface;
import com.example.moussakapp.holders.RecipeViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> implements Filterable {
    private List<RecipeWithIngredients> recipesList;
    private List<RecipeWithIngredients> recipeListFull;
    private Context context;
    private ViewRecipeDialogInterface viewRecipeDialogInterface;

    public RecipeAdapter(List<RecipeWithIngredients> recipes) {
        this.recipesList = recipes;
        recipeListFull =new ArrayList<>(recipes);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        viewRecipeDialogInterface=(ViewRecipeDialogInterface) context;


        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(recipeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {
        final RecipeWithIngredients recipeController = recipesList.get(position);
        holder.setRecipeTitle(recipeController.getRecipe().getName());

        for (Ingredient r : recipeController.getIngredients()) {
            String ingredientsView = r.getName() + " ";
            holder.setRecipeIngredients(ingredientsView);
        }
        holder.setRecipeDescription(recipeController.getRecipe().getDescription());
        Picasso.get().load(recipeController.getRecipe().getImageUrl()).into(holder.getRecipeImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRecipeDialogInterface.viewRecipeDialog(recipeController);
            }
        });
    }

    public void addNewRecipe(RecipeWithIngredients recipeWithIngredients) {
        recipesList.add(recipeWithIngredients);
        notifyItemChanged(0);
    }

    public void changeItemViewBgColor(@NonNull final RecipeViewHolder holder, int color) {
        holder.getRecipeCardView().setCardBackgroundColor(color);
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
            List<RecipeWithIngredients> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipeListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase();
                List<String> searchedData= Arrays.asList(filterPattern.split(",\\s*"));

                for (RecipeWithIngredients item : recipeListFull) {
                    for (Ingredient i : item.getIngredients() ){
                        if (searchedData.contains(i.getName().toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipesList.clear();
            recipesList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
