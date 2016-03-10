package com.example.klinten.animationdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    ImageView friskImg, charaImg;
    boolean isfrisk;
    long duuranimatie;
    int duuranimate;
    SeekBar seekBar;
    RadioButton fade, translate, rotate;
    boolean isfade, istranslate, isrotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        friskImg = (ImageView) findViewById(R.id.friskImg);
        charaImg = (ImageView) findViewById(R.id.charaImg);
        isfrisk = true;
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(duuranimate);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duuranimatie = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fade = (RadioButton) findViewById(R.id.faderadioButton);
        fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfade = true;
                istranslate = false;
                isrotate = false;
                translate.setChecked(false);
                rotate.setChecked(false);
                if(isfrisk){
                    charaImg.setAlpha(0f);
                    charaImg.setTranslationX(0f);
                    charaImg.setScaleX(1f);
                    charaImg.setScaleY(1f);
                } else {
                    friskImg.setAlpha(0f);
                    friskImg.setTranslationX(0f);
                    friskImg.setScaleX(1f);
                    friskImg.setScaleY(1f);
                }
            }
        });
        translate = (RadioButton) findViewById(R.id.translateradioButton);
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfade = false;
                istranslate = true;
                isrotate = false;
//                fade.setChecked(false);
//                rotate.setChecked(false);
                if(isfrisk){
                    charaImg.setAlpha(1f);
                    charaImg.setTranslationX(-1000f);
                    charaImg.setScaleX(1f);
                    charaImg.setScaleY(1f);
                } else {
                    friskImg.setAlpha(1f);
                    friskImg.setTranslationX(1000f);
                    friskImg.setScaleX(1f);
                    friskImg.setScaleY(1f);
                }
                fade.setChecked(false);
                rotate.setChecked(false);
            }
        });
        rotate = (RadioButton) findViewById(R.id.rotateradioButton);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isfade = false;
                istranslate = false;
                isrotate = true;

                if(isfrisk){
                    charaImg.setAlpha(1f);
                    charaImg.setTranslationX(0f);
                    charaImg.setScaleX(0f);
                    charaImg.setScaleY(0f);
                } else {
                    friskImg.setAlpha(1f);
                    friskImg.setTranslationX(0f);
                    friskImg.setScaleX(0f);
                    friskImg.setScaleY(0f);
                }
                fade.setChecked(false);
                translate.setChecked(false);
            }
        });
        isfade = true;
        fade.setChecked(true);
    }

    public void animation(View view) {
        if(isfade){
            fade();
        }
        else if(istranslate){
            translate();
        }
        else if(isrotate){
            rotate();
        }
        isfrisk = !isfrisk;
    }

    public void fade(){
        if (isfrisk) {
            friskImg.animate().alpha(0f).setDuration(duuranimatie);
            charaImg.animate().alpha(1f).setDuration(duuranimatie);
        } else {
            charaImg.animate().alpha(0f).setDuration(duuranimatie);
            friskImg.animate().alpha(1f).setDuration(duuranimatie);
        }
//        isfrisk = !isfrisk;
    }

    public void translate() {
        if (isfrisk) {
            friskImg.animate()
                    .translationX(1000f)
                    .setDuration(duuranimatie);
            charaImg.animate()
                    .translationX(0f)
                    .setDuration(duuranimatie);;
        } else {
            friskImg.animate()
                    .translationX(0f)
                    .setDuration(duuranimatie);
            charaImg.animate()
                    .translationX(-1000f)
                    .setDuration(duuranimatie);
        }
//        isfrisk = !isfrisk;
    }

    public void rotate() {
        if (isfrisk) {
            friskImg.animate()
                    .rotation(720f)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(duuranimatie);
            charaImg.animate()
                    .rotation(-720f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(duuranimatie);
        } else {
            friskImg.animate()
                    .rotation(-720f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(duuranimatie);
            charaImg.animate()
                    .rotation(720f)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(duuranimatie);;
        }
//        isfrisk = !isfrisk;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
