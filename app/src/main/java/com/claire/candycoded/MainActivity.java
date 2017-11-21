package com.claire.candycoded;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.claire.candycoded.foodcoded.GridViewActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private final String CANDY_PRODUCT_URL = "https://s3.amazonaws.com/courseware.codeschool.com/super_sweet_android_time/API/CandyCoded.json";
    private Candy[] candies;
    private CandyCursorAdapter adapter;
    private CandyDbHelper candyDbHelper = new CandyDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view_title);
        textView.setText(R.string.product);

//        final ArrayList<String> candy_list = new ArrayList<>();
//        candy_list.add("Tropical Wave");
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                R.layout.list_item_candy,
//                R.id.text_view_candy,
//                candy_list);

        SQLiteDatabase db = candyDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
        adapter = new CandyCursorAdapter(this, cursor);
        ListView listView = findViewById(R.id.list_view_candy);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
//                detailIntent.putExtra("candy_name", candies[position].name);
//                detailIntent.putExtra("candy_image", candies[position].image);
//                detailIntent.putExtra("candy_price", candies[position].price);
//                detailIntent.putExtra("candy_desc", candies[position].description);
                detailIntent.putExtra("position", position);
                startActivity(detailIntent);
               // Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(CANDY_PRODUCT_URL, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("AsyncHttpClient", "onFailure: " + responseString );
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                //Log.d("AsyncHttpClient", "onSuccess: " + responseString);
                Gson gson = new GsonBuilder().create();
                candies = gson.fromJson(responseString, Candy[].class);
//                adapter.clear();
//                for (Candy candy : candies){
//                    adapter.add(candy.name);
//                }
                //將json資料新增到Database
                addCandiesToDatabase(candies);

                //Updating our CursorAdapter with the latest Database entries
                //使用最新的數據庫條目更新我們的CursorAdapter
                SQLiteDatabase db = candyDbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
                adapter.changeCursor(cursor);
            }
        });
    }

    private void addCandiesToDatabase(Candy[] candies) {
        SQLiteDatabase db = candyDbHelper.getWritableDatabase();
        for(Candy candy : candies){
            ContentValues values = new ContentValues();
            values.put(CandyContract.CandyEntry.COLUMN_NAME_NAME, candy.name);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_PRICE, candy.price);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_DESC, candy.description);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_IMAGE, candy.image);

            db.insert(CandyContract.CandyEntry.TABLE_NAME, null, values);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.food_action:
                startActivity(new Intent(MainActivity.this, GridViewActivity.class));
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
}
