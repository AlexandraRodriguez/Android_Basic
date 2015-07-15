package com.example.ale.groceryshoppingassistant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by LUIS on 15/07/2015.
 */
public class FoodActivity extends Activity {
    private ImageView foodImage;
    private TextView txtName;
    private TextView txtCal;
    private TextView txtFat;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.food);

        foodImage =(ImageView)findViewById(R.id.foodImage);
        txtName = (TextView)findViewById(R.id.name);
        txtCal = (TextView)findViewById(R.id.calories);
        txtFat = (TextView)findViewById(R.id.fat);
    }
}
