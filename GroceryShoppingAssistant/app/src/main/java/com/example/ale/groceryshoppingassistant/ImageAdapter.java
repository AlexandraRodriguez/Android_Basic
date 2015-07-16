package com.example.ale.groceryshoppingassistant;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {

    private Context context;

    public ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return Grid.getData().length;
    }

    @Override
    public Object getItem(int position) {
        return Grid.getData()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView foodImage;
        if(convertView == null){
            foodImage = new ImageView(context);
            foodImage.setLayoutParams(new GridView.LayoutParams(185, 185));
            foodImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            foodImage.setPadding(5, 5, 5, 5);
        }else{
            foodImage = (ImageView)convertView;
        }
        foodImage.setImageResource(Grid.getData()[position]);
        return foodImage;
    }
}
