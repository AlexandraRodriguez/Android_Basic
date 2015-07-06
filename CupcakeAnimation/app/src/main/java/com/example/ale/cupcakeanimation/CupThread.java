package com.example.ale.cupcakeanimation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class CupThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private MainActivity.Panel panel;
    private boolean run;

    public CupThread(SurfaceHolder surfaceHolder, MainActivity.Panel panel){
        this.surfaceHolder = surfaceHolder;
        this.panel= panel;
        run = false;
    }

    public void setRunning(boolean run){
        this.run = run;
    }

    @Override
    public void run(){
        Canvas c;
        while (run){
            c=null;
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder){
                    panel.onDraw(c);
                }
            }finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
