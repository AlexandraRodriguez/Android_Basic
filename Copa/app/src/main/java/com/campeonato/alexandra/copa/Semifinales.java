package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;
import com.campeonato.alexandra.copa.util.Juego;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 29/06/15.
 */
public class Semifinales extends Activity {

    private ArrayList<String> grupo1;
    private ArrayList<String> grupo2;
    private ArrayList<String> resultados;
    private ListView lista1;
    private ListView lista2;
    private ListView listaRes;

    private Button btnFinal;
    public static Activity semifinalesActivity;

    EquiposDataBaseManager manager;
    private ArrayList<String> equipos;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.semifinales);

        Cuartos.cuartosActivity.finish();
        semifinalesActivity = this;

        grupo1 = new ArrayList<String>();
        grupo2 = new ArrayList<String>();
        resultados = new ArrayList<String>();

        manager = new EquiposDataBaseManager(this);
        equipos = new ArrayList<String>();

        Cursor c = manager.getClasificados();
        if(c.moveToFirst()){
            do{
                equipos.add(c.getString(0));
            }while (c.moveToNext());
        }

        btnFinal = (Button)findViewById(R.id.btnFinal);


        Juego.jugarTodos(equipos, grupo1, grupo2, resultados, manager);

        setVistas();
    }

    public void startFinal(View v){
        startActivity( new Intent(this, Final.class));
    }


    public void setVistas(){
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                grupo1.toArray(new String[grupo1.size()]));
        lista1 = (ListView)findViewById(R.id.listSemi1);
        lista1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                resultados.toArray(new String[resultados.size()]));
        listaRes = (ListView)findViewById(R.id.listSemiRes);
        listaRes.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                grupo2.toArray(new String[grupo2.size()]));
        lista2 = (ListView)findViewById(R.id.listSemi2);
        lista2.setAdapter(adapter3);
    }
}

