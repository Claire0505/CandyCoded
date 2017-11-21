package com.claire.candycoded.foodcoded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.claire.candycoded.R;
import com.squareup.picasso.Picasso;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = this.getIntent();
        String foodName = "";
        if (intent.hasExtra("recipe_name")) {
            foodName = intent.getStringExtra("recipe_name");
        }
        TextView textView = (TextView)findViewById(R.id.text_view_recipe_name);
        textView.setText(foodName);

        String recipeIngredients = "";
        if (intent.hasExtra("recipe_ingredients")) {
            recipeIngredients = intent.getStringExtra("recipe_ingredients");
        }
        TextView textViewIngredients = (TextView)findViewById(R.id.text_view_ingredients);
        textViewIngredients.setText(recipeIngredients);

        String recipeDirections = "";
        if (intent.hasExtra("recipe_directions")) {
            recipeDirections = intent.getStringExtra("recipe_directions");
        }
        TextView textViewDirections = (TextView)findViewById(R.id.text_view_directions);
        textViewDirections.setText(recipeDirections);

        String recipeImage = "";
        if (intent.hasExtra("recipe_image")) {
            recipeImage = intent.getStringExtra("recipe_image");
        }
        ImageView imageView = (ImageView)findViewById(R.id.image_view_recipe);
        Picasso.with(this).load(recipeImage).into(imageView);
    }
}
