package com.example.ale.groceryshoppingassistant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;


public class FoodActivity extends Activity {
    private ImageView foodImage;
    private TextView txtName;
    private TextView txtCal;
    private TextView txtFat;
    private TextView txtServing;
    private int pos;
    private URLDataBaseManager manager;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.food);

        manager = new URLDataBaseManager(this);

        Intent intent = getIntent();
        pos = intent.getIntExtra(MainActivity.EXTRA_POS, 0);
        String foodName = Grid.getName(pos);
        String url = manager.getURL(foodName);

        foodImage =(ImageView)findViewById(R.id.foodImage);
        txtName = (TextView)findViewById(R.id.name);
        txtCal = (TextView)findViewById(R.id.calories);
        txtFat = (TextView)findViewById(R.id.fat);
        txtServing = (TextView)findViewById(R.id.serving);

        foodImage.setImageResource(intent.getIntExtra(MainActivity.EXTRA_IMAGE_ID,0));

        final String URL = url;
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute(URL);
            }
        });
    }

    private class HttpGetTask extends AsyncTask<String, Void, Food>{
        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected Food doInBackground(String... params) {
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
        protected void onPostExecute(Food result) {

            txtName.setText(result.getName());
            txtCal.append(result.getCalories());
            txtFat.append(result.getFat());
            txtServing.setText(result.getServingSize()+" "+result.getServing());
        }
    }
}
