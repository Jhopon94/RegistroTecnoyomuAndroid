package com.example.tecnoyomuandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CrearBDSQLite extends SQLiteOpenHelper {


    public CrearBDSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatos) {
        BaseDatos.execSQL("create table empleado(id int primary key, " +
                "nombre varchar(100) not null, cargo varchar(20) not null, " +
                "celular varchar(20) not null, direccion varchar(20) not null, " +
                "correo varchar(20) not null, disponibleParaUsuario boolean not null default true, " +
                "activo boolean not null default true, " +
                "fechaRegistro timestamp not null default current_timestamp)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
