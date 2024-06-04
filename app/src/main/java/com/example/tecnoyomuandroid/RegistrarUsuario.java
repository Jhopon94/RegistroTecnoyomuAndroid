package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tecnoyomuandroid.Entidades.Empleado;
import com.example.tecnoyomuandroid.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RegistrarUsuario extends AppCompatActivity {

    private Spinner spEmpleados;
    private EditText cajitaNombreusuario;
    private EditText cajitaClave;
    List<Empleado> listaEmpleadosParaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        spEmpleados = (Spinner) findViewById(R.id.spinnerEmpleado);
        cajitaNombreusuario = (EditText) findViewById(R.id.cajitaNombreUsuario);
        cajitaClave = (EditText) findViewById(R.id.cajitaClaveUsuario);
        listaEmpleadosParaUsuario = new ArrayList<>(); //Para poder usar el objeto elegido segun nombre en el spinner

        //Obtener lista de empleados disponibles para usuario
        if(!ListaEmpleados().isEmpty()){
            listaEmpleadosParaUsuario = ListaEmpleados();
            //No tengo idea pa que, pero chatgpt me dijo que lo hiciera
            ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spEmpleados.setAdapter(adaptador);
            //Comenzar a agregar elementos al spinner
            for (Empleado empleado : listaEmpleadosParaUsuario){
                adaptador.add(empleado.getNombre());
            }
            //Acomodar el adaptador porque cambiaron sus valores
            adaptador.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "Sin empleados disponibles para usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Empleado> ListaEmpleados(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarEmpleadosParaUsuarioSQLite();
    }

    public void Registrar(View vista){
        String nombreSeleccionado = spEmpleados.getSelectedItem().toString();
        String rol = "";
        //Obtener id y rol del nombre seleccionado
        int idEmpleado = 0;
            for(Empleado empleado : listaEmpleadosParaUsuario){
                if(empleado.getNombre().equals(nombreSeleccionado)){
                    idEmpleado = empleado.getId();
                    rol = empleado.getCargo();
                    break;
                }
            }
            String nombreUsuario = cajitaNombreusuario.getText().toString();
            String clave = cajitaClave.getText().toString();

        if(nombreUsuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Deben llenarse todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(ContieneEspacios(nombreUsuario) || ContieneEspacios(clave)){
                Toast.makeText(this, "no debe haber espacios en contraseña o nombre de usuario", Toast.LENGTH_LONG).show();
            }else{
                //Procedemos a registrar una vez pasadas las verificaciones
                Usuario usuario = new Usuario();
                usuario.setIdEmpleado(idEmpleado);
                usuario.setNombreUsuario(nombreUsuario);
                usuario.setRol(rol);
                usuario.setClave(clave);
                /////////////////////////////////
                RegistroSQLite registro = new RegistroSQLite(this);
                registro.RegistrarUsuarioSQLite(usuario);
                finish();
            }
        }
        //Acomodar no mas disponible apra usuario al empleado que se registre el usuario
    }

    public void Cancelar(View vista){
        finish();
    }

    private boolean ContieneEspacios(String texto){
        boolean bandera = false;
        String[] listaCaracteres = texto.split("");
        for(String caracter : listaCaracteres){
            if(caracter.equals(" ")){
                bandera = true;
                break;
            }
        }
        return bandera;
    }
}