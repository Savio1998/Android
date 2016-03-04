package com.example.klinten.demoanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class Animation extends AppCompatActivity {
    ImageView bluecolor;
    ImageView redcolor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        bluecolor = (ImageView) findViewById(R.id.imageView);
        redcolor = (ImageView) findViewById(R.id.imageView2);
    }
    public void animate1(View view){
        bluecolor.animate().alpha(0f).setDuration(3000l);
    }

    public void animate2(View view){
        redcolor.animate().alpha(0f).setDuration(3000l);
    }
}
