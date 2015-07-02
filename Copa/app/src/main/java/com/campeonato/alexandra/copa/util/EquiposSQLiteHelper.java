package com.campeonato.alexandra.copa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EquiposSQLiteHelper extends SQLiteOpenHelper {

    final static String TABLE_NAME = "Registro";
    final static String PAIS = "equipo";
    final static String PUNTOS = "puntaje";
    final static String CLASIFICACION = "clasificado";
    final static String[] columns = {PAIS, PUNTOS, CLASIFICACION};

    final private static String CREATE_DB =

    "CREATE TABLE equipo (" +  PAIS + " TEXT PRIMARY KEY, "
            + PUNTOS + "INTEGER, "
            + CLASIFICACION + "INTEGER)";

    final private static String NAME = "equipos_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public EquiposSQLiteHelper(Context context) {
        super(context,NAME, null, VERSION);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Equipos");
        db.execSQL(CREATE_DB);
    }

}
