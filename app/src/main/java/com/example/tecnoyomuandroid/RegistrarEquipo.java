package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Equipo;

public class RegistrarEquipo extends AppCompatActivity {

    private EditText cajitaModelo;
    private EditText cajitaServicio;
    private EditText cajitaPrecio;
    private EditText cajitaAbono;
    private EditText[] listaCajitas;
    private TextView etNombreCliente;
    private int idCliente;
    private String nombreCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_equipo);

        cajitaModelo = (EditText) findViewById(R.id.cajitaModeloEquipoReg);
        cajitaServicio = (EditText) findViewById(R.id.cajitaServicioEquipoReg);
        cajitaPrecio = (EditText) findViewById(R.id.cajitaPrecioEquipoReg);
        cajitaAbono = (EditText) findViewById(R.id.cajitaAbonoEquipoReg);
        etNombreCliente = (TextView)findViewById(R.id.etNombreClienteRegEquipo);
        listaCajitas = new EditText[]{cajitaAbono, cajitaPrecio, cajitaModelo, cajitaServicio};

        //Obtener nombre y id de cliente, y si no hay, cerrar el activity
        if (this.getIntent().hasExtra("nombreCliente") && this.getIntent().hasExtra("idCliente")) {
            idCliente = this.getIntent().getIntExtra("idCliente", 0);
            etNombreCliente.setText(this.getIntent().getStringExtra("nombreCliente"));
            etNombreCliente.setTextColor(Color.BLACK);
        } else {
            idCliente = 0;
            nombreCliente = "";
            Toast.makeText(this, "No hay información del cliente!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void Registrar(View vista) {
        if (!camposVacios(listaCajitas)) {
            String modelo = cajitaModelo.getText().toString();
            String servicio = cajitaServicio.getText().toString();
            int precio = Integer.parseInt(cajitaPrecio.getText().toString());
            int saldoPendiente = precio - Integer.parseInt(cajitaAbono.getText().toString());
            //Acomodar el objeto Equipo
            Equipo equipo = new Equipo();
            equipo.setIdCliente(idCliente);
            equipo.setModelo(modelo);
            equipo.setServicio(servicio);
            equipo.setPrecio(precio);
            equipo.setSaldoPendiente(saldoPendiente);
            //Proceder a registrar
            RegistroSQLite registrar = new RegistroSQLite(this);
            registrar.RegistrarEquipoSQLite(equipo);
            finish();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos, si no hay abono, poner cero!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean camposVacios(EditText[] listaCajitas) {
        boolean bandera = false;
        for (EditText cajita : listaCajitas) {
            if (cajita.getText().toString().isEmpty()) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    public void Cancelar(View vista) {
        finish();
    }
}