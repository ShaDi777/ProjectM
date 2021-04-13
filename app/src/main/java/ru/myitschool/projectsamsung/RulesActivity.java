package ru.myitschool.projectsamsung;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.numbers.NumbersGameActivity;

public class RulesActivity extends AppCompatActivity {
    TextView rulesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        rulesText=findViewById(R.id.rulesText);
        rulesText.setText("Bubble Game: По нажатию исчезают круги, нужно успеть убрать их все.\n\n " +
                "Square Game: Нажать по порядку от 1 до 36\n\n " +
                "Number Game: На экране число(нажмите на него, чтобы начать), запомните его и сравните с предыдущим");
    }

    public void clickReturn(View view) {
        Intent intent = new Intent(RulesActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
