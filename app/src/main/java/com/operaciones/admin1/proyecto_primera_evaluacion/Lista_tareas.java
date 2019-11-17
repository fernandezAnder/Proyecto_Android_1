package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Lista_tareas extends AppCompatActivity {

    ArrayList <String> lista_tareas = new ArrayList<String>();

    ListView lista ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_tareas);

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
        lista = findViewById(R.id.listview);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista_tareas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int position, long l) {
                String nombre = (String) lista.getItemAtPosition(position);


                Intent i;
                i = new Intent(Lista_tareas.this, Detalles_tarea.class);
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });



    }

}
