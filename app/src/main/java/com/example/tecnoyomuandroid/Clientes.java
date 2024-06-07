package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Clientes extends AppCompatActivity {

    private boolean esAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        esAdmin = this.getIntent().hasExtra("esAdmin");
    }

    public void AbrirRegCliente(View vista){
        Intent intent = new Intent(this, RegistrarCliente.class);
        startActivity(intent);
    }

    public void AbrirListaClientes(View vista){
        Intent intent = new Intent(this, ListaClientes.class);
        if(esAdmin)intent.putExtra("esAdmin", true);
        startActivity(intent);
    }
    public void CerrarSesion(View vista){
        finish();
    }
}