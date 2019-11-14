package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Detalles_tarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState,String nombre) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_tarea);

        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getReadableDatabase();

        if (basedatos != null){
            String sql = "SELECT * FROM tareas WHERE nombre ="+"'"+nombre+",";

            /*
            if (cursor != null) {
                cursor.moveToFirst();
                String nombre;
                do {
                    nombre = cursor.getString(cursor.getColumnIndex("nombre"));

                } while (cursor.moveToNext());
            }*/}
    }
}
