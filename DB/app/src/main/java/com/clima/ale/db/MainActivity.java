package com.clima.ale.db;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import db.EquiposDataBaseManager;


public class MainActivity extends ListActivity {

    private EquiposDataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        manager = new EquiposDataBaseManager(this);
        //crearRegistro();
        manager.insertar("Bolivia", 0, 1);
        manager.insertar("Brasil", 0, 1);
        manager.insertar("Argentina", 0, 1);
        cursor = manager.cargarCursorRegistro();
        lista = (ListView)findViewById(R.id.listViewBase);
        String[] from = new String[]{manager.CN_EQUIPO, manager.CN_PUNTAJE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,cursor, from, to,0);
        lista.setAdapter(cursorAdapter);


    }

    public void crearRegistro(){
        manager.insertar("Bolivia", 0, 1);
        manager.insertar("Brasil", 0, 1);
        manager.insertar("Argentina", 0, 1);
        manager.insertar("Paraguay", 0, 1);
        manager.insertar("Uruguay", 0, 1);
        manager.insertar("Chile", 0, 1);
        manager.insertar("Colombia", 0, 1);
        manager.insertar("Venezuela", 0, 1);
        manager.insertar("Ecuador", 0, 1);
        manager.insertar("Mexico", 0, 1);
        manager.insertar("Peru", 0, 1);
        manager.insertar("Jamaica", 0, 1);
    }



}
