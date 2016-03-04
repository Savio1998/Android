package com.example.klinten.egg;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView countdown;
    Button start, button, black, white;
    SeekBar seekbar;
    int timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        timer = 30;

        countdown = (TextView) findViewById(R.id.countdown);
        start = (Button) findViewById(R.id.start);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setMax(600);
        seekbar.setProgress(timer);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timer = progress;
                setTimerText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void setTimerText() {
        int minuten = timer / 60;
        int seconden = timer - minuten * 60;
        String tijd = minuten + " : " + seconden;
        if (seconden < 10) {
            tijd = minuten + " : 0" + seconden;
        }
        countdown.setText(tijd);
    }

    public void startstoptimer(View view) {

        CountDownTimer downtimer = new CountDownTimer(timer * 1000 + 100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer = (int) millisUntilFinished / 1000;
                setTimerText();
            }

            @Override
            public void onFinish() {

                MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(),R.raw.slight_distant_explosion);
                mediaplayer.start();
                countdown.setText("0: 00");

            }
        };
        downtimer.start();
    }
    public void random(View view){
        int min = 1;
        int max = 3;

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        if(i1 == 1){
            MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(),R.raw.alarm_clock_beep_tone);
            mediaplayer.start();

        } else if(i1 == 2 || i1 == 3){
            MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(),R.raw.slight_distant_explosion);
            mediaplayer.start();

        }

    }

}
