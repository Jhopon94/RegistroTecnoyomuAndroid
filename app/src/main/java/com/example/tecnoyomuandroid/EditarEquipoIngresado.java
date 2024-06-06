package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Cliente;
import com.example.tecnoyomuandroid.Entidades.Equipo;

public class EditarEquipoIngresado extends AppCompatActivity {

    private TextView etModelo;
    private TextView etServicio;
    private TextView etPrecio;
    private TextView etSaldoPendiente;
    private TextView etNombreCliente;
    private Cliente cliente;
    private Button btnAbonar;
    private Button btnMarcarEntregado;
    private Equipo equipoRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_equipo_ingresado);

        etNombreCliente = (TextView) findViewById(R.id.etNombreClienteEquipoEntreg);
        etModelo = (TextView) findViewById(R.id.etModeloEquipoEntreg);
        etServicio = (TextView) findViewById(R.id.etServicioEquipoEntreg);
        etPrecio = (TextView) findViewById(R.id.etPrecioEquipoEntreg);
        etSaldoPendiente = (TextView) findViewById(R.id.etSaldoPendienteEquipoEdit);
        btnAbonar = (Button) findViewById(R.id.btnAbonar);
        btnMarcarEntregado = (Button) findViewById(R.id.btnMarcarEntregado);
        equipoRecibido = (Equipo) this.getIntent().getSerializableExtra("equipo");
        if (equipoRecibido != null) {
            if (equipoRecibido.getSaldoPendiente() == 0) {
                btnAbonar.setEnabled(false);
                btnAbonar.setBackgroundColor(Color.LTGRAY);
            }else{
                btnMarcarEntregado.setEnabled(false);
                btnMarcarEntregado.setBackgroundColor(Color.LTGRAY);
            }
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
        etSaldoPendiente.setText("Debe: $ " + String.valueOf(equipoRecibido.getSaldoPendiente()));

    }

    private Cliente ObtenerCliente(int id) {
        return new ConsultaIndividualSQLite(this).ConsultarCliente(id);
    }

    public void MarcarEntregado(View vista){
        new EdicionSQLite(this).MarcarComoEntregado(equipoRecibido.getId());
        FinalizarConResultado();
    }

    public void Abonar(int abono, int idEquipo){
        int saldoNuevo = equipoRecibido.getSaldoPendiente() - abono;
        new EdicionSQLite(this).AjustarSaldoEquipo(saldoNuevo, idEquipo);
        FinalizarConResultado();
    }

    private void FinalizarConResultado(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void MostrarDialogoAbono(View vista){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Abono por: ");

        // Configurar el EditText para ingresar solo números
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Configurar botones del diálogo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputText = input.getText().toString();
                if (!inputText.isEmpty()) {
                    int number = Integer.parseInt(inputText);
                    // si el abono es mayor lo que se debe
                    if(number > equipoRecibido.getSaldoPendiente()){
                        Toast.makeText(EditarEquipoIngresado.this, "Error, el cliente debe menos dinero!", Toast.LENGTH_SHORT).show();
                    }else{
                        Abonar(number, equipoRecibido.getId());
                    }
                } else {
                    Toast.makeText(EditarEquipoIngresado.this, "No se ingresó ningún número", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void Cancelar(View vista) {
        finish();
    }
}