package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        btnCerrarSesion.setText("Volver");
    }

    public void AbrirDatosContables(View vista){
        Intent intent = new Intent(this, DatosContables.class);
        startActivity(intent);
    }

    public void AbrirInventario(View vista){
        Intent intent = new Intent(this, Inventario.class);
        startActivity(intent);
    }

    public void CerrarSesion(View vista){
        if(!vieneDesdeAdmon){
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            finish();
        }
    }
}