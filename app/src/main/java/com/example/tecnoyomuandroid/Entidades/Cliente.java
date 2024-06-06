package com.example.tecnoyomuandroid.Entidades;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getServiciosTomados() {
        return serviciosTomados;
    }

    public void setServiciosTomados(int serviciosTomados) {
        this.serviciosTomados = serviciosTomados;
    }

    private String celular;
    private String direccion;
    private String correo;
    private int serviciosTomados;
}
