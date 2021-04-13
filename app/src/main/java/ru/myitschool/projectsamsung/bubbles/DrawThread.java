package ru.myitschool.projectsamsung.bubbles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import ru.myitschool.projectsamsung.TimerThread;

import java.util.ArrayList;
import java.util.Timer;

public class DrawThread extends Thread {

    TimerThread timer = new TimerThread();

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true;

    public int width;
    public int height;

    private int timeAdd=0;
    private int delta=0;


    private int pressedX=-1000;
    private int pressedY=-1000;
    private int radius=50;

    private int amountOfCircles=5;
    private ArrayList<Circle> circles= new ArrayList<Circle>();


    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }



    public void deletePoint(int x, int y) {
        pressedX = x;
        pressedY = y;
    }

    @Override
    public void run() {
        Paint paint = new Paint();
        for (int i = 0; i < amountOfCircles; i++) {
            circles.add(new Circle((int)(radius+Math.random()*(width-radius)), (int)(((height/6)+radius)+Math.random()*(height-3*radius)), radius));
        }
        timer.start();
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                try {

                    if(amountOfCircles==0){
                        timer.stopRunningTime();
                    }


                    paint.setColor(Color.GREEN);
                    canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), paint);

                    /*paint.setColor(Color.RED);
                    paint.setTextSize(30);
                    canvas.drawText(String.valueOf(pressedX)+"\n"+String.valueOf(pressedY),canvas.getWidth()/2, canvas.getHeight()/2, paint);
                    for (int i = 0; i < circles.size(); i++) {
                        canvas.drawText(String.valueOf(circles.get(i).getX())+"\n"+String.valueOf(circles.get(i).getY()), circles.get(i).getX(), circles.get(i).getRadius(), paint);
                    }*/

                    paint.setColor(Color.DKGRAY);
                    paint.setTextSize(canvas.getHeight()/12);
                    canvas.drawText(timer.getTimeString(), canvas.getWidth()/2, canvas.getHeight()/12, paint);



                    paint.setColor(Color.BLUE);
                    for (int i = 0; i < circles.size(); i++) {
                        canvas.drawCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getRadius(), paint);
                    }

                    for (int i = 0; i < circles.size(); i++) {
                        Circle tempCircle = circles.get(i);
                        if(pressedX<=tempCircle.getX()+tempCircle.getRadius() &&
                                pressedX>=tempCircle.getX()-tempCircle.getRadius() &&
                                pressedY<=tempCircle.getY()+tempCircle.getRadius() &&
                                pressedY>=tempCircle.getY()-tempCircle.getRadius()){
                            circles.remove(tempCircle);
                            amountOfCircles--;
                            pressedX=-1000;
                            pressedY=-1000;
                            break;
                        }
                    }

                    if(timer.getSec()%2==1 && timer.getSec()!=timeAdd){
                        for (int i = 0; i < 5; i++) {
                            addCircle(height, width);
                        }
                        timeAdd=timer.getSec();
                    }



                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void addCircle(int height, int width){
        circles.add(new Circle((int)(radius+Math.random()*(width-radius)), (int)(radius+Math.random()*(height-radius)), radius));
        amountOfCircles++;
    }



}

