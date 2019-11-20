package com.operaciones.admin1.proyecto_primera_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
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
        lista.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ContextMenu menu = new ContextMenu() {
                    @Override
                    public ContextMenu setHeaderTitle(int titleRes) {
                        return null;
                    }

                    @Override
                    public ContextMenu setHeaderTitle(CharSequence title) {
                        return null;
                    }

                    @Override
                    public ContextMenu setHeaderIcon(int iconRes) {
                        return null;
                    }

                    @Override
                    public ContextMenu setHeaderIcon(Drawable icon) {
                        return null;
                    }

                    @Override
                    public ContextMenu setHeaderView(View view) {
                        return null;
                    }

                    @Override
                    public void clearHeader() {

                    }

                    @Override
                    public MenuItem add(CharSequence title) {
                        return null;
                    }

                    @Override
                    public MenuItem add(int titleRes) {
                        return null;
                    }

                    @Override
                    public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
                        return null;
                    }

                    @Override
                    public MenuItem add(int groupId, int itemId, int order, int titleRes) {
                        return null;
                    }

                    @Override
                    public SubMenu addSubMenu(CharSequence title) {
                        return null;
                    }

                    @Override
                    public SubMenu addSubMenu(int titleRes) {
                        return null;
                    }

                    @Override
                    public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
                        return null;
                    }

                    @Override
                    public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
                        return null;
                    }

                    @Override
                    public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
                        return 0;
                    }

                    @Override
                    public void removeItem(int id) {

                    }

                    @Override
                    public void removeGroup(int groupId) {

                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {

                    }

                    @Override
                    public void setGroupVisible(int group, boolean visible) {

                    }

                    @Override
                    public void setGroupEnabled(int group, boolean enabled) {

                    }

                    @Override
                    public boolean hasVisibleItems() {
                        return false;
                    }

                    @Override
                    public MenuItem findItem(int id) {
                        return null;
                    }

                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public MenuItem getItem(int index) {
                        return null;
                    }

                    @Override
                    public void close() {

                    }

                    @Override
                    public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
                        return false;
                    }

                    @Override
                    public boolean isShortcutKey(int keyCode, KeyEvent event) {
                        return false;
                    }

                    @Override
                    public boolean performIdentifierAction(int id, int flags) {
                        return false;
                    }

                    @Override
                    public void setQwertyMode(boolean isQwerty) {

                    }
                };
                return false;
            }
        });



    }

}
