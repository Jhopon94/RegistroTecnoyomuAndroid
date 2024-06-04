package com.example.tecnoyomuandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Empleado;
import com.example.tecnoyomuandroid.Entidades.Equipo;
import com.example.tecnoyomuandroid.Entidades.Usuario;

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


    public void RegistrarUsuarioSQLite(Usuario usuario){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            //Abrimos lectura y escritura en las tablas
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Ponemos los valores a registrar
            ContentValues registro = new ContentValues();
            registro.put("idEmpleado", usuario.getIdEmpleado());
            registro.put("nombreUsuario", usuario.getNombreUsuario());
            registro.put("clave", usuario.getClave());
            registro.put("rol", usuario.getRol());
            //Los metemos en la tabla correspondiente
            baseDatos.insert("usuario", null, registro);
            Toast.makeText(context, "Exito al registrar usuario", Toast.LENGTH_SHORT).show();
            //Procedemos a quitar disponibilidad al empleado como usuario
            NoDisponibleUsuario(usuario.getIdEmpleado(), manejadorBD, baseDatos);
            //Cerramos la base de datos
            baseDatos.close();

        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    // /// Función especial para acomodar empleado como no disponoble para usuario
    private void NoDisponibleUsuario(int id, CrearBDSQLite manejadorBD, SQLiteDatabase baseDatos){
        try {
            ContentValues actualizacion = new ContentValues();
            actualizacion.put("disponibleParaUsuario", false);
            String selecc = "id = ?";
            //Clausula where sin valor real para seguridad
            String[] argumentos = {String.valueOf(id)};
            int contador = baseDatos.update("empleado", actualizacion, selecc, argumentos);
            Toast.makeText(context, "Éxito al quitar disponibilidad de usuario al empleado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al quitar disponibilidad de usuario al empleado", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void RegistrarClienteSQLite(Cliente cliente){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            //Abrimos lectura y escritura en las tablas
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Ponemos los valores a registrar
            ContentValues registro = new ContentValues();
            registro.put("id", cliente.getId());
            registro.put("nombre", cliente.getNombre());
            registro.put("celular", cliente.getCelular());
            registro.put("correo", cliente.getCorreo());
            registro.put("direccion", cliente.getDireccion());
            //Los metemos en la tabla correspondiente
            baseDatos.insert("cliente", null, registro);
            //Cerramos la base de datos
            baseDatos.close();
            Toast.makeText(context, "Exito al registrar cliente", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar cliente", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void RegistrarEquipoSQLite(Equipo equipo){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(context, "RegistroTecnoyomu", null, 1);
            //Abrimos lectura y escritura en las tablas
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            //Ponemos los valores a registrar
            ContentValues registro = new ContentValues();
            registro.put("idCliente", equipo.getIdCliente());
            registro.put("modelo", equipo.getModelo());
            registro.put("servicio", equipo.getServicio());
            registro.put("precio", equipo.getPrecio());
            registro.put("saldoPendiente", equipo.getSaldoPendiente());
            //Los metemos en la tabla correspondiente
            baseDatos.insert("equipo", null, registro);
            //Cerramos la base de datos
            baseDatos.close();
            Toast.makeText(context, "Exito al registrar equipo", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, "Error al registrar equipo", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}
