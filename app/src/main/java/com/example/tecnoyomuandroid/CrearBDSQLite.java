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
        BaseDatos.execSQL("create table empleado(id INTEGER primary key unique not null, " +
                "nombre varchar(100) not null, cargo varchar(20) not null, " +
                "celular varchar(20) not null, direccion varchar(20) not null, " +
                "correo varchar(20) not null, disponibleParaUsuario boolean not null default true, " +
                "activo boolean not null default true, " +
                "fechaRegistro timestamp not null default current_timestamp)");

        BaseDatos.execSQL("create table usuario(idEmpleado INTEGER primary key unique not null, " +
                "nombreUsuario varchar(20) not null, clave varchar(20) not null, " +
                "rol varchar(20) not null, fechaRegistro timestamp not null default current_timestamp)");

        BaseDatos.execSQL("create table cliente(id INTEGER primary key unique not null, nombre varchar(100) not null, celular varchar(20) not null, " +
                "correo varchar(100), direccion varchar(100), serviciosTomados int not null default 1, " +
                "fechaRegistro timestamp not null default current_timestamp)");

        BaseDatos.execSQL("create table equipo(id INTEGER primary key autoincrement unique not null, idCliente INTEGER not null, modelo varchar(150) not null, " +
                "servicio varchar(150) not null, precio int not null, saldoPendiente int not null, estado varchar(10) not null default 'ingresado', " +
                "fechaIngreso timestamp not null default current_timestamp, entregado boolean not null default false)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
