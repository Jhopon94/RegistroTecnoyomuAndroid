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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;
import com.example.tecnoyomuandroid.Entidades.Usuario;

import java.util.List;

public class ListaUsuarios extends AppCompatActivity {

    private LinearLayout contLista;

    private ActivityResultLauncher<Intent> resultadoActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        contLista = (LinearLayout) findViewById(R.id.contListaUsuarios);
        contLista.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Configuramos el objeto que va a recibir el resultado de la actividad de edicion para poder cerrar la lista ///
        resultadoActividad = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        recreate();
                    }
                });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Llenamos la lista de usuarios al abrir el activity
        if(!ListaUsuariosSQLite().isEmpty()){
            List<Usuario> listaUsuarios = ListaUsuariosSQLite();
            for(Usuario usuario : listaUsuarios){
                Button boton = new Button(this);
                boton.setText(usuario.getNombreUsuario());
                boton.setTextSize(20);
                boton.setBackgroundColor(ContextCompat.getColor(this, R.color.azulito));
                boton.setTextColor(Color.WHITE);
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AbrirDetalles(usuario);
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

    private List<Usuario> ListaUsuariosSQLite(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarUsuariosSQLite();
    }

    private void AbrirDetalles(Usuario usuario){
        Intent intent = new Intent(this, RegistrarUsuario.class);
        intent.putExtra("vieneDeLista", usuario);
        resultadoActividad.launch(intent);
    }

    public void Cancelar(View vista){
        finish();
    }
}