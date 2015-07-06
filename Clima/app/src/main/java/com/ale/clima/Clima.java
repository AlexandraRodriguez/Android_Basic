package com.ale.clima;

import com.ale.clima.util.Conversor;

/**
 * Created by LUIS on 05/07/2015.
 */
public class Clima {
    private double tempActual;
    private double tempMaxima;
    private double tempMinima;
    private int humedad;
    private int presion;

    public Clima(double tempActual, double tempMaxima, double tempMinima, int humedad, int presion){
        this.tempActual = tempActual;
        this.tempMaxima = tempMaxima;
        this.tempMinima = tempMinima;
        this.humedad = humedad;
        this.presion = presion;
    }

    public void setTempActual(double tempActual) {
        this.tempActual = tempActual;
    }

    public void setTempMaxima(double tempMaxima) {
        this.tempMaxima = tempMaxima;
    }

    public void setTempMinima(double tempMinima) {
        this.tempMinima = tempMinima;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }

    public String getTempActual() {
        return Conversor.kelvinToCelsius(tempActual) + " °C";
    }

    public String getTempMaxima() {
        return Conversor.kelvinToCelsius(tempMaxima) + " °C";
    }

    public String getTempMinima() {
        return Conversor.kelvinToCelsius(tempMinima) + " °C";
    }

    public String getHumedad() {
        return humedad+" %";
    }

    public String getPresion() {
        return presion+" mb";
    }

}
