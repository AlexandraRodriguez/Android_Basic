package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.campeonato.alexandra.copa.util.EquiposSQLiteHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPreliminares;
    private Button btnCuartos;
    private Button btnSemifinales;
    private Button btnFinales;
    private Button btnRes;
    private EquiposSQLiteHelper helper;
    private TextView mensaje;


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);

        btnPreliminares = (Button)findViewById(R.id.preliminares);
        btnPreliminares.setOnClickListener(this);
        btnCuartos = (Button)findViewById(R.id.cuartos);
        btnCuartos.setOnClickListener(this);
        btnSemifinales = (Button)findViewById(R.id.semifinales);
        btnSemifinales.setOnClickListener(this);
        btnFinales = (Button)findViewById(R.id.finales);
        btnFinales.setOnClickListener(this);
        btnRes = (Button)findViewById(R.id.resultados);
        btnRes.setOnClickListener(this);

        helper = new EquiposSQLiteHelper(MainActivity.this);
        insertarEquipos();


    }

    private void insertarEquipos(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("equipo", "Bolivia");
        values.put("puntaje", "0");
        values.put("clasificado", "1");
        db.insert("Registro", null, values);
    }


    @Override
    public void onClick(View v) {
        switch ( v.getId() )
        {
            case R.id.preliminares :
                startActivity( new Intent(this, Preliminares.class));
                break;
            case R.id.cuartos :
                startActivity( new Intent(this, Cuartos.class));
                break;
            case R.id.semifinales :
                startActivity( new Intent(this, Semifinales.class));
                break;
            case R.id.finales :
                startActivity( new Intent(this, Final.class));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
