package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void QueAbrir(View vista){
        TextView cajitaUsuario = (TextView) findViewById(R.id.cajitaUsuario);
        String usuario = cajitaUsuario.getText().toString().trim();
        switch (usuario){
            case "administrador":
                AbrirAdmon();
                break;
            case "servicioCliente":
                AbrirServCliente();
                break;
            case "contador":
                AbrirContabilidad();
                break;
            default:
                Toast.makeText(this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void AbrirAdmon(){
        Intent actividad = new Intent(this, Administrador.class);
        startActivity(actividad);
    }

    private void AbrirServCliente(){
        Intent actividad = new Intent(this, ServicioCliente.class);
        startActivity(actividad);
    }

    private void AbrirContabilidad(){
        Intent actividad = new Intent(this, Contabilidad.class);
        startActivity(actividad);
    }
}