package com.campeonato.alexandra.copa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ale on 01/07/2015.
 */
public class EquiposSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Equipos (equipo TEXT, puntaje INTEGER, clasificado )";

    public EquiposSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Equipos");
        db.execSQL(sqlCreate);
    }
}
