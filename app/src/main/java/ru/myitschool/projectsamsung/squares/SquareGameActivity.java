package ru.myitschool.projectsamsung.squares;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class SquareGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        int amount = arguments.getInt("amount");

        DrawSquareGameView drawSquareGameView = new DrawSquareGameView(this);

        drawSquareGameView.setAmountSqr(amount);

        setContentView(drawSquareGameView);


    }
}
