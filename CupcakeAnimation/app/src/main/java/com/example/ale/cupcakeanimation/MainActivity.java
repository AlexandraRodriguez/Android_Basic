package com.example.ale.cupcakeanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
    }

    class Panel extends SurfaceView implements SurfaceHolder.Callback{
        private CupThread thread;
        private ArrayList<GraphicObject> cupcakeGraphics = new ArrayList<GraphicObject>();

        public Panel(Context context){
            super(context);
            getHolder().addCallback(this);
            thread = new CupThread(getHolder(), this);
            setFocusable(true);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            synchronized (thread.getSurfaceHolder()){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    GraphicObject cupcakeGraphic = new GraphicObject(BitmapFactory.decodeResource(getResources(), R.drawable.kawaiicupcake1));
                    cupcakeGraphic.getCoordinates().setX((int)event.getX() - cupcakeGraphic.getGraphic().getWidth()/2);
                    cupcakeGraphic.getCoordinates().setY((int) event.getY() - cupcakeGraphic.getGraphic().getHeight() / 2);
                    cupcakeGraphics.add(cupcakeGraphic);
                }
                return true;
            }
        }

        public void updatePhysics(){
            GraphicObject.Coordinates coord;
            GraphicObject.Speed speed;
            for (GraphicObject cupcake : cupcakeGraphics) {
                coord = cupcake.getCoordinates();
                speed = cupcake.getSpeed();

                //direccion
                if (speed.getXDirection() == GraphicObject.Speed.xDirectionRight) {
                    coord.setX(coord.getX() + speed.getX());
                } else {
                    coord.setX(coord.getX() - speed.getX());
                }
                if (speed.getYDirection() == GraphicObject.Speed.yDirectionDown) {
                    coord.setY(coord.getY() + speed.getY());
                } else {
                    coord.setY(coord.getY() - speed.getY());
                }

                //bordes de x
                if (coord.getX() < 0) {
                    speed.changeXDirection();
                    coord.setX(-coord.getX());
                } else if (coord.getX() + cupcake.getGraphic().getWidth() > getWidth()) {
                    speed.changeXDirection();
                    coord.setX(coord.getX() + getWidth() - (coord.getX() + cupcake.getGraphic().getWidth()));
                }

                //bordes de y
                if (coord.getY() < 0) {
                    speed.changeYDirection();
                    coord.setY(-coord.getY());
                } else if (coord.getY() + cupcake.getGraphic().getHeight() > getHeight()) {
                    speed.changeYDirection();
                    coord.setY(coord.getY() + getHeight() - (coord.getY() + cupcake.getGraphic().getHeight()));
                }

            }
        }

        @Override
        public void onDraw(Canvas canvas){
            Bitmap bitmap;
            canvas.drawColor(Color.parseColor("#f092b0"));
            GraphicObject.Coordinates coords;
            for(GraphicObject graphic: cupcakeGraphics){
                bitmap = graphic.getGraphic();
                coords = graphic.getCoordinates();
                canvas.drawBitmap(bitmap, coords.getX(), coords.getY(), null);
            }
        }


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.setRunning(true);
            thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            thread.setRunning(false);
            while (retry) {
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
