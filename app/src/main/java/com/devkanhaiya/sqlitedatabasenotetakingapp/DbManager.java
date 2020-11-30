package com.devkanhaiya.sqlitedatabasenotetakingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

   public class DbManager {


  private mDbHelper dbHelper;
   private Context context;
    private SQLiteDatabase database;


    public DbManager(Context context) {
        this.context = context;
    }

    public  DbManager open() throws SQLException{
        dbHelper=new mDbHelper(context);
        database=dbHelper.getWritableDatabase();
        return this;
        }

    public void close(){
        database.close();
    }

    public Cursor fetch(){
        String[] columns = new String[]{mDbHelper._ID,mDbHelper.SUBJECT,mDbHelper.DESC};
        Cursor cursor=database.query(mDbHelper.Table_NAme,
                columns,null,null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void insert(String subject,String desc){
        ContentValues values = new ContentValues();
        values.put(mDbHelper.SUBJECT,subject);
        values.put(mDbHelper.DESC,desc);
        database.insert(mDbHelper.Table_NAme,null,values);
    }
    public int update(long id,String subject,String desc){
        ContentValues values=new ContentValues();
        values.put(mDbHelper.SUBJECT,subject);
        values.put(mDbHelper.DESC,desc);
        int i = database.update(mDbHelper.Table_NAme,values,mDbHelper._ID+"="+id,null);
        return i;
    }

    public void delete(long id){
        database.delete(mDbHelper.Table_NAme,mDbHelper._ID+"="+id,null);
    }
}
