package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Empleado;

public class RegistrarCliente extends AppCompatActivity {

    private EditText cajitaNombre;
    private EditText cajitaCedula;
    private EditText cajitaCelular;
    private EditText cajitaDireccion;
    private EditText cajitaCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        cajitaNombre = (EditText) findViewById(R.id.cajitaNombreCliente);
        cajitaCedula = (EditText) findViewById(R.id.cajitaCedulaCliente);
        cajitaCelular = (EditText) findViewById(R.id.cajitaCelularCliente);
        cajitaDireccion = (EditText) findViewById(R.id.cajitaDireccionCliente);
        cajitaCorreo = (EditText) findViewById(R.id.cajitaCorreoCliente);
    }

    public void Registrar(View vista) {
        //Capturamos los textos de las cajitas
        String nombre = cajitaNombre.getText().toString();
        String cedula = cajitaCedula.getText().toString(); //Convertir a numero mas adelante
        String celular = cajitaCelular.getText().toString();
        String direccion = cajitaDireccion.getText().toString();
        String correo = cajitaCorreo.getText().toString();

        if (nombre.isEmpty() || cedula.isEmpty() ||  celular.isEmpty()) {
            Toast.makeText(this, "Obligatorio nombre, cédula y celular!", Toast.LENGTH_SHORT).show();
        } else {
            //Si los campos no están vacíos
            //Iniciamos el objeto Cliente
            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setId(Integer.parseInt(cedula));
            cliente.setCelular(celular);
            cliente.setDireccion(direccion);
            cliente.setCorreo(correo);
            //Instanciamos el registro y registramos!
            RegistroSQLite registrar = new RegistroSQLite(this);
            registrar.RegistrarClienteSQLite(cliente);
            finish();//Cerrar ventana de registro
        }

    }

    public void Cancelar(View vista) {
        finish();
    }
}