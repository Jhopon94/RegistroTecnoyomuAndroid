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
import com.example.tecnoyomuandroid.Entidades.Usuario;

import java.util.List;

public class ListaUsuarios extends AppCompatActivity {

    private LinearLayout contLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        contLista = (LinearLayout) findViewById(R.id.contListaUsuarios);
        contLista.setBackgroundColor(ContextCompat.getColor(this, R.color.white));

        //Llenamos la lista de usuarios al abrir el activity
        if(!ListaUsuariosSQLite().isEmpty()){
            List<Usuario> listaUsuarios = ListaUsuariosSQLite();
            for(Usuario usuario : listaUsuarios){
                Button boton = new Button(this);
                boton.setText(usuario.getNombreUsuario());
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
            Toast.makeText(this, "Lista de usuarios vacía o error", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Usuario> ListaUsuariosSQLite(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarUsuariosSQLite();
    }

    public void Cancelar(View vista){
        finish();
    }
}