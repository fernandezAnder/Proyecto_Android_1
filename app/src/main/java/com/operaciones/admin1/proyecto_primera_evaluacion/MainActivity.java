package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import java.io.Console;

import static java.nio.file.attribute.AclEntryPermission.DELETE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bbdd conexion = new bbdd( this);
        SQLiteDatabase basedatos = conexion.getWritableDatabase();

        if (basedatos != null){

        basedatos.execSQL("delete  from tareas");
        basedatos.execSQL("INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ('Sacar al perro','creacion de consulta','01/01/9999','5','Alta','Si')");
        basedatos.execSQL("INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ('Recoger al crio','creacion de consulta','01/01/9999','5','Normal','No')");
        basedatos.execSQL("INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ('Robar','creacion de consulta','01/01/9999','5','Baja','Si')");
        basedatos.execSQL("INSERT INTO tareas ( nombre , descripcion ,fecha , coste ,prioridad , realizada ) VALUES ('Siesta','creacion de consulta','01/01/9999','5','Alta','No')");

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
            i = new Intent(this, Menu.class);
            startActivity(i);
            this.finish();
        }
    }
}

