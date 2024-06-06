package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;

public class RegistrarEmpleado extends AppCompatActivity {

    private EditText cajitaNombre;
    private EditText cajitaCedula;
    private Spinner spCargo;
    private EditText cajitaCelular;
    private EditText cajitaDireccion;
    private EditText cajitaCorreo;
    private Button btnRegistrar;
    private boolean vieneDeLista;
    private Empleado empleadoDesdeLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empleado);

        cajitaNombre = (EditText) findViewById(R.id.cajitaModeloEquipoReg);
        cajitaCedula = (EditText) findViewById(R.id.cajitaServicioEquipoReg);
        spCargo = (Spinner) findViewById(R.id.spinnerCargo);
        cajitaCelular = (EditText) findViewById(R.id.cajitaPrecioEquipoReg);
        cajitaDireccion = (EditText) findViewById(R.id.cajitaAbonoEquipoReg);
        cajitaCorreo = (EditText) findViewById(R.id.cajitaCorreoCliente);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarCliente);
        empleadoDesdeLista = new Empleado();

        //Entrar en modo detalles si viene desde la lista
        vieneDeLista = this.getIntent().hasExtra("vieneDeLista");
        if (vieneDeLista) {
            empleadoDesdeLista = (Empleado) this.getIntent().getSerializableExtra("vieneDeLista");
            modoDetalles();
        }
    }

    public void Registrar(View vista) {
        //Capturamos los textos de las cajitas
        String nombre = cajitaNombre.getText().toString();
        String cedula = cajitaCedula.getText().toString(); //Convertir a numero mas adelante
        String cargo = spCargo.getSelectedItem().toString();
        String celular = cajitaCelular.getText().toString();
        String direccion = cajitaDireccion.getText().toString();
        String correo = cajitaCorreo.getText().toString();

        if (nombre.isEmpty() || cedula.isEmpty() || cargo.isEmpty() || celular.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos!", Toast.LENGTH_SHORT).show();
        } else {
            //Si los campos no están vacíos
            //Iniciamos el objeto Empleado
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre);
            empleado.setId(Integer.parseInt(cedula));
            empleado.setCargo(cargo);
            empleado.setCelular(celular);
            empleado.setDireccion(direccion);
            empleado.setCorreo(correo);
            //Instanciamos el registro y registramos!
            RegistroSQLite registrar = new RegistroSQLite(this);
            registrar.RegistrarEmpleadoSQLite(empleado);
            finish();//Cerrar ventana de registro
        }

    }


    public void Editar(View vista) {
        //Capturamos los textos de las cajitas
        String nombre = cajitaNombre.getText().toString();
        String cedula = cajitaCedula.getText().toString(); //Convertir a numero mas adelante
        String cargo = spCargo.getSelectedItem().toString();
        String celular = cajitaCelular.getText().toString();
        String direccion = cajitaDireccion.getText().toString();
        String correo = cajitaCorreo.getText().toString();

        if (nombre.isEmpty() || cedula.isEmpty() || cargo.isEmpty() || celular.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos!", Toast.LENGTH_SHORT).show();
        } else {
            //Si los campos no están vacíos
            //Iniciamos el objeto Empleado
            Empleado empleado = new Empleado();
            empleado.setNombre(nombre);
            empleado.setId(Integer.parseInt(cedula));
            empleado.setCargo(cargo);
            empleado.setCelular(celular);
            empleado.setDireccion(direccion);
            empleado.setCorreo(correo);
            //Instanciamos la edicion y registramos!
            EdicionSQLite editar = new EdicionSQLite(this);
            editar.EditarEmpleadoSQLite(empleado);
            EnviarResultadoALista();//Cerrar ventana de registro
        }

    }

    private void modoEditar() {
        cajitaNombre.setEnabled(true);
        cajitaNombre.setBackgroundColor(Color.WHITE);
        cajitaCelular.setEnabled(true);
        cajitaCelular.setBackgroundColor(Color.WHITE);
        cajitaDireccion.setEnabled(true);
        cajitaDireccion.setBackgroundColor(Color.WHITE);
        cajitaCorreo.setEnabled(true);
        cajitaCorreo.setBackgroundColor(Color.WHITE);
        spCargo.setEnabled(true);
        btnRegistrar.setText("Registrar");
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar(v);
            }
        });
    }

    private void modoDetalles() {
        cajitaNombre.setEnabled(false);
        cajitaNombre.setBackgroundColor(Color.LTGRAY);
        cajitaNombre.setText(empleadoDesdeLista.getNombre());
        cajitaCedula.setEnabled(false);
        cajitaCedula.setBackgroundColor(Color.LTGRAY);
        cajitaCedula.setText(String.valueOf(empleadoDesdeLista.getId()));
        cajitaCelular.setEnabled(false);
        cajitaCelular.setBackgroundColor(Color.LTGRAY);
        cajitaCelular.setText(String.valueOf(empleadoDesdeLista.getCelular()));
        cajitaDireccion.setEnabled(false);
        cajitaDireccion.setBackgroundColor(Color.LTGRAY);
        cajitaDireccion.setText(empleadoDesdeLista.getDireccion());
        cajitaCorreo.setEnabled(false);
        cajitaCorreo.setBackgroundColor(Color.LTGRAY);
        cajitaCorreo.setText(empleadoDesdeLista.getCorreo());
        spCargo.setEnabled(false);
        //Sincronizar la opción del spinner con la que tiene el empleado obtenido del intent
        ArrayAdapter<String> adaptadorSP = (ArrayAdapter<String>) spCargo.getAdapter();
        for (int i = 0; i < adaptadorSP.getCount(); i++) {
            if (adaptadorSP.getItem(i).equals(empleadoDesdeLista.getCargo())) {
                spCargo.setSelection(i);
                break;
            }
        }
        /////////////////////////////////////////////////////////////////////////////////
        btnRegistrar.setText("Editar");
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modoEditar();
            }
        });
    }

    private void EnviarResultadoALista() {
        Intent resultadoIntent = new Intent();
        setResult(Activity.RESULT_OK, resultadoIntent);
        finish();
    }

    public void Cancelar(View vista) {
        finish();
    }

}