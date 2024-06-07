package com.example.tecnoyomuandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class EliminacionSQLite {
    private Context contexto;

    public EliminacionSQLite(Context contexto) {
        this.contexto = contexto;
    }

    public void EliminarEmpleado(int id){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("empleado", "id=" + id, null);
            baseDatos.close();
            Toast.makeText(contexto, "Empleado eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el empelado!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
    public void EliminarUsuario(int id){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("usuario", "id=" + id, null);
            baseDatos.close();
            Toast.makeText(contexto, "Usuario eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el usuario!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
    public void EliminarCliente(int id){
        try {
            CrearBDSQLite manejadorBD = new CrearBDSQLite(contexto, "RegistroTecnoyomu", null, 1);
            SQLiteDatabase baseDatos = manejadorBD.getWritableDatabase();
            baseDatos.delete("cliente", "id=" + id, null);
            baseDatos.close();
            Toast.makeText(contexto, "Cliente eliminado satisfactoriamente!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(contexto, "Error al eliminar el cliente!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
    public void EliminarEquipoIngresado(int id){
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
}
