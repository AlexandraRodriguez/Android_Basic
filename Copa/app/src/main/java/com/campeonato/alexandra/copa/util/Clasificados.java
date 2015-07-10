package com.campeonato.alexandra.copa.util;

import android.database.Cursor;

import com.campeonato.alexandra.copa.Preliminares;
import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;

import java.util.ArrayList;

public class Clasificados {

    public static void setClasificados(EquiposDataBaseManager manager){
        ArrayList<String> equipos = new ArrayList<String>();
        ArrayList<Integer> puntos = new ArrayList<Integer>();
        Cursor c = manager.getTodosLosPuntajes();
        if(c.moveToFirst())
            do{
                equipos.add(c.getString(0));
                puntos.add(c.getInt(1));
            }while (c.moveToNext());


        String equipo;
        int i = 0; int pos = 0;
        while(i<4){
            pos = getPeorPuntaje(puntos);
            equipo = equipos.remove(pos);
            manager.cambiarClasificado(equipo, 0);
            i++;
        }
    }

    public static int getPeorPuntaje(ArrayList<Integer> puntos){
        int pos = -1; int min;
        int tam = puntos.size();
        if(tam > 0){
            min = puntos.get(0);
            for(int i = 0; i<tam; i++){
                if(puntos.get(i)<=min){
                    min = puntos.get(i);
                    pos = i;
                }
            }
            puntos.remove(pos);
        }
        return pos;
    }
}
