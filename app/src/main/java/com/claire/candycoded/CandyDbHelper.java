package com.claire.candycoded;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by claire on 2017/11/21.
 */

public class CandyDbHelper extends SQLiteOpenHelper {

//    public CandyDbHelper(Context context, String name,
//                         SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    public CandyDbHelper(Context context){
        super(context,
                CandyContract.DB_NAME,
                null,
                CandyContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandyContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CandyContract.SQL_DELETE_ENTRES);
        onCreate(db);
    }
}
