package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;
import com.campeonato.alexandra.copa.util.Juego;

import java.util.ArrayList;

public class Final extends Activity {

    private ArrayList<String> grupo1;
    private ArrayList<String> grupo2;
    private ArrayList<String> resultados;
    private String ganador;
    private TextView eq1;
    private TextView eq2;
    private TextView res;
    private TextView winner;

    EquiposDataBaseManager manager;
    private ArrayList<String> equipos;


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.la_final);

        Semifinales.semifinalesActivity.finish();

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

        eq1 = (TextView)findViewById(R.id.textFinal1);
        eq2 = (TextView)findViewById(R.id.textFinal2);
        res = (TextView)findViewById(R.id.textFinalRes);
        winner = (TextView)findViewById(R.id.textGanador);

        ganador = jugar();

        setVistas();
    }

    public void startMain(View v){
        //this.deleteDatabase(manager.TABLE_NAME);
        startActivity( new Intent(this, MainActivity.class));
        finishFinal(v);
    }

    public void finishFinal(View v){
        Final.this.finish();
        //this.deleteDatabase(manager.TABLE_NAME);
    }

    public String jugar(){
        String e1 = equipos.remove(0);
        String e2 = equipos.remove(0);
        return Juego.jugar(e1, e2, grupo1, grupo2, resultados, manager);
    }

    public void setVistas(){
        eq1.setText(grupo1.remove(0));
        eq2.setText(grupo2.remove(0));
        res.setText(resultados.remove(0));
        winner.setText(ganador);
    }
}
