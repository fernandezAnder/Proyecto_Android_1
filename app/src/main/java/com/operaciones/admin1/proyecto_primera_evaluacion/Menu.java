package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void nuevaTarea(View view){
        Intent i;
        i = new Intent(this, Nueva_tarea.class);
        startActivity(i);

    }
    public void ListaTareas(View view){
        Intent i;
        i = new Intent(this, Lista_tareas.class);
        startActivity(i);

    }
}
