package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Nueva_tarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        //Agregar datos al spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_sino);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_prioridad);
        String[] datos = new String[] {"Si", "No"};
        String[] datos2 = new String[] {"Alta", "Normal","Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos2);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

    }

    public void confirmar(View view){
        final EditText nombre_e = (EditText) findViewById(R.id.text_nombre);
        final EditText descripcion_e = (EditText) findViewById(R.id.text_descrip);
        final EditText fecha_e = (EditText) findViewById(R.id.text_fecha);
        final EditText coste_e = (EditText) findViewById(R.id.text_coste);
        final Spinner spiner = (Spinner) findViewById(R.id.spinner_prioridad);

        final Spinner spiner2 = (Spinner) findViewById(R.id.spinner_sino);


        //convertir a string
        final String nombre = nombre_e.getText().toString();
        final String descripcion = descripcion_e.getText().toString();
        final String fecha = fecha_e.getText().toString();
        final String coste = coste_e.getText().toString();
        final String prioridad = spiner.getSelectedItem().toString();
        final String realizada = spiner2.getSelectedItem().toString();

        if (!"".equals(nombre) |!"".equals(descripcion) |!"".equals(fecha) |!"".equals(coste) |!"".equals(prioridad) |!"".equals(realizada)){
            bbdd conexion = new bbdd(this);
            SQLiteDatabase basedatos = conexion.getWritableDatabase();
            String sql= "INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ("+nombre+","+descripcion+","+fecha+","+coste+","+prioridad+","+realizada+")";
            if (basedatos != null) {
                try{
                    basedatos.rawQuery(sql,null);
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }


                Cursor cursor = basedatos.rawQuery("SELECT nombre FROM tareas",null);

                if (cursor != null) {
                    cursor.moveToFirst();
                    String nombres;
                    do {
                        nombres = cursor.getString(cursor.getColumnIndex("nombre"));
                        System.out.println(nombres);
                    } while (cursor.moveToNext());
                }

            }

            }

    }
}
