package com.example.sanz11.webservicemaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button dato;
        EditText longitu,latitu;
        TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dato=(Button)findViewById(R.id.btn_dato);
        longitu=(EditText)findViewById(R.id.longitu);
        latitu=(EditText)findViewById(R.id.latitu);
        resultado=(TextView)findViewById(R.id.resultado);

        dato.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dato:



                break;
            default:

                break;
        }
    }
}
