package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;

public class RegistrarCliente extends AppCompatActivity {

    private EditText cajitaNombre;
    private EditText cajitaCedula;
    private EditText cajitaCelular;
    private EditText cajitaDireccion;
    private EditText cajitaCorreo;
    private Button btnRegistrar;
    private boolean vieneDesdeLista;
    private Cliente clienteRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        cajitaNombre = (EditText) findViewById(R.id.cajitaModeloEquipoReg);
        cajitaCedula = (EditText) findViewById(R.id.cajitaServicioEquipoReg);
        cajitaCelular = (EditText) findViewById(R.id.cajitaPrecioEquipoReg);
        cajitaDireccion = (EditText) findViewById(R.id.cajitaAbonoEquipoReg);
        cajitaCorreo = (EditText) findViewById(R.id.cajitaCorreoCliente);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarCliente);
        clienteRecibido = new Cliente();

        vieneDesdeLista = this.getIntent().hasExtra("vieneDeLista");
        if (vieneDesdeLista) {
            clienteRecibido = (Cliente) this.getIntent().getSerializableExtra("vieneDeLista");
            ModoDetalles();
        }
    }

    public void Registrar(View vista) {
        //Capturamos los textos de las cajitas
        String nombre = cajitaNombre.getText().toString();
        String cedula = cajitaCedula.getText().toString(); //Convertir a numero mas adelante
        String celular = cajitaCelular.getText().toString();
        String direccion = cajitaDireccion.getText().toString();
        String correo = cajitaCorreo.getText().toString();

        if (nombre.isEmpty() || cedula.isEmpty() || celular.isEmpty()) {
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

    private void ModoDetalles() {
        cajitaNombre.setEnabled(false);
        cajitaNombre.setBackgroundColor(Color.LTGRAY);
        cajitaNombre.setText(clienteRecibido.getNombre());
        cajitaCedula.setEnabled(false);
        cajitaCedula.setBackgroundColor(Color.LTGRAY);
        cajitaCedula.setText(String.valueOf(clienteRecibido.getId()));
        cajitaCelular.setEnabled(false);
        cajitaCelular.setBackgroundColor(Color.LTGRAY);
        cajitaCelular.setText(clienteRecibido.getCelular());
        cajitaDireccion.setEnabled(false);
        cajitaDireccion.setBackgroundColor(Color.LTGRAY);
        cajitaDireccion.setText(clienteRecibido.getDireccion());
        cajitaCorreo.setEnabled(false);
        cajitaCorreo.setBackgroundColor(Color.LTGRAY);
        cajitaCorreo.setText(clienteRecibido.getCorreo());
        btnRegistrar.setText("Editar");
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModoEdicion();
            }
        });
    }

    private void ModoEdicion() {
        cajitaNombre.setEnabled(true);
        cajitaNombre.setBackgroundColor(Color.WHITE);
        cajitaNombre.setText(clienteRecibido.getNombre());
        cajitaCedula.setText(String.valueOf(clienteRecibido.getId()));
        cajitaCelular.setEnabled(true);
        cajitaCelular.setBackgroundColor(Color.WHITE);
        cajitaCelular.setText(clienteRecibido.getCelular());
        cajitaDireccion.setEnabled(true);
        cajitaDireccion.setBackgroundColor(Color.WHITE);
        cajitaDireccion.setText(clienteRecibido.getDireccion());
        cajitaCorreo.setEnabled(true);
        cajitaCorreo.setBackgroundColor(Color.WHITE);
        cajitaCorreo.setText(clienteRecibido.getCorreo());
        btnRegistrar.setText("Registrar");
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        });
    }

    public void Editar() {
        //Capturamos los textos de las cajitas
        String nombre = cajitaNombre.getText().toString();
        String cedula = cajitaCedula.getText().toString(); //Convertir a numero mas adelante
        String celular = cajitaCelular.getText().toString();
        String direccion = cajitaDireccion.getText().toString();
        String correo = cajitaCorreo.getText().toString();

        if (nombre.isEmpty() || cedula.isEmpty() || celular.isEmpty()) {
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
            EdicionSQLite editar = new EdicionSQLite(this);
            editar.EditarClienteSQLite(cliente);
            FinalizarConResultado();
        }

    }

    private void FinalizarConResultado(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void Cancelar(View vista) {
        finish();
    }
}