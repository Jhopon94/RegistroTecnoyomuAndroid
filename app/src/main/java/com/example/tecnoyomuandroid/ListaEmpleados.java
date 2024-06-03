package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;

import java.util.List;

public class ListaEmpleados extends AppCompatActivity {

    private LinearLayout contLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        contLista = (LinearLayout) findViewById(R.id.contListaEmpl);
        contLista.setBackgroundColor(ContextCompat.getColor(this, R.color.white));

        //Estilo de borde para los botones de la lista
        GradientDrawable borde = new GradientDrawable();
        borde.setColor(Color.TRANSPARENT);
        borde.setStroke(2, Color.BLACK);

        //Llenamos la lista de empleados al abrir el activity
        if(!ListaEmpleadosSQLite().isEmpty()){
            List<Empleado> listaEmpleados = ListaEmpleadosSQLite();
            for(Empleado empleado : listaEmpleados){
                Button boton = new Button(this);
                boton.setText(empleado.getNombre());
                boton.setTextSize(20);
                boton.setBackgroundColor(ContextCompat.getColor(this, R.color.azulito));
                boton.setTextColor(Color.WHITE);
                //Agregar linea
                View linea = new View(this);
                linea.setBackgroundColor(Color.BLACK);
                linea.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 4));
                contLista.addView(boton);
                contLista.addView(linea);
            }
        }else {
            Toast.makeText(this, "Lista de empleados vacía o error", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Empleado> ListaEmpleadosSQLite(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarEmpleadosSQLite();
    }

    public void Cancelar(View vista){
        finish();
    }
}