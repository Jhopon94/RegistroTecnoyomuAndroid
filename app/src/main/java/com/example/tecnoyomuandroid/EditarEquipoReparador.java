package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Equipo;

public class EditarEquipoReparador extends AppCompatActivity {

    private TextView etModelo;
    private TextView etServicio;
    private TextView etNombreCliente;
    private TextView etEstado;
    private Cliente cliente;
    private Button btnMarcarReparado;
    private Equipo equipoRecibido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_equipo_reparador);

        etNombreCliente = (TextView) findViewById(R.id.etNombreClienteEquipoReparador);
        etModelo = (TextView) findViewById(R.id.etModeloEquipoReparador);
        etServicio = (TextView) findViewById(R.id.etServicioEquipoReparador);
        etEstado = (TextView) findViewById(R.id.etEstadoEquipoReparador);
        btnMarcarReparado = (Button) findViewById(R.id.btnMarcarReparado);
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

    public void MarcarReparado(View vista){
        new EdicionSQLite(this).MarcarReparado(equipoRecibido.getId());
        FinalizarConResultado();
    }

    private void AcomodarDetalles() {
        String estadoEquipo = equipoRecibido.getEstado();
        int saldoPendiente = equipoRecibido.getSaldoPendiente();
        etNombreCliente.setText(cliente.getNombre());
        etModelo.setText("Modelo: " + equipoRecibido.getModelo());
        etServicio.setText("Servicio: " + equipoRecibido.getServicio());
        etEstado.setText("Estado: " + estadoEquipo);
        if(estadoEquipo.equals("ingresado")){
            etEstado.setTextColor(Color.RED);
            etEstado.setTypeface(null, Typeface.BOLD);
        }
    }

    private Cliente ObtenerCliente(int id) {
        return new ConsultaIndividualSQLite(this).ConsultarCliente(id);
    }

    private void FinalizarConResultado(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void Cancelar(View vista) {
        finish();
    }
}