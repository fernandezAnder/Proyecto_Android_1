package com.operaciones.admin1.proyecto_primera_evaluacion;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Modificar_tarea extends AppCompatActivity {

    Tarea tarea = new Tarea("","","","","","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modificar_tarea);
        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getWritableDatabase();
        String nombre = getIntent().getExtras().getString("nombre");
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

        if (!"".equals(nombre)){
            if (basedatos != null){


                Cursor cursor = basedatos.rawQuery("SELECT * FROM tareas WHERE nombre = '"+nombre+"'",null);


                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        tarea.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                        tarea.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                        tarea.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
                        tarea.setCoste(cursor.getString(cursor.getColumnIndex("coste")));
                        tarea.setPrioridad(cursor.getString(cursor.getColumnIndex("prioridad")));
                        tarea.setRealizada(cursor.getString(cursor.getColumnIndex("realizada")));
                    } while (cursor.moveToNext());
                }
            }
        }
        EditText nombre_text = findViewById(R.id.text_nombre);
        EditText descrip_text = findViewById(R.id.text_descrip);
        EditText fecha_text = findViewById(R.id.text_fecha);
        EditText coste_text = findViewById(R.id.text_coste);
        Spinner prioridad_spinner = findViewById(R.id.spinner_prioridad);
        Spinner realizada_spinner = findViewById(R.id.spinner_sino);

        nombre_text.setText(tarea.getNombre());
        descrip_text.setText(tarea.getDescripcion());
        fecha_text.setText(tarea.getFecha());
        coste_text.setText(tarea.getCoste());
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
            basedatos.execSQL("delete from tareas where nombre = '"+nombre+"'");
            String sql= "INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ("+"'"+nombre+"'"+","+"'"+descripcion+"'"+","+"'"+fecha+"'"+","+"'"+coste+"'"+","+"'"+prioridad+"'"+","+"'"+realizada+"'"+")";



            if (basedatos != null) {
                try {
                    basedatos.execSQL(sql);

            }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        Intent i;
            i = new Intent(this, Lista_tareas.class);
            startActivity(i);

    }

}
    public void cancelar(View view){
        Intent i;
        i = new Intent(this, Detalles_tarea.class);
        startActivity(i);
    }

    }