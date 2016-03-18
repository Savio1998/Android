package com.example.klinten.braintrainer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView    highscoretext, newhighscoretext;
    int         highscore;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        highscoretext = (TextView) findViewById(R.id.highScore);
        newhighscoretext = (TextView) findViewById(R.id.newhighScore);
        preferences = this.getPreferences(Context.MODE_PRIVATE);
        highscore = preferences.getInt("highscore", 0);
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        newhighscoretext.setText("");
//        preferences.edit().clear().commit();

        if(score > highscore){
            highscore = score;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", highscore);
            editor.commit();
            newhighscoretext.setText("New Highscore!" );
        }
            highscoretext.setText("High score: " + highscore);

    }

    public void start(View view){

        Intent intent = new Intent(this, Secondactivitay.class);
        startActivity(intent);
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
        if(id == R.id.action_refresh) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", 0);
            editor.commit();
            highscoretext.setText("High score: " + 0);
        }

        return super.onOptionsItemSelected(item);
    }
}
