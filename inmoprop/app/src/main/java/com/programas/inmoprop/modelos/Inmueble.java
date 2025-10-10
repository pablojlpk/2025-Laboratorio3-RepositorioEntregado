package com.programas.inmoprop.modelos;

import java.io.Serializable;

public class Inmueble implements Serializable {

    private int idinmueble;
    private String direccion;
    private int ambientes;
    private int superficie;
    private double latitud;
    private double longitud;
        private int idpropietario;
        private boolean borrado;
    private double importe;
        private String estado;
        private String tipoinmueble;
    private String habilitado;

    public Inmueble() {
    }

    public Inmueble(int idinmueble, String direccion, int ambientes, int superficie, double latitud, double longitud, int idpropietario,String tipoinmueble, boolean borrado, double importe, String estado, String habilitado) {
        this.idinmueble = idinmueble;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idpropietario = idpropietario;
        this.tipoinmueble = tipoinmueble;
        this.borrado = borrado;
        this.importe = importe;
        this.estado = estado;
        this.habilitado = habilitado;
    }

//getter y setter

    public int getIdinmueble() {
        return idinmueble;
    }

    public void setIdinmueble(int idinmueble) {
        this.idinmueble = idinmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getIdpropietario() {
        return idpropietario;
    }

    public String getTipoinmueble() {
        return tipoinmueble;
    }

    public void setIdpropietario(int idpropietario) {
        this.idpropietario = idpropietario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    public void setTipoinmueble(String tipoinmueble) {
        this.tipoinmueble = tipoinmueble;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
    public String getInmuebletoString() {
        return "Inmueble{" +
                "idinmueble=" + idinmueble +
                ", direccion='" + direccion + '\'' +
                ", ambientes=" + ambientes +
                ", superficie=" + superficie +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", idpropietario=" + idpropietario +
                ", borrado=" + borrado +
                ", importe=" + importe +
                ", estado='" + estado + '\'' +
                ", habilitado='" + habilitado + '\'' +
                '}';
    }
}
