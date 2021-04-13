package ru.myitschool.projectsamsung.bubbles;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private ru.myitschool.projectsamsung.bubbles.DrawThread DrawThread;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DrawThread.deletePoint((int)event.getX(),(int)event.getY());
        return false;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawThread = new DrawThread(getContext(),getHolder());
        DrawThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        DrawThread.width = width;
        DrawThread.height = height;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        DrawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                DrawThread.join();

                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }

    }
}
