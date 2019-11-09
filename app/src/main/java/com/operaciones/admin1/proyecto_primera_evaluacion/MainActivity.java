package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bbdd conexion = new bbdd(this);
        SQLiteDatabase basedatos = conexion.getReadableDatabase();

        if (basedatos != null){
        basedatos.execSQL("INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ('Prueba1','creacion de consulta','01/01/9999','5','Alta','Si')");
        }

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("admin", "admin");
        editor.putString("admin", "admin");
        editor.commit();
    }
    public void login(View view){
        Intent i;

        final EditText usuario_getter = (EditText) findViewById(R.id.usuarioTxt);
        final EditText passwd_getter = (EditText) findViewById(R.id.passwdTxt);

        String usuario_textbox =  usuario_getter.getText().toString();
        String passwd_textbox = passwd_getter.getText().toString();
        String usuario;
        String passwd;
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        usuario = prefs.getString("admin",
                "Usuario no encontrado");
        passwd = prefs.getString("admin",
                "Contraseña no encontrada");

        if ( usuario_textbox.equals(usuario) && passwd_textbox.equals(passwd)) {
            i = new Intent(this, Lista_tareas.class);
            startActivity(i);
        }
    }
}
