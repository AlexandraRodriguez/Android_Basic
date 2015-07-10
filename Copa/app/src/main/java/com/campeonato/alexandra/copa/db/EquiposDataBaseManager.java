package com.campeonato.alexandra.copa.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EquiposDataBaseManager {
    public static final String TABLE_NAME= "REGISTRO";

    public static final String _ID = "_id";
    public static final String CN_EQUIPO = "equipo";
    public static final String CN_PUNTAJE = "puntaje";
    public static final String CN_CLASIFICADO = "clasificado";

    public static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CN_EQUIPO + " TEXT,"
            + CN_PUNTAJE + " INTEGER NOT NULL,"
            + CN_CLASIFICADO + " INTEGER NOT NULL);";


    private EquiposDBHelper helper;
    private SQLiteDatabase db;

    private ContentValues generarContentValues(String equipo, int puntaje, int clasificado){
        ContentValues values = new ContentValues();
        values.put(CN_EQUIPO, equipo);
        values.put(CN_PUNTAJE, puntaje);
        values.put(CN_CLASIFICADO, clasificado);

        return values;
    }

    public EquiposDataBaseManager(Context context) {
        helper = new EquiposDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertar(String equipo, int puntaje, int clasificado){

        db.insert(TABLE_NAME, null, generarContentValues(equipo, puntaje, clasificado));
    }

    public int getPuntaje(String equipo){

        int res=-1;
        String[] columnas = new String[]{CN_PUNTAJE};
        Cursor c = db.query(TABLE_NAME, columnas, CN_EQUIPO + "=?", new String[]{equipo}, null, null, null);
        if(c.moveToFirst())
            do{
                res = c.getInt(0);
            }while (c.moveToNext());
        return res;
    }

    public Cursor getTodosLosPuntajes(){
        String[] columnas = new String[]{CN_EQUIPO,CN_PUNTAJE};
        return db.query(TABLE_NAME, columnas, null, null, null, null, null);
    }

    public Cursor getClasificados(){

        String[] columnas = new String[]{CN_EQUIPO};
        return db.query(TABLE_NAME, columnas, CN_CLASIFICADO + "=?", new String[]{"1"}, null, null, null);


        //Cursor c = db.rawQuery(" SELECT codigo,nombre FROM Usuarios WHERE nombre='usu1' ", null);
    }

    public void cambiarClasificado(String equipo, int nuevoClasificado){
        ContentValues values = new ContentValues();
        values.put(CN_CLASIFICADO, nuevoClasificado);
        db.update(TABLE_NAME, values, CN_EQUIPO + "=?", new String[]{equipo});
    }

    public void cambiarPuntaje(String equipo, int nuevoPuntaje){

        ContentValues values = new ContentValues();
        values.put(CN_PUNTAJE, nuevoPuntaje);
        db.update(TABLE_NAME, values, CN_EQUIPO + "=?", new String[]{equipo});
    }


}
