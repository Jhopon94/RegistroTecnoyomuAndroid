package com.example.tecnoyomuandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Empleado;
import com.example.tecnoyomuandroid.Entidades.Usuario;

public class ConsultaIndividualSQLite {
    private Context contexto;

    public ConsultaIndividualSQLite(Context contexto) {
        this.contexto = contexto;
    }

    public Empleado ConsultarEmpleado(int id){
        try {
            Empleado empleado = new Empleado();
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            Cursor empleados = baseDatos.rawQuery("SELECT * FROM empleado WHERE id=" + id, null);
            if(empleados.moveToFirst()){
                do{
                    empleado.setNombre(empleados.getString(empleados.getColumnIndexOrThrow("nombre")));
                    empleado.setId(empleados.getInt(empleados.getColumnIndexOrThrow("id")));
                    empleado.setCelular(empleados.getString(empleados.getColumnIndexOrThrow("celular")));
                    empleado.setDireccion(empleados.getString(empleados.getColumnIndexOrThrow("direccion")));
                    empleado.setCargo(empleados.getString(empleados.getColumnIndexOrThrow("cargo")));
                    empleado.setActivo(empleados.getInt(empleados.getColumnIndexOrThrow("activo")) > 0);
                    empleado.setDisponibleParaUsuario(empleados.getInt(empleados.getColumnIndexOrThrow("disponibleParaUsuario")) > 0);
                    return empleado;
                }while(empleados.moveToNext());
            }else {
                Toast.makeText(contexto, "No se encontró empleado con el id proporcionado!", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al consultar Empleado!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public Cliente ConsultarCliente(int id){
        try {
            Cliente cliente = new Cliente();
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            Cursor clientes = baseDatos.rawQuery("SELECT * FROM cliente WHERE id=" + id, null);
            if(clientes.moveToFirst()){
                do{
                    cliente.setNombre(clientes.getString(clientes.getColumnIndexOrThrow("nombre")));
                    cliente.setId(clientes.getInt(clientes.getColumnIndexOrThrow("id")));
                    cliente.setCelular(clientes.getString(clientes.getColumnIndexOrThrow("celular")));
                    cliente.setDireccion(clientes.getString(clientes.getColumnIndexOrThrow("direccion")));
                    cliente.setCorreo(clientes.getString(clientes.getColumnIndexOrThrow("correo")));
                    return cliente;
                }while(clientes.moveToNext());
            }else {
                Toast.makeText(contexto, "No se encontró cliente con el id proporcionado!", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al consultar Cliente!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}
