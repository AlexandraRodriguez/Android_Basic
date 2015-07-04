package com.campeonato.alexandra.copa.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ale on 03/07/2015.
 */
public class EquiposDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CopaAmerica";
    private static final int DB_SCHEME_VERSION = 1;


    public EquiposDBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EquiposDataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
