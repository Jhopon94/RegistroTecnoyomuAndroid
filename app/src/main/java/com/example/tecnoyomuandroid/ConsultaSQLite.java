package com.example.tecnoyomuandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;
import com.example.tecnoyomuandroid.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ConsultaSQLite {

    private Context context;

    public ConsultaSQLite(Context context) {
        this.context = context;
    }

    public List<Empleado> ConsultarEmpleadosSQLite() {
        try {
            List<Empleado> listaAuxiliar = new ArrayList<>();
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Obtenemos los datos de la base de datos
            Cursor empleados = baseDatos.rawQuery("select * from empleado", null);
            //Verificar que la consulta tenga datos
            if (empleados.moveToFirst()) {
                //Obtenemos todos los datos de la tabla
                do {
                    Empleado empleado = new Empleado();
                    empleado.setId(empleados.getInt(empleados.getColumnIndexOrThrow("id")));
                    empleado.setNombre(empleados.getString(empleados.getColumnIndexOrThrow("nombre")));
                    empleado.setCargo(empleados.getString(empleados.getColumnIndexOrThrow("cargo")));
                    empleado.setCelular(empleados.getString(empleados.getColumnIndexOrThrow("celular")));
                    empleado.setDireccion(empleados.getString(empleados.getColumnIndexOrThrow("direccion")));
                    empleado.setCorreo(empleados.getString(empleados.getColumnIndexOrThrow("correo")));
                    empleado.setDisponibleParaUsuario(empleados.getInt(empleados.getColumnIndexOrThrow("disponibleParaUsuario")) > 0);
                    empleado.setActivo(empleados.getInt(empleados.getColumnIndexOrThrow("activo")) > 0);
                    //Agregar el empleado a la lista
                    listaAuxiliar.add(empleado);
                } while (empleados.moveToNext());
                baseDatos.close();
                return listaAuxiliar;
            } else {
                Toast.makeText(context, "No hay registros de empleados", Toast.LENGTH_SHORT).show();
                baseDatos.close();
                return listaAuxiliar;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al consultar empleados en base de datos!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public List<Empleado> ConsultarEmpleadosParaUsuarioSQLite() {
        try {
            List<Empleado> listaAuxiliar = new ArrayList<>();
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Obtenemos los datos de la base de datos
            Cursor empleados = baseDatos.rawQuery("select * from empleado where disponibleparausuario=true", null);
            //Verificar que la consulta tenga datos
            if (empleados.moveToFirst()) {
                //Obtenemos todos los datos de la tabla
                do {
                    Empleado empleado = new Empleado();
                    empleado.setId(empleados.getInt(empleados.getColumnIndexOrThrow("id")));
                    empleado.setNombre(empleados.getString(empleados.getColumnIndexOrThrow("nombre")));
                    empleado.setCargo(empleados.getString(empleados.getColumnIndexOrThrow("cargo")));
                    empleado.setCelular(empleados.getString(empleados.getColumnIndexOrThrow("celular")));
                    empleado.setDireccion(empleados.getString(empleados.getColumnIndexOrThrow("direccion")));
                    empleado.setCorreo(empleados.getString(empleados.getColumnIndexOrThrow("correo")));
                    empleado.setDisponibleParaUsuario(empleados.getInt(empleados.getColumnIndexOrThrow("disponibleParaUsuario")) > 0);
                    empleado.setActivo(empleados.getInt(empleados.getColumnIndexOrThrow("activo")) > 0);
                    //Agregar el empleado a la lista
                    listaAuxiliar.add(empleado);
                } while (empleados.moveToNext());
                baseDatos.close();
                return listaAuxiliar;
            } else {
                Toast.makeText(context, "No hay empelados disponibles para usuario", Toast.LENGTH_SHORT).show();
                baseDatos.close();
                return listaAuxiliar;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al consultar empleados en base de datos!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> ConsultarUsuariosSQLite() {
        try {
            List<Usuario> listaAuxiliar = new ArrayList<>();
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Obtenemos los datos de la base de datos
            Cursor usuarios = baseDatos.rawQuery("select * from usuario", null);
            //Verificar que la consulta tenga datos
            if (usuarios.moveToFirst()) {
                //Obtenemos todos los datos de la tabla
                do {
                    Usuario usuario = new Usuario();
                    usuario.setIdEmpleado(usuarios.getInt(usuarios.getColumnIndexOrThrow("idEmpleado")));
                    usuario.setNombreUsuario(usuarios.getString(usuarios.getColumnIndexOrThrow("nombreUsuario")));
                    usuario.setClave(usuarios.getString(usuarios.getColumnIndexOrThrow("clave")));
                    usuario.setRol(usuarios.getString(usuarios.getColumnIndexOrThrow("rol")));
                    //Agregar el usuario a la lista
                    listaAuxiliar.add(usuario);
                } while (usuarios.moveToNext());
                baseDatos.close();
                return listaAuxiliar;
            } else {
                Toast.makeText(context, "No hay registros de usuarios", Toast.LENGTH_SHORT).show();
                baseDatos.close();
                return listaAuxiliar;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al consultar usuarios en base de datos!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}
