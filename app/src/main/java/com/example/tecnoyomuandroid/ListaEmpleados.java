package com.example.tecnoyomuandroid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;

import java.util.List;

public class ListaEmpleados extends AppCompatActivity {

    private LinearLayout contLista;
    private ActivityResultLauncher<Intent> resultadoActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Configuramos el objeto que va a recibir el resultado de la actividad de edicion para poder cerrar la lista ///
        resultadoActividad = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        recreate();
                    }
                });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        contLista = (LinearLayout) findViewById(R.id.contListaEmpl);
        contLista.setBackgroundColor(ContextCompat.getColor(this, R.color.white));


        //Llenamos la lista de empleados al abrir el activity
        if(!ListaEmpleadosSQLite().isEmpty()){
            List<Empleado> listaEmpleados = ListaEmpleadosSQLite();
            for(Empleado empleado : listaEmpleados){
                //Para excluir el administrador por defecto y no perder acceso a la app
                if(empleado.getId() != 123456789){
                    Button boton = new Button(this);
                    boton.setText(empleado.getNombre());
                    boton.setTextSize(20);
                    boton.setBackgroundColor(ContextCompat.getColor(this, R.color.azulito));
                    boton.setTextColor(Color.WHITE);
                    boton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AbrirDetalles(empleado);
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
        }
    }

    private List<Empleado> ListaEmpleadosSQLite(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarEmpleadosSQLite();
    }

    private void AbrirDetalles(Empleado empleado){
        Intent intent = new Intent(this, RegistrarEmpleado.class);
        intent.putExtra("vieneDeLista", empleado);
        resultadoActividad.launch(intent);
    }

    public void Cancelar(View vista){
        finish();
    }
}