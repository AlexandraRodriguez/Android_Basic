package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.campeonato.alexandra.copa.db.EquiposDBHelper;
import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;

public class MainActivity extends Activity {

    private Button btnPreliminares;
    public static Activity main;
    private EquiposDataBaseManager manager;

    //private EquiposDataBaseManager manager;
    //private Cursor cursor;
    //private ListView lista;
    //private SimpleCursorAdapter cursorAdapter;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);
        main = this;
        btnPreliminares = (Button)findViewById(R.id.btnPreliminares);

        manager = new EquiposDataBaseManager(this);
        crearRegistro();
        
        //cursor = manager.cargarCursorRegistro();
        //lista = (ListView)findViewById(R.id.listViewBase);
        //String[] from = new String[]{manager.CN_EQUIPO, manager.CN_PUNTAJE};
        //int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        //cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,cursor, from, to,0);
        //lista.setAdapter(cursorAdapter);
    }

    public void startPreliminares(View v){
        startActivity( new Intent(this, Preliminares.class));
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
