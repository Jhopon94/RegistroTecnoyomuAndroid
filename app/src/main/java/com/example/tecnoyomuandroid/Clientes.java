package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Clientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
    }

    public void AbrirRegCliente(View vista){
        Intent intent = new Intent(this, RegistrarCliente.class);
        startActivity(intent);
    }

    public void CerrarSesion(View vista){
        finish();
    }
}