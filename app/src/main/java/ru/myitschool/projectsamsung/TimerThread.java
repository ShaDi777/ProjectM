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
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
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

    public void setInTextViewDown(int n, TextView t){
        String time_str = "0:00";
        int timeN = n - time;
        if(timeN<10){
            time_str="0:0"+timeN;
        }else if(timeN<60){
            time_str="0:"+timeN;
        }else{
            int min = timeN/60;
            if((timeN-min*60)<10) {
                time_str = min + ":0" + (timeN - min * 60);
            }else{
                time_str=min+":"+(timeN-min*60);
            }
        }
        t.setText(time_str);
    }





    public int getSec(){
        return time;
    }


    public void stopRunningTime(){
        running=false;
    }


}
