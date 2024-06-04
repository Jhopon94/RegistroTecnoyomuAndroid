package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        contLista = (LinearLayout) findViewById(R.id.contListaClientes);
        contLista.setBackgroundColor(ContextCompat.getColor(this, R.color.white));


        //Llenamos la lista de clientes al abrir el activity
        if(!ListaClientesSQLite().isEmpty()){
            List<Cliente> listaClientes = ListaClientesSQLite();
            for(Cliente cliente : listaClientes){
                Button boton = new Button(this);
                boton.setText(cliente.getNombre());
                boton.setTextSize(20);
                boton.setBackgroundColor(ContextCompat.getColor(this, R.color.azulito));
                boton.setTextColor(Color.WHITE);
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

    private List<Cliente> ListaClientesSQLite(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarClientesSQLite();
    }

    public void Cancelar(View vista){
        finish();
    }
}