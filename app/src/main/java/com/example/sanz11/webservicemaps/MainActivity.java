package com.example.sanz11.webservicemaps;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button dato;
    EditText longitu, latitu;
    TextView resultado;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dato = (Button) findViewById(R.id.btn_dato);
        longitu = (EditText) findViewById(R.id.longitu);
        latitu = (EditText) findViewById(R.id.latitu);
        resultado = (TextView) findViewById(R.id.resultado);

        dato.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dato:


                break;
            default:

                break;
        }
    }


    public class ObtenerWebService extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            //aca nos toca conectar con el web service
            String cadena = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
            cadena= cadena+params[0]+","+params[1]+"&sensor=false";


            URL url= null;//dond equeremos obtener info
            //abrimos conneccion a internet
            try {
                url=new URL(cadena);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();//abrir la conneccion

                connection.setRequestProperty("User-Agent","Mozilla/5.0"+"(Linux; Android 1.5; es-ES) Ejemplo HTTP");

                int respuesta=connection.getResponseCode();

                if(respuesta == HttpURLConnection.HTTP_OK){
                    BufferedReader lector=new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));



                }

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }
}
