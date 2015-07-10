package com.campeonato.alexandra.copa.util;

import android.database.Cursor;

import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class Juego {


    public static void jugarPorGrupo(List<String> grupo,List<String> g1,List<String> g2,List<String> res, EquiposDataBaseManager manager){
        String e1 = grupo.remove(0); String e2 = grupo.remove(0);
        String e3 = grupo.remove(0); String e4 = grupo.remove(0);

        jugarPre(e1, e2, g1, g2, res, manager);
        jugarPre(e2, e3, g1, g2, res, manager);
        jugarPre(e4, e1, g1, g2, res, manager);
        jugarPre(e3, e4, g1, g2, res, manager);
        jugarPre(e1, e3, g1, g2, res, manager);
        jugarPre(e4, e2, g1, g2, res, manager);
    }

    public static void jugarTodos(List<String> ini,List<String> g1,List<String> g2,List<String> res, EquiposDataBaseManager manager){

        int tam = ini.size()/2;
        for(int i=1; i<=tam;i++){
            String e1 = ini.remove(0);
            String e2 = ini.remove(0);
            jugar(e1, e2, g1, g2, res, manager);

        }

    }

    public static String jugar(String e1, String e2, List<String> g1, List<String> g2,List<String> res, EquiposDataBaseManager manager){
        String result;
        int p1=0; int p2=0;
        while(p1==p2){
            p1=(int)(Math.random()*6);
            p2=(int)(Math.random()*6);
        }
        if(p1>p2) {
            manager.cambiarClasificado(e2, 0);
            result = e1;
        }else {
            manager.cambiarClasificado(e1, 0);
            result = e2;
        }
        g1.add(e1);
        g2.add(e2);
        res.add(p1 + " - " + p2);
        return result;
    }

    public static void jugarPre(String e1, String e2, List<String> g1, List<String> g2,List<String> res, EquiposDataBaseManager manager){
        int p1=0; int p2=0;
        int pun1; int pun2;
        p1=(int)(Math.random()*6);
        p2=(int)(Math.random()*6);
        if(p1>p2){
            pun1 = manager.getPuntaje(e1) + 3;
            manager.cambiarPuntaje(e1, pun1);
        }else if(p1<p2){
                pun2 = manager.getPuntaje(e2) + 3;
                manager.cambiarPuntaje(e2, pun2);
            }else{
                pun1 = manager.getPuntaje(e1)+1;
                manager.cambiarPuntaje(e1, pun1);
                pun2 = manager.getPuntaje(e2)+1;
                manager.cambiarPuntaje(e2, pun2);
        }

        g1.add(e1);
        g2.add(e2);
        res.add(p1 + " - " + p2);

    }

}
