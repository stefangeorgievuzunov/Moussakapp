package com.example.moussakapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.moussakapp.adapters.LoadRecipeImageListener;
import com.example.moussakapp.adapters.RecipeImageAdapter;
import com.example.moussakapp.fragments.AddRecipeFragment;

public class GalleryActivity extends AppCompatActivity implements LoadRecipeImageListener {
    RecipeImageAdapter recipeImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView recyclerView = findViewById(R.id.recipeGallery);
        recipeImageAdapter = new RecipeImageAdapter();
        recyclerView.setAdapter(recipeImageAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public void setImgUrl(String url) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("recipeUrl",url);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        this.onBackPressed();
    }
}
