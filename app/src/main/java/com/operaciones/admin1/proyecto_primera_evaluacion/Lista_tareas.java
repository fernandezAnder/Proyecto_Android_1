package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Lista_tareas extends AppCompatActivity {

    ArrayList <String> lista_tareas = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);
        LinearLayout llBotonera = (LinearLayout) findViewById(R.id.botonera);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );
        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getReadableDatabase();

        if (basedatos != null){
            Cursor cursor = basedatos.rawQuery("SELECT nombre FROM tareas",null);

            if (cursor != null) {
                cursor.moveToFirst();
                String nombre;
                do {
                    nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                   lista_tareas.add(nombre);
                } while (cursor.moveToNext());
            }

            }

        for (int i=0;i<lista_tareas.size();i++){
            System.out.println(lista_tareas.get(i));
        }

        for (String tarea : lista_tareas){
            Button button = new Button(this);
            //Asignamos propiedades de layout al boton
            button.setLayoutParams(lp);
            //Asignamos Texto al botón
            button.setText(tarea);
            //Añadimos el botón a la botonera
            llBotonera.addView(button);
        }

    }
}
