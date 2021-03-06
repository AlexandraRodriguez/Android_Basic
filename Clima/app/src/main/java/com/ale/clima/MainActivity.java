package com.ale.clima;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.ale.clima.util.Conversor;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnCbba;
    private Button btnSucre;
    private Button btnTarija;
    private Button btnLaPaz;
    private Button btnElAlto;
    private Button btnOruro;
    private Button btnPotosi;
    private Button btnSantaCruz;
    private Button btnBeni;
    private Button btnPando;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_layout);

        btnCbba = (Button)findViewById(R.id.btnCbba);
        btnSucre = (Button)findViewById(R.id.btnSucre);
        btnTarija = (Button)findViewById(R.id.btnTarija);
        btnLaPaz = (Button)findViewById(R.id.btnLaPaz);
        btnElAlto = (Button)findViewById(R.id.btnElAlto);
        btnOruro = (Button)findViewById(R.id.btnOruro);
        btnPotosi = (Button)findViewById(R.id.btnPotosi);
        btnSantaCruz = (Button)findViewById(R.id.btnSantaCruz);
        btnBeni = (Button)findViewById(R.id.btnBeni);
        btnPando = (Button)findViewById(R.id.btnPando);
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() )
        {
            case R.id.btnCbba :
                Conversor.setCiudad("Cochabamba");
                break;
            case R.id.btnSucre :
                Conversor.setCiudad("Sucre");
                break;
            case R.id.btnTarija :
                Conversor.setCiudad("Tarija");
                break;
            case R.id.btnLaPaz :
                Conversor.setCiudad("La Paz");
                break;
            case R.id.btnElAlto :
                Conversor.setCiudad("El Alto");
                break;
            case R.id.btnOruro :
                Conversor.setCiudad("Oruro");
                break;
            case R.id.btnPotosi :
                Conversor.setCiudad("Potosi");
                break;
            case R.id.btnSantaCruz :
                Conversor.setCiudad("Santa Cruz");
                break;
            case R.id.btnBeni :
                Conversor.setCiudad("Beni");
                break;
            case R.id.btnPando :
                Conversor.setCiudad("Pando");
                break;
        }
        startActivity(new Intent(this, ClimaCiudad.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}
