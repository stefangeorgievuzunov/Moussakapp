package com.example.moussakapp.holders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moussakapp.R;

public class RecipeImageViewHolder  extends RecyclerView.ViewHolder {
    private ImageView recipeImageItem;

    public RecipeImageViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeImageItem=itemView.findViewById(R.id.recipeImgItem);
    }

    public ImageView getRecipeImageItem() {
        return recipeImageItem;
    }

    public void setRecipeImageItem(ImageView recipeImageItem) {
        this.recipeImageItem = recipeImageItem;
    }
}
