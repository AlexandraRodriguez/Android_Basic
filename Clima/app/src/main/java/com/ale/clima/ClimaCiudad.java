package com.ale.clima;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ale.clima.util.Conversor;
import com.ale.clima.util.URLPorCiudad;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import java.io.IOException;

public class ClimaCiudad extends AppCompatActivity {
    private TextView tempActual;
    private TextView tempMax;
    private TextView tempMin;
    private TextView humedad;
    private TextView presion;
    private TextView descripcion;
    private String ciudad;
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
            tempMax.setText("Temperatura maxima: " + result.getTempMaxima());
            tempMin.setText("Temperatura minima: "+result.getTempMinima());
            humedad.setText("Humedad: "+result.getHumedad());
            presion.setText("Presion: "+result.getPresion());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cambiarCiudad:
                Log.i("ActionBar", "Nuevo!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
