package com.example.tecnoyomuandroid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Equipo;
import com.example.tecnoyomuandroid.Entidades.Equipo;

import java.util.List;

public class ListaEquiposIngresados extends AppCompatActivity {

    private LinearLayout contLista;
    private List<Equipo> listaEquipos;
    private ActivityResultLauncher resultadoActividad;
    private boolean vieneDeReparacion;
    private boolean listaVaciaAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipos_ingresados);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Configuramos el objeto que va a recibir el resultado de la actividad de edicion para poder cerrar la lista ///
        resultadoActividad = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        recreate();
                    }
                });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        contLista = (LinearLayout) findViewById(R.id.contListaEqIng);
        contLista.setBackgroundColor(Color.WHITE);
        vieneDeReparacion = this.getIntent().hasExtra("reparacion");

        if (vieneDeReparacion) {        //Llenamos la lista de equipos al abrir el activity
            if (!ListaEquiposPorRepararSQLite().isEmpty()) {
                listaEquipos = ListaEquiposPorRepararSQLite();
                ListaEquipos(listaEquipos);
            } else {
                Toast.makeText(this, "Lista de equipos vacía o error", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Llenamos la lista de equipos al abrir el activity
            if (!ListaEquIngSQLite().isEmpty()) {
                listaEquipos = ListaEquIngSQLite();
                ListaEquipos(listaEquipos);
            } else {
                Toast.makeText(this, "Lista de equipos vacía o error", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void ListaEquipos(List<Equipo> lista){
        for (Equipo equipo : lista) {
            Button boton = new Button(this);
            boton.setText(equipo.getModelo());
            boton.setTextSize(20);
            boton.setBackgroundColor(ContextCompat.getColor(this, R.color.azulito));
            boton.setTextColor(Color.WHITE);
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AbrirDetalles(equipo);
                }
            });
            //Agregar linea
            View linea = new View(this);
            linea.setBackgroundColor(Color.BLACK);
            linea.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 4));
            contLista.addView(boton);
            contLista.addView(linea);
        }
    }

    private List<Equipo> ListaEquIngSQLite() {
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarEquiposIngresadosSQLite();
    }

    private List<Equipo> ListaEquiposPorRepararSQLite() {
        return new ConsultaSQLite(this).ConsultarEquiposPorRepararSQLite();
    }

    private void AbrirDetalles(Equipo equipo) {
        if(vieneDeReparacion){
            Intent intent = new Intent(this, EditarEquipoReparador.class);
            intent.putExtra("equipo", equipo);
            resultadoActividad.launch(intent);
        }else{
            Intent intent = new Intent(this, EditarEquipoIngresado.class);
            intent.putExtra("equipo", equipo);
            resultadoActividad.launch(intent);
        }
    }

    public void Cancelar(View vista) {
        finish();
    }
}