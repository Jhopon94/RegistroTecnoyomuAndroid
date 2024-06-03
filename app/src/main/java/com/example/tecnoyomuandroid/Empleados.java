package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Empleados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
    }

    public void AbrirRegEmpleado(View vista){
        Intent intent = new Intent(this, RegistrarEmpleado.class);
        startActivity(intent);
    }

    public void AbrirListaEmpleados(View vista){
        Intent intent = new Intent(this, ListaEmpleados.class);
        startActivity(intent);
    }

    public void CerrarSesion(View vista){
        finish();
    }
}