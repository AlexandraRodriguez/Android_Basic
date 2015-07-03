package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.campeonato.alexandra.copa.util.Juego;
import java.util.ArrayList;
import java.util.Random;

public class Preliminares extends Activity {

    private ArrayList<String> grupo1;
    private ArrayList<String> grupo2;
    private ArrayList<String> resultados;
    private ArrayList<String> equipos;
    private ListView lista1;
    private ListView lista2;
    private ListView listaRes;
    private ArrayList<String> grupoA;
    private ArrayList<String> grupoB;
    private ArrayList<String> grupoC;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.preliminares);

        grupo1 = new ArrayList<String>();
        grupo2 = new ArrayList<String>();
        resultados = new ArrayList<String>();
        grupoA = new ArrayList<String>();
        grupoB = new ArrayList<String>();
        grupoC = new ArrayList<String>();
        equipos = new ArrayList<String>();
        equipos.add("Bolivia");
        equipos.add("Brasil");
        equipos.add("Argentina");
        equipos.add("Peru");
        equipos.add("Chile");
        equipos.add("Paraguay");
        equipos.add("Uruguay");
        equipos.add("Colombia");
        equipos.add("Mexico");
        equipos.add("Venezuela");
        equipos.add("Jamaica");
        equipos.add("Ecuador");

        if(!Juego.getPreliminares()) {
            agrupar();
            Juego.jugarPorGrupo(grupoA, grupo1, grupo2, resultados);
            Juego.jugarPorGrupo(grupoB, grupo1, grupo2, resultados);
            Juego.jugarPorGrupo(grupoC, grupo1, grupo2, resultados);
            Juego.setPreliminares(true);
        }
        setVistas();
    }

    public void agrupar(){
        int a = 4; int b = 4; int c = 4;
        int tam = 12; int i;
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
