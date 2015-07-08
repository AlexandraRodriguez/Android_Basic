package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EquiposDataBaseManager {
    public static final String TABLE_NAME= "Registro";

    public static final String CN_EQUIPO = "equipo";
    public static final String CN_PUNTAJE = "puntaje";
    public static final String CN_CLASIFICADO = "clasificado";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_EQUIPO + " text primary key,"
            + CN_PUNTAJE + " integer not null,"
            + CN_CLASIFICADO + " integer not null);";


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

    public void modificarPuntajeYClasificado(String equipo, int nuevoPuntaje, int clasificado){

        db.update(TABLE_NAME, generarContentValues(equipo, nuevoPuntaje, clasificado), CN_EQUIPO + "=?", new String[]{equipo});
    }

    public Cursor cargarCursorRegistro(){
        //query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        String[] columns = new String[]{CN_EQUIPO, CN_PUNTAJE, CN_CLASIFICADO};
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }
}

