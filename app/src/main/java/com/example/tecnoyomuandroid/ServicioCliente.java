package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServicioCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_cliente);
    }

    public void AbrirClientes(View vista){
        Intent intent = new Intent(this, Clientes.class);
        startActivity(intent);
    }

    public void AbrirEquipos(View vista){
        Intent intent = new Intent(this, Equipos.class);
        startActivity(intent);
    }

    public void CerrarSesion(View vista){
        finish();
    }
}