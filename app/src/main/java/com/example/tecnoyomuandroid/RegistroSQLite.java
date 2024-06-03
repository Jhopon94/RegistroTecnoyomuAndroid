package com.example.tecnoyomuandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;

public class RegistroSQLite {

    private Context context;
    public RegistroSQLite(Context context){
        this.context = context;
    }

    public void RegistrarEmpleadoSQLite(Empleado empleado){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            //Abrimos lectura y escritura en las tablas
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Ponemos los valores a registrar
            ContentValues registro = new ContentValues();
            registro.put("id", empleado.getId());
            registro.put("nombre", empleado.getNombre());
            registro.put("cargo", empleado.getCargo());
            registro.put("celular", empleado.getCelular());
            registro.put("correo", empleado.getCorreo());
            registro.put("direccion", empleado.getDireccion());
            //Los metemos en la tabla correspondiente
            baseDatos.insert("empleado", null, registro);
            //Cerramos la base de datos
            baseDatos.close();
            Toast.makeText(context, "Exito al registrar empleado", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar empleado", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}
