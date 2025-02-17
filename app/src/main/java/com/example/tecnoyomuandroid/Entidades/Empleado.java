package com.example.tecnoyomuandroid.Entidades;

import java.io.Serializable;

public class Empleado implements Serializable {

    private int id;
    private String nombre;
    private String cargo;
    private String celular;
    private String correo;
    private String direccion;
    private boolean disponibleParaUsuario;
    private boolean activo;
    private String fechaRegistro;

    public String getFechaRegistro() {
        return fechaRegistro;
    }

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isDisponibleParaUsuario() {
        return disponibleParaUsuario;
    }

    public void setDisponibleParaUsuario(boolean disponibleParaUsuario) {
        this.disponibleParaUsuario = disponibleParaUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
