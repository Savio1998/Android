package com.example.klinten.braintrainer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class Secondactivitay extends AppCompatActivity {
    private TextView timerText, scoreText;
    private Button[] buttons;
    private int goedeAntwoord;
    private int goed, beurten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivitay);
        timerText = (TextView) findViewById(R.id.Timer);
        scoreText = (TextView) findViewById(R.id.Score);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        buttons = new Button[4];
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = (Button) gridLayout.getChildAt(i);
        }
        goed = 0;
        beurten = 0;
        startTimer();
        maakSom();
    }

    private void startTimer(){
        new CountDownTimer(30000 + 100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                int seconden = (int) millisUntilFinished / 1000;
                timerText.setText(String.valueOf(seconden));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.putExtra("score", goed);
                startActivity(intent);
            }
        }.start();
    }
    private void maakSom(){
        Random random = new Random();
        int getal1 = random.nextInt(100) + 1;
        int getal2 = random.nextInt(100) +1;
        TextView som = (TextView) findViewById(R.id.Som);
        som.setText(String.valueOf(getal1) + " + " + getal2);
        goedeAntwoord = getal1 + getal2;

        int antwoordKnop = random.nextInt(4);
        buttons[antwoordKnop].setText(String.valueOf(goedeAntwoord));
        for (int i = 0; i < buttons.length; i++){
            if(i != antwoordKnop) {
                int fout = random.nextInt(100) +1;
                while(fout == goedeAntwoord) {
                     fout = random.nextInt(100) +1;
                }buttons[i].setText(String.valueOf(fout));
            }
        }
    }
    public void checkUitkomst(View view){
        beurten++;
        Button button = (Button) view;
        int    antwoord = Integer.parseInt(button.getText().toString());
        if(antwoord == goedeAntwoord){
            goed++;

        }
        scoreText.setText(String.valueOf(goed) + "/" + beurten);
        maakSom();
    }
}
