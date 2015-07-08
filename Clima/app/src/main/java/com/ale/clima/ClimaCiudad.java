package com.ale.clima;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ale.clima.util.Conversor;
import com.ale.clima.util.URLPorCiudad;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClimaCiudad extends Activity {
    private TextView tempActual;
    private TextView tempMax;
    private TextView tempMin;
    private TextView humedad;
    private TextView presion;
    private TextView descripcion;
    private String ciudad;
    private TextView textoCiudad;
    private TextView fecha;
    private String url;



    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.clima);

        tempActual = (TextView)findViewById(R.id.tempActual);
        tempMax = (TextView)findViewById(R.id.tempMax);
        tempMin = (TextView)findViewById(R.id.tempMin);
        humedad = (TextView)findViewById(R.id.humedad);
        presion = (TextView)findViewById(R.id.presion);
        descripcion = (TextView)findViewById(R.id.descripcion);

        ciudad = Conversor.getCiudad();
        textoCiudad = (TextView)findViewById(R.id.ciudad_actual);
        textoCiudad.setText(ciudad);

        setURL();
        final String URL = url;
        Button b = (Button)findViewById(R.id.actualizar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute(URL);
            }
        });
    }


    private class HttpGetTask extends AsyncTask<String, Void, Clima> {
        //private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Cochabamba&lang=es";
        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected Clima doInBackground(String... params) {
            HttpGet request = new HttpGet(params[0]);
            JSONResponseHandler responseHandler = new JSONResponseHandler();
            try {
                return mClient.execute(request, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Clima result) {
            if (null != mClient)
                mClient.close();
            tempActual.setText(result.getTempActual());
            tempMax.append(result.getTempMaxima());
            tempMin.append(result.getTempMinima());
            humedad.append(result.getHumedad());
            presion.append(result.getPresion());
            descripcion.setText(result.getDescripcion());
        }
    }


    public void setURL(){
        switch (ciudad){
            case "Cochabamba":
                url = URLPorCiudad.getURLCbba();
                break;
            case "Sucre":
                url = URLPorCiudad.getURLSucre();
                break;
            case "Tarija":
                url = URLPorCiudad.getURLTarija();
                break;
            case "La Paz":
                url = URLPorCiudad.getURLLaPaz();
                break;
            case "Oruro":
                url = URLPorCiudad.getURLOruro();
                break;
            case "Potosi":
                url = URLPorCiudad.getURLPotosi();
                break;
            case "El Alto":
                url = URLPorCiudad.getURLElAlto();
                break;
            case "Santa Cruz":
                url = URLPorCiudad.getURLSantaCruz();
                break;
            case "Beni":
                url = URLPorCiudad.getURLBeni();
                break;
            case "Pando":
                url = URLPorCiudad.getURLPando();
                break;
        }

    }

}
