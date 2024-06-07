package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tecnoyomuandroid.Entidades.Equipo;

import java.util.List;

public class DatosContables extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contables);
    }

    public void CerrarSesion(View vista){
        finish();
    }


    public void MostrarDialogoAbono(View vista){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("El total de ingresos histórico es: ");

        // Configurar el EditText para ingresar solo números
        final EditText etIngresos = new EditText(this);
        etIngresos.setInputType(InputType.TYPE_CLASS_TEXT);
        int ingresos = CalcularIngresos();
        etIngresos.setText("$ " + ingresos);
        etIngresos.setEnabled(false);
        builder.setView(etIngresos);

        // Configurar botones del diálogo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private int CalcularIngresos(){
        ConsultaSQLite consultaEqEntreg = new ConsultaSQLite(this);
        List<Equipo> listaEquipos = consultaEqEntreg.ConsultarEquiposEntregadosSQLite();
        int sumatoria = 0;
        if(!listaEquipos.isEmpty()){
            for(Equipo equipo : listaEquipos){
                sumatoria += equipo.getPrecio();
            }
        }
        return sumatoria;
    }
}