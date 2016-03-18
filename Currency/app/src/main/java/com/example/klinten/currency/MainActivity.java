package com.example.klinten.currency;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String BASE_URL = "http://api.fixer.io/latest?base=";
    Spinner spinner;
    ListView list;
    TextView datum;
    final String[] KEYS = {"EUR", "USD", "GBP", "JPY", "AUD"};
    ArrayList<String> currencies;
    int selected;
    ArrayList<Double> rates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spinner);
        list = (ListView) findViewById(R.id.listView);
        datum = (TextView) findViewById(R.id.datum);
        currencies = new ArrayList<>();
        rates = new ArrayList<>();
        String packageName = getPackageName();
        for(String key: KEYS){
            int resId = getResources().getIdentifier(key, "string", packageName);
            String s = getString(resId);

            currencies.add(s);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, currencies);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                selected = 0;
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selected = position;
                        rates.clear();
                        maakList();
                        String url = BASE_URL + KEYS[selected];
                        new DownloadTaak().execute(url);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        maakList();
        String url = BASE_URL + KEYS[selected];
        new DownloadTaak().execute(url);

    }
    private  void maakList(){
        ArrayList<String> listitems = new ArrayList<>();
        for(int i = 0; i < currencies.size(); i++){
            if(i !=selected){
                String item = currencies.get(i) + ": ";
                if(rates.isEmpty()){
                    item += getString(R.string.msg);
                }else{
                    item += rates.get(i);
                }listitems.add(item);
            }
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listitems);
        list.setAdapter(listAdapter);
    }

    class DownloadTaak extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String jsonString = "";
            HttpURLConnection connection = null;
            InputStream in = null;
            InputStreamReader reader = null;

            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                in = connection.getInputStream();
                reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char letter = (char) data;
                    jsonString += letter;
                    data = reader.read();
                }

            }catch(Exception e){
                e.printStackTrace();
            } finally {
                if(connection != null){
                    connection.disconnect();
                }
                if(in !=null){
                    try{
                        in.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(reader !=null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
                }
            }

            return jsonString;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            try{
                JSONObject jsonobject = new JSONObject(json);
                String datumtext =  jsonobject.getString("date");
                datum.setText(datumtext);
                JSONObject rate = jsonobject.getJSONObject("rates");
                rates.clear();
                for(String key: KEYS){
                    Double d;

                    try{
                        d = rate.getDouble(key);
                    }catch(JSONException e){
                        d = 0.0;
                    }
                    rates.add(d);
                }
                maakList();
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
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
