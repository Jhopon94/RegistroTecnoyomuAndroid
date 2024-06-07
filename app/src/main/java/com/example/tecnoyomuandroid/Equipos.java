package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Equipos extends AppCompatActivity {

    private boolean esAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        esAdmin = this.getIntent().hasExtra("esAdmin");
    }

    public void AbrirListaClientesEquipos(View vista){
        Intent intent = new Intent(this, ListaClientes.class);
        intent.putExtra("registrandoEquipo", true);
        startActivity(intent);
    }

    public void AbrirListaEquiposIng(View vista){
        Intent intent = new Intent(this, ListaEquiposIngresados.class);
        if(esAdmin)intent.putExtra("esAdmin", true);
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