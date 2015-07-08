package com.clima.ale.db1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHelper extends SQLiteOpenHelper {

    final static String TABLE_NAME = "registro";
    final static String _EQUIPO = "equipo";
    final static String PUNTAJE = "puntaje";
    final static String[] columns = {_EQUIPO, PUNTAJE};

    private final static String CREATE_CMD =

            "CREATE TABLE registro (" + _EQUIPO
                    + " TEXT PRIMARY KEY, "
                    + PUNTAJE + "INTEGER NOT NULL)";

    private final static String NAME = "registro_db";
    private final static Integer VERSION = 1;
    private final Context context;

    public DatabaseOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
