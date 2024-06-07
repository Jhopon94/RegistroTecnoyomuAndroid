package com.example.tecnoyomuandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button btnRegistrar;
    private Button btnEliminarUsuario;
    private int rojitoOscuro;
    private List<Empleado> listaEmpleadosParaUsuario;
    private Empleado empleadoDeusuario;

    private boolean vieneDeLista;
    private Usuario usuarioDesdeLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        spEmpleados = (Spinner) findViewById(R.id.spinnerEmpleado);
        cajitaNombreusuario = (EditText) findViewById(R.id.cajitaNombreUsuario);
        cajitaClave = (EditText) findViewById(R.id.cajitaClaveUsuario);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarCliente);
        btnEliminarUsuario = (Button) findViewById(R.id.btnEliminarUsuario);
        rojitoOscuro = btnEliminarUsuario.getSolidColor();
        btnEliminarUsuario.setVisibility(View.GONE);

        listaEmpleadosParaUsuario = new ArrayList<>(); //Para poder usar el objeto elegido segun nombre en el spinner
        usuarioDesdeLista = new Usuario();

        vieneDeLista = this.getIntent().hasExtra("vieneDeLista");
        if(vieneDeLista){
            usuarioDesdeLista = (Usuario) this.getIntent().getSerializableExtra("vieneDeLista");
            if(ObtenerEmpleado(usuarioDesdeLista.getIdEmpleado()) != null){
                empleadoDeusuario = ObtenerEmpleado(usuarioDesdeLista.getIdEmpleado());
                ModoDetalles();
            }else{
                Toast.makeText(this, "Empleado no encontrado para este usuario", Toast.LENGTH_SHORT).show();
                FinalizarConResultado();
            }
        }else{
            //Obtener lista de empleados disponibles para usuario
            if(!ListaEmpleadosDisponibles().isEmpty()){
                listaEmpleadosParaUsuario = ListaEmpleadosDisponibles();
                //No tengo idea pa que, pero chatgpt me dijo que lo hiciera para agregarlos al spinner
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
                FinalizarConResultado();
            }
        }

    }

    private List<Empleado> ListaEmpleadosDisponibles(){
        ConsultaSQLite consulta = new ConsultaSQLite(this);
        return consulta.ConsultarEmpleadosParaUsuarioSQLite();
    }

    private Empleado ObtenerEmpleado(int id){
        ConsultaIndividualSQLite consulta = new ConsultaIndividualSQLite(this);
        return consulta.ConsultarEmpleado(id);
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
    }

    private void ModoDetalles(){
        spEmpleados.setEnabled(false);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEmpleados.setAdapter(adaptador);
        adaptador.add(empleadoDeusuario.getNombre());
        adaptador.notifyDataSetChanged();

        cajitaNombreusuario.setEnabled(false);
        cajitaNombreusuario.setBackgroundColor(Color.LTGRAY);
        cajitaNombreusuario.setText(usuarioDesdeLista.getNombreUsuario());
        cajitaClave.setEnabled(false);
        cajitaClave.setBackgroundColor(Color.LTGRAY);
        cajitaClave.setText(usuarioDesdeLista.getClave());
        btnRegistrar.setText("Editar");
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModoEdicion();
            }
        });
        btnEliminarUsuario.setVisibility(View.VISIBLE);
        btnEliminarUsuario.setEnabled(false);
        btnEliminarUsuario.setBackgroundColor(Color.LTGRAY);
    }

    private void ModoEdicion(){
        cajitaNombreusuario.setEnabled(true);
        cajitaNombreusuario.setBackgroundColor(Color.WHITE);
        cajitaClave.setEnabled(true);
        cajitaClave.setBackgroundColor(Color.WHITE);
        btnRegistrar.setText("Registrar");
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        });
        btnEliminarUsuario.setEnabled(true);
        btnEliminarUsuario.setBackgroundColor(rojitoOscuro);
    }

    private void Editar(){
        int idEmpleado = usuarioDesdeLista.getIdEmpleado();
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
                usuario.setClave(clave);
                /////////////////////////////////
                EdicionSQLite edicion = new EdicionSQLite(this);
                edicion.EditarUsuarioSQLite(usuario);
                FinalizarConResultado();
            }
        }
    }

    public void Cancelar(View vista){
        finish();
    }

    private void FinalizarConResultado(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void EliminarUsuario(View vista){
        new EliminacionSQLite(this).EliminarUsuario(usuarioDesdeLista.getIdEmpleado());
        FinalizarConResultado();
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