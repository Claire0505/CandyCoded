package com.claire.candycoded.foodcoded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.claire.candycoded.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class GridViewActivity extends AppCompatActivity {

//    private Integer[] imageList = {
//            R.drawable._15,
//            R.drawable._23,
//            R.drawable._26,
//            R.drawable._44
//    };

    Recipe[] recipes;
    ImageAdapter imageAdapter;
    GridView gridView;

    private final String FOODURL = "https://s3.amazonaws.com/courseware.codeschool.com/super_sweet_android_time/API/TinyBytes.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        imageAdapter = new ImageAdapter(this, recipes);

        gridView = (GridView)this.findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent recipeIntent = new Intent(GridViewActivity.this, RecipeActivity.class);
                recipeIntent.putExtra("recipe_name", recipes[i].name);
                recipeIntent.putExtra("recipe_image", recipes[i].image);
                recipeIntent.putExtra("recipe_ingredients", recipes[i].ingredients);
                recipeIntent.putExtra("recipe_directions", recipes[i].directions);
                startActivity(recipeIntent);

            }
        });

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(FOODURL,
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("AsyncHttpClient","response:" + responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("AsyncHttpClient", responseString);
                        Gson gson = new GsonBuilder().create();
                        recipes = gson.fromJson(responseString, Recipe[].class);

                        for (Recipe recipe : recipes) {
                            Log.d("AsyncHttpClient", recipe.name);
                        }

                        imageAdapter = new ImageAdapter(GridViewActivity.this, recipes);
                        gridView.setAdapter(imageAdapter);
                    }
                }
        );
    }
}
