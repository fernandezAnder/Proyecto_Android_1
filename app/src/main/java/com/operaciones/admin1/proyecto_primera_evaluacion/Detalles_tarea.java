package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Detalles_tarea extends AppCompatActivity {


    Tarea tarea = new Tarea("","","","","","");
    protected void onCreate(Bundle savedInstanceState,String nombre) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);

        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getReadableDatabase();

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
}
