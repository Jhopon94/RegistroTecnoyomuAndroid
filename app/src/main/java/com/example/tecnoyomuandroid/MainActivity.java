package com.example.tecnoyomuandroid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cajitaNombreUsuario;
    private EditText cajitaClave;
    private ActivityResultLauncher resultadoActividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajitaNombreUsuario = (EditText) findViewById(R.id.cajitaUsuarioLogin);
        cajitaClave = (EditText) findViewById(R.id.cajitaClaveLogin);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Configuramos el objeto que va a recibir el resultado de la actividad de edicion para poder cerrar la lista ///
        resultadoActividad = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        cajitaNombreUsuario.setText("");
                        cajitaClave.setText("");
                    }
                });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void QueAbrir(View vista){
        String usuario = cajitaNombreUsuario.getText().toString().trim();
        String clave = cajitaClave.getText().toString().trim();
        if(usuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Debes llenar los campos!", Toast.LENGTH_SHORT).show();
        }else{
            String rol = new ConsultaIndividualSQLite(this).MetodoLogin(usuario, clave);
            if(rol != null){
                switch (rol){
                    case "Administrador":
                        AbrirAdmon();
                        break;
                    case "Servicio al Cliente":
                        AbrirServCliente();
                        break;
                    case "Contador":
                        AbrirContabilidad();
                        break;
                    case "Reparador":
                        AbrirListaEqPorReparar();
                        break;
                    default:
                        Toast.makeText(this, "Información Incorrecta", Toast.LENGTH_SHORT).show();
                        break;
                }
            }else{
                Toast.makeText(this, "Información Incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void AbrirAdmon(){
        Intent actividad = new Intent(this, Administrador.class);
        resultadoActividad.launch(actividad);
    }

    private void AbrirServCliente(){
        Intent actividad = new Intent(this, ServicioCliente.class);
        resultadoActividad.launch(actividad);
    }

    private void AbrirContabilidad(){
        Intent actividad = new Intent(this, Contabilidad.class);
        resultadoActividad.launch(actividad);
    }

    private void AbrirListaEqPorReparar(){
        Intent actividad = new Intent(this, ListaEquiposIngresados.class);
        actividad.putExtra("reparacion", true);
        resultadoActividad.launch(actividad);
    }
}