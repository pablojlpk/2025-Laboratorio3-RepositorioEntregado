package com.programas.inmoprop.modelos;

import java.io.Serializable;

public class Propietario implements Serializable {

    private int idpropietario;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String mail;
    private String clave;
    private boolean borrado;

    public Propietario(int idpropietario, String nombre, String apellido, Integer dni, String mail, String clave, boolean borrado) {
        this.idpropietario = idpropietario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.clave = clave;
        this.borrado = borrado;
    }

    public Propietario() {
    }

    public int getIdpropietario() {
        return idpropietario;
    }

    public void setIdpropietario(int idpropietario) {
        this.idpropietario = idpropietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
