package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;

public class RegistrarEmpleado extends AppCompatActivity {

    EditText cajitaNombre;
    EditText cajitaCedula;
    Spinner spCargo;
    EditText cajitaCelular;
    EditText cajitaDireccion;
    EditText cajitaCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_empleado);

        cajitaNombre = (EditText) findViewById(R.id.cajitaNombre);
        cajitaCedula = (EditText) findViewById(R.id.cajitaCedula);
        spCargo = (Spinner) findViewById(R.id.spinnerCargo);
        cajitaCelular = (EditText) findViewById(R.id.cajitaCelular);
        cajitaDireccion =  (EditText) findViewById(R.id.cajitaDireccion);
        cajitaCorreo = (EditText) findViewById(R.id.cajitaCorreo);
    }

    public void Registrar(View vista){
        //Capturamos los textos de las cajitas
        String nombre = cajitaNombre.getText().toString();
        String cedula = cajitaCedula.getText().toString(); //Convertir a numero mas adelante
        String cargo = spCargo.getSelectedItem().toString();
        String celular = cajitaCelular.getText().toString();
        String direccion = cajitaDireccion.getText().toString();
        String correo = cajitaCorreo.getText().toString();

        if(nombre.isEmpty() || cedula.isEmpty() || cargo.isEmpty() || celular.isEmpty() || direccion.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Todos los campos deben estar llenos!", Toast.LENGTH_SHORT).show();
        }else{
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

    public void Cancelar(View vista){
        finish();
    }

}