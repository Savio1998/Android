package com.appspot.httpphp_savio.money;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MoneyTransfer extends AppCompatActivity {
    EditText currency;
    TextView viewmoney;
    Button dollareuro, eurodollar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);
            EditText currency = (EditText) findViewById(R.id.currency);
            Button eurodollar = (Button) findViewById (R.id.eurodollar);
            Button dollareuro = (Button) findViewById (R.id.dollareuro);
            TextView viewmoney = (TextView) findViewById (R.id.moneyview);
    }
    public void clickeuro (View view){

        Double dollar = currency.getText().;
        Double eudo = dollar * 1.12885;
        viewmoney.setText("$ " + eudo);
    }

}
