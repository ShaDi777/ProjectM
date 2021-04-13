package ru.myitschool.projectsamsung;

import android.widget.TextView;
import ru.myitschool.projectsamsung.numbers.NumbersGameActivity;

public class TimerThread extends Thread {
    private int time=0;
    private volatile boolean running = true;
    @Override
    public void run() {
        time=0;
        while(running) {
            time++;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTimeString(){
        String time_str = "0:00";
        if(time<10){
            time_str="0:0"+time;
        }else if(time<60){
            time_str="0:"+time;
        }else{
            int min = time/60;
            if((time-min*60)<10) {
                time_str = min + ":0" + (time - min * 60);
            }else{
                time_str=min+":"+(time-min*60);
            }
        }
        return time_str;
    }

    public int getSec(){
        return time;
    }

    public void stopRunningTime(){
        running=false;
    }


}
