package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
    }

    public void AbrirRegUsuario(View vista){
        Intent intent = new Intent(this, RegistrarUsuario.class);
        startActivity(intent);
    }

    public void AbrirListaUsuarios(View vista){
        Intent intent = new Intent(this, ListaUsuarios.class);
        startActivity(intent);
    }

    public void CerrarSesion(View vista){
        finish();
    }
}