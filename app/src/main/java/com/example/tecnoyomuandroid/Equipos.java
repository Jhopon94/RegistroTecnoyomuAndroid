package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Equipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);
    }

    public void AbrirListaClientesEquipos(View vista){
        Intent intent = new Intent(this, ListaClientes.class);
        intent.putExtra("registrandoEquipo", true);
        startActivity(intent);
    }

    public void AbrirListaEquiposIng(View vista){
        Intent intent = new Intent(this, ListaEquiposIngresados.class);
        startActivity(intent);
    }

    public void AbrirListaEquiposEntregados(View vista){
        Intent intent = new Intent(this, ListaEquiposEntregados.class);
        startActivity(intent);
    }
    public void CerrarSesion(View vista){
        finish();
    }
}