package com.example.moussakapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.GalleryActivity;
import com.example.moussakapp.R;
import com.example.moussakapp.fragments.entities.RecipeImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeFragment extends DialogFragment implements View.OnClickListener {
    private EditText recipeName;
    private EditText recipeIngredients;
    private EditText recipeDescription;
    private RecipeImage recipeImage;
    private Button addNewRecipe;
    AddRecipeDialogInterface listener;

    public AddRecipeFragment() {
        recipeImage = new RecipeImage();
    }

    public static AddRecipeFragment newInstance() {
        return new AddRecipeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_recipe, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeName = view.findViewById(R.id.recipeNameFragment);
        recipeIngredients = view.findViewById(R.id.recipeIngredientsFragment);
        recipeDescription = view.findViewById(R.id.recipeDescriptionFragment);
        addNewRecipe = view.findViewById(R.id.addNewRecipeFragment);
        recipeImage.setImageView((ImageView) view.findViewById(R.id.recipeImageFragment));

        Picasso.get().load(recipeImage.getImageUrl()).into(recipeImage.getImageView());
        recipeImage.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), GalleryActivity.class);
                startActivityForResult(i, 1);
            }
        });

        addNewRecipe.setOnClickListener(this);
        recipeName.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddRecipeDialogInterface) context;
    }

    @Override
    public void onClick(View v) {
        Recipe recipe = new Recipe(recipeName.getText().toString(), recipeDescription.getText().toString(), recipeImage.getImageUrl(), 1);

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("cheren peper", "100 gr"));

        listener.onFinishAddDialog(recipe, ingredientList);
        dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            recipeImage.setImageUrl(data.getStringExtra("recipeUrl"));
            Picasso.get().load(recipeImage.getImageUrl()).into(recipeImage.getImageView());
        }
    }
}
