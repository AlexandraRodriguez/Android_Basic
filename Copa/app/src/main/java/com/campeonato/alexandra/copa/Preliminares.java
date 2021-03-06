package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;
import com.campeonato.alexandra.copa.util.Clasificados;
import com.campeonato.alexandra.copa.util.Juego;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Preliminares extends Activity {

    private ArrayList<String> grupo1;
    private ArrayList<String> grupo2;
    private ArrayList<String> resultados;
    private ListView lista1;
    private ListView lista2;
    private ListView listaRes;
    private ArrayList<String> grupoA;
    private ArrayList<String> grupoB;
    private ArrayList<String> grupoC;

    private List<String> equipos;
    EquiposDataBaseManager manager;

    private Button btnCuartos;
    public static Activity preliminaresActivity;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.preliminares);

        MainActivity.main.finish();
        preliminaresActivity = this;

        grupo1 = new ArrayList<String>();
        grupo2 = new ArrayList<String>();
        resultados = new ArrayList<String>();
        grupoA = new ArrayList<String>();
        grupoB = new ArrayList<String>();
        grupoC = new ArrayList<String>();
        btnCuartos = (Button)findViewById(R.id.btnCuartos);

        manager = new EquiposDataBaseManager(this);

        equipos = new ArrayList<String>();

        Cursor c = manager.getClasificados();
        if(c.moveToFirst()){
            do{
                equipos.add(c.getString(0));
            }while (c.moveToNext());
        }

        agrupar();
        Juego.jugarPorGrupo(grupoA, grupo1, grupo2, resultados, manager);
        Juego.jugarPorGrupo(grupoB, grupo1, grupo2, resultados, manager);
        Juego.jugarPorGrupo(grupoC, grupo1, grupo2, resultados, manager);

        Clasificados.setClasificados(manager);
        /*manager.cambiarClasificado("Bolivia", 0);
        manager.cambiarClasificado("Mexico", 0);
        manager.cambiarClasificado("Ecuador", 0);
        manager.cambiarClasificado("Jamaica", 0);*/

        setVistas();
    }

    public void startCuartos(View v){
        startActivity( new Intent(this, Cuartos.class));
    }

    public void agrupar(){
        int a = 4; int b = 4; int c = 4;
        int tam = 12;
        int i = 0;
        Random r = new Random();
        while(!equipos.isEmpty()){
            i = r.nextInt(tam);
            tam--;
            if(a>0){
                grupoA.add(equipos.remove(i));
                a--;
            }else{
                if(b>0){
                    grupoB.add(equipos.remove(i));
                    b--;
                }else{
                    if(c>0){
                        grupoC.add(equipos.remove(i));
                        c--;
                    }
                }
            }
        }
    }

    public void setVistas(){
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                grupo1.toArray(new String[grupo1.size()]));
        lista1 = (ListView)findViewById(R.id.listPre1);
        lista1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                resultados.toArray(new String[resultados.size()]));
        listaRes = (ListView)findViewById(R.id.listPreRes);
        listaRes.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                grupo2.toArray(new String[grupo2.size()]));
        lista2 = (ListView)findViewById(R.id.listPre2);
        lista2.setAdapter(adapter3);
    }

}
