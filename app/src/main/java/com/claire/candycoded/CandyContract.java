package com.claire.candycoded;

import android.provider.BaseColumns;

/**
 * Created by claire on 2017/11/21.
 */

public class CandyContract {
    public static final String DB_NAME = "candycoded.db";
    public static final int DB_VERSION = 1;

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + CandyEntry.TABLE_NAME + " (" +
        CandyEntry._ID + " INTEGER PRIMARY KEY," +
        CandyEntry.COLUMN_NAME_NAME + " TEXT," +
        CandyEntry.COLUMN_NAME_PRICE + " TEXT," +
        CandyEntry.COLUMN_NAME_DESC + " TEXT," +
        CandyEntry.COLUMN_NAME_IMAGE + " TEXT)";

    public static final String SQL_DELETE_ENTRES =
        "DROP TABLE IF EXISTS " + CandyEntry.TABLE_NAME;

    //BaseColumns 會自動產出 final _ID
    public static class CandyEntry implements BaseColumns {
        public static final String TABLE_NAME = "candy";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_DESC = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
    }

}
