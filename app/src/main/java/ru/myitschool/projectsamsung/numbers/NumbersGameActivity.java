package ru.myitschool.projectsamsung.numbers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.MainActivity;
import ru.myitschool.projectsamsung.R;
import ru.myitschool.projectsamsung.TimerThread;
import ru.myitschool.projectsamsung.squares.SquareGameActivity;

import java.util.Collection;


public class NumbersGameActivity extends AppCompatActivity {

    private TextView timerText, lifeText, numberText;
    private Button bigger, smaller;

    private boolean running=true;

    private int prev;
    private int next;
    private int life;
    private int time;
    private int count=0;
    private int fromNum=0;
    private int toNum=100;

    private Vibrator v;
    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbersgame);
        timerText = findViewById(R.id.timerText);
        lifeText = findViewById(R.id.lifeText);
        numberText = findViewById(R.id.numberText);
        bigger = findViewById(R.id.bigger);
        smaller = findViewById(R.id.smaller);
        bigger.setEnabled(false);
        smaller.setEnabled(false);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        h = new Handler() {
            public void handleMessage(Message msg) {
                timerText.setText(getTimeString(msg.what));
            };
        };

        prev = (int) (int) (fromNum+Math.random() * (toNum-fromNum));
        next = (int) (int) (fromNum+Math.random() * (toNum-fromNum));
        life = 3;
        time = 60;
        numberText.setTextColor(Color.BLACK);
        numberText.setText(String.valueOf(prev));

        lifeText.setTextColor(Color.MAGENTA);
        lifeText.setText("HP = " + life);
        timerText.setText(getTimeString(time));

        numberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberText.setEnabled(false);
                numberText.setText(String.valueOf(next));
                bigger.setEnabled(true);
                smaller.setEnabled(true);
                updater.start();
            }
        });
    }

    public void smallerClick(View view) {
        if (next < prev) {
            prev = next;
            while(true){
                next = (int) (fromNum+Math.random() * (toNum-fromNum));
                if(next!=prev){
                    break;
                }
            }
            numberText.setTextColor(Color.BLACK);
            numberText.setText(String.valueOf(next));
            count++;
            if(count%10==0 && fromNum<30 && toNum>70) {
                fromNum+=5;
                toNum-=5;
            }
        }else{
            life--;
            lifeText.setText("HP = "+ life);
            numberText.setTextColor(Color.RED);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(500);
            }
        }
        if(life==0){
            result();
            running=false;
        }
    }

    public void biggerClick(View view) {
        if (next > prev) {
            prev = next;
            while(true){
                next = (int) (fromNum+Math.random() * (toNum-fromNum));
                if(next!=prev){
                    break;
                }
            }
            numberText.setTextColor(Color.BLACK);
            numberText.setText(String.valueOf(next));
            count++;
            if(count%10==0 && fromNum<30 && toNum>70) {
                fromNum+=5;
                toNum-=5;
            }
        }else{
            life--;
            lifeText.setText("HP = "+ life);
            numberText.setTextColor(Color.RED);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                v.vibrate(300);
            }
        }
        if(life==0){
            result();
            running=false;
        }
    }


    Thread updater = new Thread() {
        @Override
        public void run() {
            try {
                while (//!updater.isInterrupted()
                 running) {
                    h.sendEmptyMessage(time);
                    if(time==0){
                        running=false;
                    }
                    Thread.sleep(1000);
                    time--;
                }
            } catch (InterruptedException e) {
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result();
                }
            });

        }
    };

    public String getTimeString(int time){
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

    public void result(){
        numberText.setTextColor(Color.GREEN);
        numberText.setText("YOUR SCORE: "+count);
        bigger.setText("Restart");
        smaller.setText("Back to menu");
        bigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumbersGameActivity.this.recreate();
            }
        });
        smaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NumbersGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        running=false;
        super.onDestroy();
    }
}
