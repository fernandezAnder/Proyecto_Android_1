package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista_tareas extends AppCompatActivity {

    ArrayList <String> lista_tareas = new ArrayList<String>();
    String nombre_lista;
    ListView lista ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_tareas);

        final bbdd conexion = new bbdd(this);
        final SQLiteDatabase basedatos = conexion.getReadableDatabase();

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

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                String[] opc = new String[] { "Eliminar Tarea"};
                nombre_lista = (String) lista.getItemAtPosition(pos);
                AlertDialog opciones = new AlertDialog.Builder(
                        Lista_tareas.this)
                        .setTitle("Opciones")
                        .setItems(opc, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int selected) {
                                        if (selected == 0) {

                                            System.out.println("------------------ El nombre es:  "+nombre_lista);

                                            final SQLiteDatabase basedatos = conexion.getWritableDatabase();

                                            if (basedatos != null) {
                                                try {
                                                    basedatos.execSQL("delete  from tareas where nombre = '" + nombre_lista + "'");
                                                }catch (SQLException e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                        }


                                    }
                                }).create();
                opciones.show();

            return true;
            }


        });



        }





}
