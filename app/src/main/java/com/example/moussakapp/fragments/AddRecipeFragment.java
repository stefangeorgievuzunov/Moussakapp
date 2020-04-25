package com.example.moussakapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.moussakapp.Entities.Ingredient;
import com.example.moussakapp.Entities.Recipe;
import com.example.moussakapp.Entities.RecipeWithIngredients;
import com.example.moussakapp.GalleryActivity;
import com.example.moussakapp.R;
import com.example.moussakapp.fragments.entities.RecipeImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddRecipeFragment extends DialogFragment implements View.OnClickListener{
    private EditText recipeName;
    private EditText recipeIngredients;
    private EditText recipeDescription;
    private RecipeImage recipeImage;
    private Button addNewRecipe;
    private TextView invalidFormatMessage;

    private AddRecipeDialogInterface listener;

    private final Pattern pattern = Pattern.compile("^([\\d]{1,3}\\s[\\w]{1,3})\\s(\\S.*)");

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

        invalidFormatMessage = view.findViewById(R.id.invalidFormat);
        recipeName = view.findViewById(R.id.recipeNameFragment);
        recipeIngredients = view.findViewById(R.id.recipeIngredientsFragment);
        recipeDescription = view.findViewById(R.id.recipeDescriptionFragment);
        addNewRecipe = view.findViewById(R.id.addNewRecipeFragment);
        recipeImage.setImageView(view.findViewById(R.id.recipeImageFragment));

        loadImage(recipeImage.getImageUrl());
        recipeImage.getImageView().setOnClickListener(v -> {
            Intent i = new Intent(view.getContext(), GalleryActivity.class);
            startActivityForResult(i, 1);
        });

        recipeIngredients.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                applyPatternOnTextChanged(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        addNewRecipe.setOnClickListener(this);
        recipeName.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onAttach(@NonNull  Context context) {
        super.onAttach(context);
        listener = (AddRecipeDialogInterface) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            recipeImage.setImageUrl(data.getStringExtra("recipeUrl"));
            loadImage(recipeImage.getImageUrl());
        }
    }

    @Override
    public void onClick(View v) {
        if (!recipeName.getText().toString().isEmpty() && !recipeIngredients.getText().toString().isEmpty()) {
            listener.onFinishAddDialog(loadRecipeDataForInsert());
            dismiss();
        } else {
            Toast.makeText(getContext(), "Name and Ingredients fields cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }
    private RecipeWithIngredients loadRecipeDataForInsert(){
        RecipeWithIngredients recipeWithIngredients=new RecipeWithIngredients();
        Recipe recipe = new Recipe(recipeName.getText().toString(), recipeDescription.getText().toString(), recipeImage.getImageUrl(),new Date().getTime());

        recipeWithIngredients.setRecipe(recipe);
        recipeWithIngredients.setIngredients(ingredientsDataSource());

        return recipeWithIngredients;
    }
    private List<Ingredient> ingredientsDataSource() {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] ingredientsInput = recipeIngredients.getText().toString().split("\n");

        for (String input : ingredientsInput) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String quantity = matcher.group(1);
                String name = matcher.group(2);
                ingredients.add(new Ingredient(name, quantity));
            }
        }

        return ingredients;
    }

    private void loadImage(String url){
        if(url!=null){
            Picasso.get().load(url).into(recipeImage.getImageView());
        }
    }

    private void applyPatternOnTextChanged(String s) {
        String[] ingredientsInput = s.split("\n");

        for (String input : ingredientsInput) {
            if (!pattern.matcher(input).matches()) {
                invalidFormatMessage.setVisibility(View.VISIBLE);
                addNewRecipe.setVisibility(View.INVISIBLE);
                break;
            } else {
                invalidFormatMessage.setVisibility(View.INVISIBLE);
                addNewRecipe.setVisibility(View.VISIBLE);
            }
        }
    }


}
