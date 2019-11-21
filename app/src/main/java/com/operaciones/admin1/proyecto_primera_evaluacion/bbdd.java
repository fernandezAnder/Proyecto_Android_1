package com.operaciones.admin1.proyecto_primera_evaluacion;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bbdd extends SQLiteOpenHelper {
    private static final String COMMENTS_TABLE_CREATE = "CREATE TABLE tareas(id_tarea INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT,fecha TEXT, coste TEXT,prioridad TEXT, realizada TEXT)";
    private static final String DB_NAME = "tareas.sqlite";
    private static final int DB_VERSION = 1;

    public bbdd(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMMENTS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}