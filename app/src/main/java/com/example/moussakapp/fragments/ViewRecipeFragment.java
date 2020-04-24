package com.example.moussakapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.R;
import com.squareup.picasso.Picasso;


public class ViewRecipeFragment extends DialogFragment {
    private TextView recipeName;
    private ImageView recipeImage;
    private TextView recipeIngradients;
    private TextView recipeDescription;

    private final RecipeWithIngredients recipeWithIngredients;

    public ViewRecipeFragment(RecipeWithIngredients recipeWithIngredients) {
        this.recipeWithIngredients = recipeWithIngredients;
    }

    public static ViewRecipeFragment newInstance(RecipeWithIngredients recipeWithIngredients) {
        return new ViewRecipeFragment(recipeWithIngredients);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_view_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeName = view.findViewById(R.id.recipeTitleVF);
        recipeImage = view.findViewById(R.id.recipeImageVF);
        recipeIngradients = view.findViewById(R.id.recipeIngredientsVF);
        recipeDescription = view.findViewById(R.id.recipeDescriptionVF);

        loadRecipeViewData();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void loadRecipeViewData() {
        recipeName.setText(recipeWithIngredients.getRecipe().getName());
        recipeDescription.setText(recipeWithIngredients.getRecipe().getDescription());
        loadImage(recipeWithIngredients.getRecipe().getImageUrl());

        String ingredientsView = "";
        for (Ingredient i : recipeWithIngredients.getIngredients()) {
            ingredientsView += i.getName() + ": " + i.getQuantity() + "\n";
        }

        recipeIngradients.setText(ingredientsView);
    }

    private void loadImage(String url) {
        if (url != null) {
            Picasso.get().load(url).into(recipeImage);
        }
    }
}
