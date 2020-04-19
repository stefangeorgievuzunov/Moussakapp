package com.example.moussakapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moussakapp.Data.resources.RecipeGallery;
import com.example.moussakapp.R;
import com.example.moussakapp.holders.RecipeImageViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeImageAdapter extends RecyclerView.Adapter<RecipeImageViewHolder>{
    private Context context;
    private List<String> recipeImageList;
    private LoadRecipeImageListener listener;

    public RecipeImageAdapter() {
        this.recipeImageList = RecipeGallery.getAllUrls();
    }

    @NonNull
    @Override
    public RecipeImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        listener=(LoadRecipeImageListener) context;

        LayoutInflater inflater = LayoutInflater.from(context);
        View recipeView = inflater.inflate(R.layout.recipe_image_item, parent, false);
        RecipeImageViewHolder viewHolder = new RecipeImageViewHolder(recipeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeImageViewHolder holder, int position) {
        final String recipeImageUrl=recipeImageList.get(position);
        Picasso.get().load(recipeImageUrl).into(holder.getRecipeImageItem());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setImgUrl(recipeImageUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeImageList.size();
    }

}
