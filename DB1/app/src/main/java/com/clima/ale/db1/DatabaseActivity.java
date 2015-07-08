package com.clima.ale.db1;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class DatabaseActivity extends ListActivity {

    private DatabaseOpenHelper helper;
    private SimpleCursorAdapter adapter;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main);

        helper = new DatabaseOpenHelper(this);

        clearAll();

        insertEquipos();

        Cursor c = readEquipos();
        adapter = new SimpleCursorAdapter(this, R.layout.list_layout, c,
                DatabaseOpenHelper.columns, new int[]{R.id.equipo, R.id.puntaje}, 0);

        setListAdapter(adapter);
    }

    private void insertEquipos(){
        ContentValues values = new ContentValues();

        values.put(DatabaseOpenHelper._EQUIPO, "Bolivia");
        values.put(DatabaseOpenHelper.PUNTAJE, 0);
        helper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, values);
    }

    private Cursor readEquipos(){
        return helper.getWritableDatabase().query(DatabaseOpenHelper.TABLE_NAME,
                DatabaseOpenHelper.columns, null, new String[]{}, null, null, null);
    }

    private void clearAll(){
        helper.getWritableDatabase().delete(DatabaseOpenHelper.TABLE_NAME, null, null);
    }
}