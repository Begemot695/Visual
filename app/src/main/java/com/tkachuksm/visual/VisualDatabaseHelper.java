package com.tkachuksm.visual;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BG on 06.02.2017.
 */

public class VisualDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "visual";
    private static final int DB_VERSION = 1;

    public VisualDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE FRUITS (_id INTEGER PRIMARY KEY AUTOINCREMENT,FRUIT TEXT); ");


        db.execSQL("CREATE TABLE USER ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PASSWORD TEXT); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
