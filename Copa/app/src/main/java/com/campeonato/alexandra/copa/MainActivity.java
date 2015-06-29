package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alexandra on 29/06/15.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnPreliminares;
    private Button btnCuartos;
    private Button btnSemifinales;
    private Button btnFinales;
    private Button btnRes;

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
                startActivity( new Intent(this, Finales.class));
                break;

        }
    }
}
