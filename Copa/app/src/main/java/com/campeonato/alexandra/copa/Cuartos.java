package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.campeonato.alexandra.copa.util.Juego;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexandra on 29/06/15.
 */
public class  Cuartos extends Activity {

    private ArrayList<String> grupo1;
    private ArrayList<String> grupo2;
    private ArrayList<String> resultados;
    private ArrayList<String> equipos;
    private ListView lista1;
    private ListView lista2;
    private ListView listaRes;

    private Button btnSemifinales;
    public static Activity cuar;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.cuartos);

        Preliminares.pre.finish();
        cuar = this;

        grupo1 = new ArrayList<String>();
        grupo2 = new ArrayList<String>();
        resultados = new ArrayList<String>();
        equipos = new ArrayList<String>();
        equipos.add("Bolivia");
        equipos.add("Brasil");
        equipos.add("Argentina");
        equipos.add("Peru");
        equipos.add("Chile");
        equipos.add("Paraguay");
        equipos.add("Uruguay");
        equipos.add("Colombia");

        btnSemifinales = (Button)findViewById(R.id.btnSemifinales);

        if(!Juego.getCuartos())
        if(Juego.getPreliminares()) {
            jugarTodos(equipos, grupo1, grupo2, resultados);
            Juego.setCuartos(true);
        }
        setVistas();
    }

    public void startSemifinales(View v){
        startActivity( new Intent(this, Semifinales.class));
    }

    private List<String> jugarTodos(ArrayList<String> e, ArrayList<String> gru1,
                                    ArrayList<String> gru2, ArrayList<String> result) {
        return Juego.jugarTodos(e, gru1, gru2, result);
    }

    public void setVistas(){
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                grupo1.toArray(new String[grupo1.size()]));
        lista1 = (ListView)findViewById(R.id.list1);
        lista1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                resultados.toArray(new String[resultados.size()]));
        listaRes = (ListView)findViewById(R.id.listRes);
        listaRes.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                grupo2.toArray(new String[grupo2.size()]));
        lista2 = (ListView)findViewById(R.id.list2);
        lista2.setAdapter(adapter3);
    }
}
