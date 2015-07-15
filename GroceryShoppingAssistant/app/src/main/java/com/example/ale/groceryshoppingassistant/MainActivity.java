package com.example.ale.groceryshoppingassistant;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TabHost tabHost;
    private GridView grid;


    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);

        tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Tab1").setIndicator("Nutritional Values").setContent(R.id.tab1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab2").setIndicator("Recommendations").setContent(R.id.tab2);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab3").setIndicator("Grocery shopping List").setContent(R.id.tab3);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        grid = (GridView)findViewById(R.id.grid);
        grid.setAdapter(new ImageAdapter(this));

        grid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "You clicked data position: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
        //setGrid();
    }

    public void setGrid(){

    }
}
