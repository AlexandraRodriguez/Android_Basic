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

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
    }

    class Panel extends SurfaceView implements SurfaceHolder.Callback{
        private CupThread thread;
        private int x;
        private int y;

        public Panel(Context context){
            super(context);
            getHolder().addCallback(this);
            thread = new CupThread(getHolder(), this);
            setFocusable(true);
            x=20; y=20;
        }

        @Override
        public void onDraw(Canvas canvas){
            Bitmap cupcake = BitmapFactory.decodeResource(getResources(), R.drawable.kawaiicupcake1);
            canvas.drawColor(Color.parseColor("#f092b0"));
            canvas.drawBitmap(cupcake, x-(cupcake.getWidth()/2), y-(cupcake.getHeight()/2), null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            x = (int) event.getX();
            y = (int) event.getY();
            return true;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.setRunning(true);
            thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // simply copied from sample application LunarLander:
            // we have to tell thread to shut down & wait for it to finish, or else
            // it might touch the Surface after we return and explode
            boolean retry = true;
            thread.setRunning(false);
            while (retry) {
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // we will try it again and again...
                }
            }
        }
    }
}
