package com.example.moussakapp.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moussakapp.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private TextView recipeTitle;
    private TextView recipeIngredients;
    private TextView recipeDescription;
    private ImageView recipeImg;

    public RecipeViewHolder(@NonNull View itemView){
        super(itemView);

        recipeTitle=itemView.findViewById(R.id.recipeTitle);
        recipeIngredients=itemView.findViewById(R.id.recipeIngredients);
        recipeDescription=itemView.findViewById(R.id.recipeDescription);
        recipeImg=itemView.findViewById(R.id.recipeImg);
    }
    public TextView getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle.setText(recipeTitle);
    }

    public TextView getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients.setText(recipeIngredients);
    }

    public TextView getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription.setText(recipeDescription);
    }

    public ImageView getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(ImageView recipeImg) {
        this.recipeImg = recipeImg;
    }
}
