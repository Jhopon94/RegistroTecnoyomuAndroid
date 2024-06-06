package com.example.tecnoyomuandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;
import com.example.tecnoyomuandroid.Entidades.Usuario;

public class EdicionSQLite {

    private Context context;
    public EdicionSQLite(Context context){
        this.context = context;
    }

    public void EditarEmpleadoSQLite(Empleado empleado){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            //Abrimos lectura y escritura en las tablas
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Ponemos los valores a registrar
            ContentValues edicion = new ContentValues();
            edicion.put("nombre", empleado.getNombre());
            edicion.put("cargo", empleado.getCargo());
            edicion.put("celular", empleado.getCelular());
            edicion.put("correo", empleado.getCorreo());
            edicion.put("direccion", empleado.getDireccion());
            //Los metemos en la tabla correspondiente
            baseDatos.update("empleado", edicion, "id=" + empleado.getId(), null);
            //Cerramos la base de datos
            baseDatos.close();
            Toast.makeText(context, "Exito al editar empleado", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al editar empleado", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void EditarUsuarioSQLite(Usuario usuario){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            //Abrimos lectura y escritura en las tablas
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Ponemos los valores a registrar
            ContentValues edicion = new ContentValues();
            edicion.put("nombreUsuario", usuario.getNombreUsuario());
            edicion.put("clave", usuario.getClave());
            //Los metemos en la tabla correspondiente
            baseDatos.update("usuario", edicion, "idEmpleado=" + usuario.getIdEmpleado(), null);
            Toast.makeText(context, "Exito al editar usuario", Toast.LENGTH_SHORT).show();
            //Cerramos la base de datos
            baseDatos.close();

        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}
