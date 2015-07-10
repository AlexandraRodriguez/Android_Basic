package com.campeonato.alexandra.copa;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.campeonato.alexandra.copa.db.EquiposDataBaseManager;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button btnPreliminares;
    public static Activity main;
    private EquiposDataBaseManager manager;


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);
        main = this;
        btnPreliminares = (Button)findViewById(R.id.btnPreliminares);

        manager = new EquiposDataBaseManager(this);
        crearRegistro();
    }

    public void startPreliminares(View v){
        startActivity( new Intent(this, Preliminares.class));
    }


    public void crearRegistro(){
        manager.insertar("Bolivia", 0, 1);
        manager.insertar("Brasil", 0, 1);
        manager.insertar("Argentina", 0, 1);
        manager.insertar("Paraguay", 0, 1);
        manager.insertar("Uruguay", 0, 1);
        manager.insertar("Chile", 0, 1);
        manager.insertar("Colombia", 0, 1);
        manager.insertar("Venezuela", 0, 1);
        manager.insertar("Ecuador", 0, 1);
        manager.insertar("Mexico", 0, 1);
        manager.insertar("Peru", 0, 1);
        manager.insertar("Jamaica", 0, 1);
    }
}
