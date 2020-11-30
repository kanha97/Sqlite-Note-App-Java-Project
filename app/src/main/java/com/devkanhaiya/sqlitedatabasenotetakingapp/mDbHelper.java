package com.devkanhaiya.sqlitedatabasenotetakingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class mDbHelper extends SQLiteOpenHelper {

    public static final String Table_NAme ="COUNTTRIES";

    public static final String _ID ="_id";
    public static final String SUBJECT ="subject";
    public static final String DESC ="description";

    public static final String Dbname ="COUNTRIES.DB";
    public static final int Dbversion =1;

    public static final String CRETE_TABLE ="create table " +Table_NAme +"("+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SUBJECT +" TEXT NOT NULL,"+DESC + " TEXT);";

    public mDbHelper(@Nullable Context context) {
        super(context, Dbname, null, Dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
  db.execSQL(CRETE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_NAme);
        onCreate(db);
    }
}
