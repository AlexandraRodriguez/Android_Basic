package com.ale.clima;

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

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;


/**
 * Created by Ale on 04/07/2015.
 */
public class MainActivity extends AppCompatActivity {
    private TextView tempActual;
    private TextView tempMax;
    private TextView tempMin;
    private TextView humedad;
    private TextView presion;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_clima);

        tempActual = (TextView)findViewById(R.id.tempActual);
        tempMax = (TextView)findViewById(R.id.tempMax);
        tempMin = (TextView)findViewById(R.id.tempMin);
        humedad = (TextView)findViewById(R.id.humedad);
        presion = (TextView)findViewById(R.id.presion);
        descripcion = (TextView)findViewById(R.id.descripcion);

        Button b = (Button)findViewById(R.id.actualizar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute();
            }
        });
    }

    private class HttpGetTask extends AsyncTask<Void, Void, Clima>{
        private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Cochabamba&lang=es";
        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected Clima doInBackground(Void... params) {
            HttpGet request = new HttpGet(URL);
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
}
