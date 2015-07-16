package com.example.ale.groceryshoppingassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class URLDataBaseManager {
    public static final String TABLE_NAME= "DIRECCIONES";

    public static final String _ID = "_id";
    public static final String CN_NAME = "name";
    public static final String CN_URL = "url";

    public static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CN_NAME + " TEXT NOT NULL,"
            + CN_URL + " INTEGER NOT NULL);";


    private URLDBHelper helper;
    private SQLiteDatabase db;

    private ContentValues generarContentValues(String name, String url){
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_URL, url);

        return values;
    }

    public URLDataBaseManager(Context context) {
        helper = new URLDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertar(String name, String url){

        db.insert(TABLE_NAME, null, generarContentValues(name, url));
    }

    public String getURL(String name){
        String res = "";
        String[] columns = new String[]{CN_URL};
        Cursor c = db.query(TABLE_NAME, columns, CN_NAME + "=?", new String[]{name}, null, null, null);
        if(c.moveToFirst())
            do{
                res = c.getString(0);
            }while(c.moveToNext());
        return res;
    }


    private class URLDBHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = "URLs";
        private static final int DB_SCHEME_VERSION = 1;


        public URLDBHelper(Context context) {
            super(context, DB_NAME, null, DB_SCHEME_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(URLDataBaseManager.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}


