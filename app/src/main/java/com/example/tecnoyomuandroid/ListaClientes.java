package com.example.tecnoyomuandroid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Empleado;

import java.util.List;

public class ListaClientes extends AppCompatActivity {

    private LinearLayout contLista;
    //Variable para saber si se abre la ventana desde el registro de equipos

    private boolean registrandoEquipo;
    private List<Cliente> listaClientes;
    private ActivityResultLauncher resultadoActividad;
    private boolean esAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Configuramos el objeto que va a recibir el resultado de la actividad de edicion para poder cerrar la lista ///
        resultadoActividad = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        recreate();
                    }
                    if(result.getResultCode() == 123){
                        finish();
                    }
                });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        registrandoEquipo = this.getIntent().hasExtra("registrandoEquipo");
        esAdmin = this.getIntent().hasExtra("esAdmin");

        contLista = (LinearLayout) findViewById(R.id.contListaClientes);
        contLista.setBackgroundColor(ContextCompat.getColor(this, R.color.white));


        //Llenamos la lista de clientes al abrir el activity
        if(!ListaClientesSQLite().isEmpty()){
            listaClientes = ListaClientesSQLite();
            for(Cliente cliente : listaClientes){
                Button boton = new Button(this);
                boton.setText(cliente.getNombre());
                boton.setTextSize(20);
                boton.setBackgroundColor(ContextCompat.getColor(this, R.color.azulito));
                boton.setTextColor(Color.WHITE);
                //Agregar el listener al button
                boton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ManejarOnClick(cliente);
                    }
                });
                //Agregar linea
                View linea = new View(this);
                linea.setBackgroundColor(Color.BLACK);
                linea.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 4));
                contLista.addView(boton);
                contLista.addView(linea);
            }
        }else {
            Toast.makeText(this, "Lista de clientes vacía o error", Toast.LENGTH_SHORT).show();
        }
    }

    private void ManejarOnClick(Cliente cliente){
        //Dependiendo de  si se está registrando un equipo o simplemente consultando el cliente
        if(registrandoEquipo){
            AbrirRegistroEquipo(cliente);
        }else{
            AbrirDetalles(cliente);
        }
    }

    private void AbrirRegistroEquipo(Cliente cliente){
        Intent intent = new Intent(this, RegistrarEquipo.class);
        intent.putExtra("idCliente", cliente.getId());
        intent.putExtra("nombreCliente", cliente.getNombre());
        resultadoActividad.launch(intent);
    }

    private List<Cliente> ListaClientesSQLite(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarClientesSQLite();
    }

    private void AbrirDetalles(Cliente cliente){
        Intent intent = new Intent(this, RegistrarCliente.class);
        intent.putExtra("vieneDeLista", cliente);
        if(esAdmin) intent.putExtra("esAdmin", true);
        resultadoActividad.launch(intent);
    }

    public void Cancelar(View vista){
        finish();
    }
}