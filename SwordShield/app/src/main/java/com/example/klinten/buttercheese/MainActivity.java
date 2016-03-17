package com.example.klinten.buttercheese;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int             speleractief;
    int[]           spelverloop = {0,0,0,0,0,0,0,0,0};
    int[][]         winners = {
                    {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},
                    {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
    int             spelergewonnen;
    boolean         spelAfgelopen;
    TextView        eindText;
    Button          newGame;
    LinearLayout    eindeSpelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        speleractief = 1;
        spelergewonnen = 0;
        eindText = (TextView) findViewById(R.id.textView);
        newGame = (Button) findViewById(R.id.newGame);
        eindeSpelLayout = (LinearLayout) findViewById(R.id.eindeSpelLayout);

    }

    public void nieuwSpel(View view){
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < spelverloop.length; i++){
            spelverloop[i] = 0;
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageResource(0);
        }
        speleractief = 1;
        spelAfgelopen = false;
        spelergewonnen = 0;
        eindeSpelLayout.setVisibility(View.GONE);
    }

    public void setImg(View view){
        ImageView image = (ImageView) view;
        int index = Integer.parseInt(image.getTag().toString());
        if(spelverloop[index] == 0){
            if(speleractief == 1) {
                spelverloop[index] = speleractief;
                image.setTranslationY(-1000f);
                image.setImageResource(R.drawable.sword);
                image.animate().translationY(0f).setDuration(400);
                checkEindeSpel();
                speleractief = 2;
            } else {
                spelverloop[index] = speleractief;
                image.setTranslationY(-1000f);
                image.setImageResource(R.drawable.shield);
                image.animate().translationY(0f).setDuration(400);
                checkEindeSpel();
                speleractief = 1;
            }
            if(spelAfgelopen){
                eindSpel();
            }
        }

    }
    private void checkEindeSpel(){
        for (int[] winner: winners){
            boolean isWinner = true;
            for(int i: winner){
                if(spelverloop[i] != speleractief){
                    isWinner = false;
                    break;
                }
            }
            if (isWinner){
                spelergewonnen = speleractief;
                spelAfgelopen = true;
            }
        }
        if (spelergewonnen == 0){
             spelAfgelopen = true;
            for(int i: spelverloop){
                if (i == 0){
                    spelAfgelopen = false;
                    break;
                }
            }
        }
    }

    private void eindSpel(){
        switch (spelergewonnen){
            case 0:
                eindText.setText("Draw... Try again");
                eindeSpelLayout.setBackgroundColor(0xFF289BBB);
                break;
            case 1:
                eindText.setText("Player 1 wins!");
                eindeSpelLayout.setBackgroundColor(0xFF8D2D2D);
                break;
            case 2:
                eindText.setText("Player 2 wins!");
                eindeSpelLayout.setBackgroundColor(0xFF80B771);
                break;
        }
        eindeSpelLayout.setVisibility(View.VISIBLE);

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
