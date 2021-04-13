package ru.myitschool.projectsamsung.squares;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import ru.myitschool.projectsamsung.TimerThread;

import java.util.ArrayList;
import java.util.Collections;

public class DrawSquareGameThread extends Thread {

    TimerThread timer = new TimerThread();

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true;

    public int width;
    public int height;
    private int size;
    private int targetID=0;

    private int pressedX=-1000;
    private int pressedY=-1000;

    public int amountOfSquares=36;

    private ArrayList<Square> squares= new ArrayList<Square>();
    private ArrayList<Integer> id_array=new ArrayList<>();


    public void setAmountOfSquares(int amountOfSquares) {
        this.amountOfSquares = amountOfSquares;
    }

    public DrawSquareGameThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;

    }

    public void requestStop() {
        running = false;
    }

    public void deletePoint(int x, int y) {
        pressedX = x;
        pressedY = y;
    }

    public void generateID(ArrayList<Integer> id_array, int amountOfSquares){
        for (int i = 0; i < amountOfSquares; i++) {
            id_array.add(i+1);
        }
        Collections.shuffle(id_array);
    }

    @Override
    public void run() {
        Paint paint = new Paint();
        generateID(id_array, amountOfSquares);

        size= (int)(0.9f*width/Math.sqrt(amountOfSquares));
        int otstup = (int)(0.1f*width/(Math.sqrt(amountOfSquares)+1));

        for (int i = 0; i < Math.sqrt(amountOfSquares); i++) {
            for (int j = 0; j < Math.sqrt(amountOfSquares); j++) {
                squares.add(new Square(otstup+(otstup + size) * j,
                        (int)((height-width)/2)+(otstup + size) * i,  size,
                        id_array.get((int) (i*Math.sqrt(amountOfSquares)+j))));
            }
        }

        targetID=1;
        timer.start();
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {

                    if(amountOfSquares==0){
                        timer.stopRunningTime();
                    }

                    paint.setColor(Color.YELLOW);
                    canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), paint);
                    paint.setColor(Color.DKGRAY);
                    paint.setTextSize(canvas.getHeight()/12);
                    canvas.drawText(timer.getTimeString(), canvas.getWidth()/2, canvas.getHeight()/12, paint);


                    for (int i = 0; i < amountOfSquares; i++) {
                        paint.setColor(Color.BLUE);
                        canvas.drawRect(squares.get(i).getX0(),
                                squares.get(i).getY0(),
                                squares.get(i).getX0()+squares.get(i).getSize(),
                                squares.get(i).getY0()+squares.get(i).getSize(),
                                paint);

                        paint.setColor(Color.WHITE);
                        paint.setTextSize(0.8f*squares.get(i).getSize());
                        canvas.drawText(String.valueOf(squares.get(i).getId()), squares.get(i).getX0(), squares.get(i).getY0()+(squares.get(i).getSize()*0.8f), paint);
                    }

                    for (int i = 0; i < amountOfSquares; i++) {
                        Square tempSquare = squares.get(i);
                        if(pressedX<=tempSquare.getX0()+tempSquare.getSize() &&
                                pressedX>=tempSquare.getX0() &&
                                pressedY<=tempSquare.getY0()+tempSquare.getSize() &&
                                pressedY>=tempSquare.getY0() &&
                                targetID == tempSquare.getId()){
                            squares.remove(tempSquare);
                            targetID++;
                            amountOfSquares--;
                            break;
                        }
                    }



                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }



}
