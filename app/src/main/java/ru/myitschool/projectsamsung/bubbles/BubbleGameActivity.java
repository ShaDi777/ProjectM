package ru.myitschool.projectsamsung.bubbles;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.bubbles.DrawView;

public class BubbleGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

}
