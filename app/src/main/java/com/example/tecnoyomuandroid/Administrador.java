package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
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
    public void AbrirUsuarios(View vista){
        Intent intent = new Intent(this, Usuarios.class);
        startActivity(intent);
    }

    public void AbrirClientes(View vista){
        Intent intent = new Intent(this, Clientes.class);
        intent.putExtra("esAdmin", true);
        startActivity(intent);
    }

    public void AbrirEquipos(View vista){
        Intent intent = new Intent(this, Equipos.class);
        intent.putExtra("esAdmin", true);
        startActivity(intent);
    }

}