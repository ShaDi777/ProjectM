package ru.myitschool.projectsamsung;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.myitschool.projectsamsung.bubbles.BubbleGameActivity;
import ru.myitschool.projectsamsung.bubbles.DrawThread;
import ru.myitschool.projectsamsung.numbers.NumbersGameActivity;
import ru.myitschool.projectsamsung.squares.DrawSquareGameThread;
import ru.myitschool.projectsamsung.squares.SquareGameActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int amountSqr=5*5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBubbleGame(View view) {
        Intent intent = new Intent(MainActivity.this, BubbleGameActivity.class);
        startActivity(intent);
    }

    public void openSquareGame(View view) {
        Intent intent = new Intent(MainActivity.this, SquareGameActivity.class);
        intent.putExtra("amount", amountSqr);
        startActivity(intent);
    }

    public void openNumberGame(View view) {
        Intent intent = new Intent(MainActivity.this, NumbersGameActivity.class);
        startActivity(intent);
    }

    public void openRules(View view) {
        Intent intent = new Intent(MainActivity.this, RulesActivity.class);
        startActivity(intent);
    }



    //For SquareGame
    public void set5(View view) {
        amountSqr=5*5;
    }
    public void set6(View view) {
        amountSqr=6*6;
    }
    public void set7(View view) {
        amountSqr=7*7;
    }
    public void set8(View view) {
        amountSqr=8*8;
    }
    public void set9(View view) {
        amountSqr=9*9;
    }
}
