package com.example.tecnoyomuandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class EliminacionSQLite {
    private Context contexto;

    public EliminacionSQLite(Context contexto) {
        this.contexto = contexto;
    }

    public void EliminarEmpleado(int id) {
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("empleado", "id=" + id, null);
            baseDatos.close();
            Toast.makeText(contexto, "Empleado eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
            EliminarUsuario(id);
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el empelado!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void EliminarUsuario(int id) {
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("usuario", "idEmpleado=" + id, null);
            Toast.makeText(contexto, "Usuario eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
            EmpleadoDisponibleParaUsaurio(id, baseDatos);
            baseDatos.close();
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el usuario!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void EliminarCliente(int id) {
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("cliente", "id=" + id, null);
            Toast.makeText(contexto, "Cliente eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
            EliminarEquiposIngresadosSegunCliente(id, baseDatos);
            baseDatos.close();
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el cliente!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void EliminarEquipoIngresado(int id) {
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("equipo", "id=" + id, null);
            baseDatos.close();
            Toast.makeText(contexto, "EquipoIngresado eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el equipo!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    public void EliminarEquiposIngresadosSegunCliente(int idCliente, SQLiteDatabase baseDatos) {
        baseDatos.delete("equipo", "idCliente=" + idCliente, null);
        baseDatos.close();
        Toast.makeText(contexto, "Equipos eliminados satisfactoriamente!", Toast.LENGTH_SHORT).show();
    }

    private void EmpleadoDisponibleParaUsaurio(int id, SQLiteDatabase baseDatos) {
        ContentValues actualizacion = new ContentValues();
        actualizacion.put("disponibleParaUsuario", true);
        String selecc = "id = ?";
        //Clausula where sin valor real para seguridad
        String[] argumentos = {String.valueOf(id)};
        int contador = baseDatos.update("empleado", actualizacion, selecc, argumentos);
        Toast.makeText(contexto, "Éxito al poner disponibilidad de usuario al empleado", Toast.LENGTH_SHORT).show();
    }
}
