package ru.myitschool.projectsamsung.numbers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.MainActivity;
import ru.myitschool.projectsamsung.R;
import ru.myitschool.projectsamsung.TimerThread;
import android.os.Vibrator;
import ru.myitschool.projectsamsung.squares.SquareGameActivity;

import java.util.Collection;


public class NumbersGameActivity extends AppCompatActivity {

    TextView timerText, lifeText, numberText;
    Button bigger, smaller;
    TimerThread timer;

    int prev;
    int next;
    int life;
    int time;
    int count=0;
    int fromNum=0;
    int toNum=100;

    Vibrator v;

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

        timer = new TimerThread();

        prev = (int) (int) (fromNum+Math.random() * (toNum-fromNum));
        next = (int) (int) (fromNum+Math.random() * (toNum-fromNum));
        life = 3;
        time = 50;
        numberText.setTextColor(Color.BLACK);
        numberText.setText(String.valueOf(prev));

        lifeText.setTextColor(Color.MAGENTA);
        lifeText.setText("HP = " + life);

        numberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberText.setEnabled(false);
                timer.start();
                numberText.setText(String.valueOf(next));
                bigger.setEnabled(true);
                smaller.setEnabled(true);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            timerText.setText(timer.getTimeString());

                    }
                });
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
    }

    @Override
    protected void onDestroy() {
        timer.stopRunningTime();
        super.onDestroy();
    }
}
