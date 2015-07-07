package com.ale.clima.util;

/**
 * Created by LUIS on 05/07/2015.
 */
public class Conversor {

    public static int kelvinToCelsius(double kelvin){

        return (int)(kelvin - 273.15);
    }

    private static String ciudad;

    public static void setCiudad(String ciudadElegida){
        ciudad = ciudadElegida;
    }

    public static String getCiudad(){
        return ciudad;
    }
}
