package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Detalles_tarea extends AppCompatActivity {


    Tarea tarea = new Tarea("","","","","","");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);

        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getReadableDatabase();
        String nombre = getIntent().getExtras().getString("nombre");
        System.out.println("EL NOMBRE ES -------"+nombre);
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

        TextView detalle_nombre = findViewById(R.id.detalle_nombre);
        TextView detalle_descripcion = findViewById(R.id.detalle_descrip);
        TextView detalle_fecha = findViewById(R.id.detalle_fecha);
        TextView detalle_coste = findViewById(R.id.detalle_coste);
        TextView detalle_prioridad = findViewById(R.id.detalle_prioridad);
        TextView detalle_realizada = findViewById(R.id.detalle_realizada);

        detalle_nombre.setText(tarea.getNombre());

         detalle_descripcion.setText(tarea.getDescripcion());
         detalle_fecha.setText("Para el "+tarea.getFecha());
         detalle_coste.setText("Tiene un coste de "+tarea.getCoste()+" €");
         detalle_prioridad.setText(tarea.getPrioridad());
        if ("Alta".equals(tarea.getPrioridad())){
             detalle_prioridad.setTextColor(Color.RED);
         }
        if ("Normal".equals(tarea.getPrioridad())){
            detalle_prioridad.setTextColor(Color.YELLOW);
        }
        if ("Baja".equals(tarea.getPrioridad())){
            detalle_prioridad.setTextColor(Color.GREEN);
        }
         detalle_realizada.setText("Realizada: "+tarea.getRealizada());

    }

    protected void modificar (View view){
        Intent i;
        String detalle_nombre = tarea.getNombre();
        i = new Intent(this, Nueva_tarea.class);
        i.putExtra("nombre",detalle_nombre);
        startActivity(i);
    }
    protected void volver(View view){
        Intent i;
        i = new Intent(this, Lista_tareas.class);
        startActivity(i);
    }
}
