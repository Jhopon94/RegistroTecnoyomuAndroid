package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Administrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }

    public void CerrarSesion(View vista){
        finish();
    }

    public void AbrirContabilidad(View vista){
        Intent intent = new Intent(this, Contabilidad.class);
        intent.putExtra("admon", true);
        startActivity(intent);
    }

    public void AbrirEmpleados(View vista){
        Intent intent = new Intent(this, Empleados.class);
        startActivity(intent);
    }

}