package ru.myitschool.projectsamsung.squares;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DrawSquareGameView extends SurfaceView implements SurfaceHolder.Callback {

    public DrawSquareGameThread DrawThread;
    public int amount=0;

    public DrawSquareGameView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public void setAmountSqr(int amountSqr){
        amount = amountSqr;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DrawThread.deletePoint((int)event.getX(),(int)event.getY());
        return false;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawThread = new DrawSquareGameThread(getContext(),getHolder());
        DrawThread.setAmountOfSquares(amount);
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
