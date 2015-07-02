package com.campeonato.alexandra.copa.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 29/06/15.
 */
public class Juego {

    public static void jugarPorGrupo(List<String> grupo,List<String> g1,List<String> g2,List<String> res ){
        String e1 = grupo.remove(0); String e2 = grupo.remove(0);
        String e3 = grupo.remove(0); String e4 = grupo.remove(0);

        jugar(e1, e2, g1, g2, res);
        jugar(e2, e3, g1, g2, res);
        jugar(e4, e1, g1, g2, res);
        jugar(e3, e4, g1, g2, res);
        jugar(e1, e3, g1, g2, res);
        jugar(e4, e2, g1, g2, res);
    }

    public static List<String> jugarTodos(List<String> ini,List<String> g1,List<String> g2,List<String> res ){
        List<String> listFinal = new ArrayList<String>();
        int tam = ini.size()/2;
        for(int i=1; i<=tam;i++){
            String e1 = ini.remove(0);
            String e2 = ini.remove(0);
            listFinal.add(jugar(e1, e2, g1, g2, res));

        }

        return listFinal;
    }

    public static String jugar(String e1, String e2, List<String> g1, List<String> g2,List<String> res){
        String result;
        int p1=0; int p2=0;
        while(p1==p2){
            p1=(int)(Math.random()*6);
            p2=(int)(Math.random()*6);
        }
        if(p1>p2)
            result=e1;
        else
            result=e2;
        g1.add(e1);
        g2.add(e2);
        res.add(p1 + " - " + p2);

        return result;

    }
}
