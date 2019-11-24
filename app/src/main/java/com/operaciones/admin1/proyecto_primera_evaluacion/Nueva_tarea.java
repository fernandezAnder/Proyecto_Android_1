package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Nueva_tarea extends AppCompatActivity {

    Tarea tarea = new Tarea("","","","","","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getWritableDatabase();


        String[] datos = new String[] {"Si", "No"};
        String[] datos2 = new String[] {"Alta", "Normal","Baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos2);



        Spinner prioridad_spinner = findViewById(R.id.spinner_prioridad);
        Spinner realizada_spinner = findViewById(R.id.spinner_sino);

        prioridad_spinner.setAdapter(adapter);
        realizada_spinner.setAdapter(adapter2);

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
            String sql= "INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ("+"'"+nombre+"'"+","+"'"+descripcion+"'"+","+"'"+fecha+"'"+","+"'"+coste+"'"+","+"'"+prioridad+"'"+","+"'"+realizada+"'"+")";



            if (basedatos != null) {
                try {
                    basedatos.execSQL(sql);

            }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        Intent i;
            i = new Intent(this, Menu.class);
            startActivity(i);
            this.finish();

    }

}
    public void cancelar(View view){
        Intent i;
        i = new Intent(this, Menu.class);
        startActivity(i);
        this.finish();
    }


    }