package com.programas.inmoprop.modelos;

import java.io.Serializable;

public class Inquilino implements Serializable {
    private int idinquilino;
    private String nombre;
    private String apellido;
    private int dni;
    private String mail;
    private String clave;
    private boolean borrado;

    public Inquilino() {
    }

    public Inquilino(int idinquilino, String nombre, String apellido, int dni, String mail, String clave, boolean borrado) {
        this.idinquilino = idinquilino;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.clave = clave;
        this.borrado = borrado;
    }

    public int getIdinquilino() {
        return idinquilino;
    }

    public void setIdinquilino(int idinquilino) {
        this.idinquilino = idinquilino;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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
