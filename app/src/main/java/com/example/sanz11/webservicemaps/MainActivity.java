package com.example.sanz11.webservicemaps;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button dato;
    EditText longitu, latitu;
    TextView resultado;
    ObtenerWebService hiloconexion;
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
                hiloconexion=new ObtenerWebService();
                hiloconexion.execute(latitu.getText().toString(),longitu.getText().toString());

                break;
            default:

                break;
        }
    }


    public class ObtenerWebService extends AsyncTask<Object, Object, String> {

        @Override
        protected String doInBackground(Object... params) {
            //aca nos toca conectar con el web service
            String cadena = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
            cadena= cadena+params[0]+","+params[1]+"&sensor=false";

            String devuelve="";
            URL url= null;//dond equeremos obtener info
            //abrimos conneccion a internet

            try {
                url=new URL(cadena);
                HttpURLConnection connection =(HttpURLConnection) url.openConnection();//abrir la conneccion

                connection.setRequestProperty("User-Agent","Mozilla/5.0"+"(Linux; Android 1.5; es-ES) Ejemplo HTTP");

                int respuesta=connection.getResponseCode();

                StringBuilder result = new StringBuilder();//

                if(respuesta == HttpURLConnection.HTTP_OK){

                    InputStream in = new BufferedInputStream(connection.getInputStream());//para la cadena de entrada

                    BufferedReader reader=new BufferedReader(
                            new InputStreamReader(in));

                    String line;
                    while ((line=reader.readLine()) !=null){
                       result.append(line);
                    }
                    JSONObject respuestaJSON= new JSONObject(result.toString());//creamos el json apartir del stringbuilder
                    JSONArray resultJSON = respuestaJSON.getJSONArray("results");//results es el nombre del campo JSON
                    //RECOGEMOS SOLO LOS CAMPOS QUE NOS INTERESAN DEL JSHON EN ESTE CASO EL HIJO DE results
                    //obtnmos la direccion de los resultados "formatted_address"
                    String direccion ="Sin datos para esa longitud";
                    if(resultJSON.length()>0){
                        direccion=resultJSON.getJSONObject(0).getString("formatted_address");
                    }//solo nos     uedamos con el primer resultado objeto por eso el array 0 del jsonobjet
                    devuelve="Direccion: "+ direccion;


                }

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return devuelve;
        }

        @Override
        protected void onPreExecute() {
            resultado.setText("");
           super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            resultado.setText(aVoid);

        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }
    }
}
