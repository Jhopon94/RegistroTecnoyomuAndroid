package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Contabilidad extends AppCompatActivity {

    boolean vieneDesdeAdmon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contabilidad);


        //Verifica si viene desde admon
        vieneDesdeAdmon = this.getIntent().hasExtra("admon");

        if(vieneDesdeAdmon) CambiarTextoSesion();
    }

    private void CambiarTextoSesion(){
        Button btnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesionContabilidad);
        btnCerrarSesion.setText("<-- Volver");
    }

    public void CerrarSesion(View vista){
        finish();
    }
}