package com.claire.candycoded.foodcoded;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by claire on 2017/11/20.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Recipe[] recipes;

    public ImageAdapter(Context mContext, Recipe[] recipes) {
        this.mContext = mContext;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        if (recipes == null){
            return 0;
        }
        return recipes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null){
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(8,8,8,8);
        }else {
            imageView = (ImageView)convertView;
        }

        Picasso.with(mContext).load(recipes[position].image).into(imageView);
        return imageView;
    }
}
