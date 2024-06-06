package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Equipo;

public class DetallesEquipoEntregado extends AppCompatActivity {

    private TextView etModelo;
    private TextView etServicio;
    private TextView etPrecio;
    private TextView etNombreCliente;
    private Cliente cliente;
    private Equipo equipoRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_equipo_entregado);

        etNombreCliente = (TextView) findViewById(R.id.etNombreClienteEquipoEntreg);
        etModelo = (TextView) findViewById(R.id.etModeloEquipoEntreg);
        etServicio = (TextView) findViewById(R.id.etServicioEquipoEntreg);
        etPrecio = (TextView) findViewById(R.id.etPrecioEquipoEntreg);
        equipoRecibido = (Equipo) this.getIntent().getSerializableExtra("equipo");
        if (equipoRecibido != null) {
            cliente = ObtenerCliente(equipoRecibido.getIdCliente());
            if (cliente != null) {
                AcomodarDetalles();
            } else {
                Toast.makeText(this, "No se encontró el cliente de este equipo", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "No se recibió ningún equipo", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private void AcomodarDetalles() {
        etNombreCliente.setText(cliente.getNombre());
        etModelo.setText("Modelo: " + equipoRecibido.getModelo());
        etServicio.setText("Servicio: " + equipoRecibido.getServicio());
        etPrecio.setText("Precio: $ " + String.valueOf(equipoRecibido.getPrecio()));

    }

    private Cliente ObtenerCliente(int id) {
        return new ConsultaIndividualSQLite(this).ConsultarCliente(id);
    }

    public void Cancelar(View vista){
        finish();
    }
}