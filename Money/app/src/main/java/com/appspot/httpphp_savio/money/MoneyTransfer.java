package com.appspot.httpphp_savio.money;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MoneyTransfer extends AppCompatActivity {
    EditText currency;
    TextView viewmoney;
    Button dollareuro, eurodollar;
    DecimalFormat decimalFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);
            currency = (EditText) findViewById(R.id.currency);
            eurodollar = (Button) findViewById (R.id.eurodollar);
            dollareuro = (Button) findViewById (R.id.dollareuro);
            viewmoney = (TextView) findViewById (R.id.moneyview);
    }
    public void eurodollar (View view){
        decimalFormat = new DecimalFormat("$ ###,###.##");
        String dollartxt = currency.getText().toString();
        Double dollar = Double.parseDouble(dollartxt);
        Double eudo = dollar * 1.12885;
        String eudoeudo = decimalFormat.format(eudo);

        viewmoney.setText(eudoeudo);
    }
    public void dollareuro (View view){
        decimalFormat = new DecimalFormat("€ ###,###.##");
        String eurotxt = currency.getText().toString();
        Double euro = Double.parseDouble(eurotxt);
        Double doeu = (euro / 112.885) * 100;
        String doeudoeu = decimalFormat.format(doeu);

        viewmoney.setText(doeudoeu);
    }


}
