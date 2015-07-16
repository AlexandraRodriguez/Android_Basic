package com.example.ale.groceryshoppingassistant;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;


public class TaskActivity extends ListActivity {
    private ListAdapter listAdapter;
    private TaskDBHelper helper;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.tasks);
    }
}
