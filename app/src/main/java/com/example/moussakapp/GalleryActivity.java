package com.example.moussakapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.moussakapp.adapters.LoadRecipeImageListener;
import com.example.moussakapp.adapters.RecipeImageAdapter;

public class GalleryActivity extends AppCompatActivity implements LoadRecipeImageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        RecyclerView recyclerView = findViewById(R.id.recipeGallery);
        recyclerView.setAdapter(new RecipeImageAdapter());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void setImgUrl(String url) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("recipeUrl", url);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        this.onBackPressed();
    }
}
