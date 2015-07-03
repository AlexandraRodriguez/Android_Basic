package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.campeonato.alexandra.copa.util.Juego;

import java.util.ArrayList;

/**
 * Created by Alexandra on 30/06/15.
 */
public class Final extends Activity {

    private ArrayList<String> grupo1;
    private ArrayList<String> grupo2;
    private ArrayList<String> resultados;
    private ArrayList<String> equipos;
    private String ganador;
    private TextView eq1;
    private TextView eq2;
    private TextView res;
    private TextView winner;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.la_final);

        grupo1 = new ArrayList<String>();
        grupo2 = new ArrayList<String>();
        resultados = new ArrayList<String>();
        equipos = new ArrayList<String>();
        equipos.add("Argentina");
        equipos.add("Chile");

        eq1 = (TextView)findViewById(R.id.textFinal1);
        eq2 = (TextView)findViewById(R.id.textFinal2);
        res = (TextView)findViewById(R.id.textFinalRes);
        winner = (TextView)findViewById(R.id.textGanador);

        if(!Juego.getFinales())
        if(Juego.getSemifinales()) {
            ganador = jugar();
            Juego.setFinales(true);
        }
        setVistas();
    }

    public String jugar(){
        String e1 = equipos.remove(0);
        String e2 = equipos.remove(0);
        return Juego.jugar(e1, e2, grupo1, grupo2, resultados);
    }

    public void setVistas(){
        eq1.setText(grupo1.remove(0));
        eq2.setText(grupo2.remove(0));
        res.setText(resultados.remove(0));
        winner.setText(ganador);
    }
}
