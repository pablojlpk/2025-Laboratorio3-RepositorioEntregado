package com.programas.inmoprop.modelos;

import java.io.Serializable;
import java.sql.Date;

public class Contrato implements Serializable {
    private int idcontrato;
    private int idinquilino;
    private Inquilino datosinquilino;

    private int idinmueble;
    private Inmueble datosinmueble;

    private Date fdesde;
    private Date fhasta;
    private double importe;
    private boolean borrado;

    public Contrato() {
    }

    public Contrato(int idcontrato, int idinquilino, Inquilino datosinquilino, int idinmueble, Inmueble datosinmueble, Date fdesde, Date fhasta, double importe, boolean borrado) {
        this.idcontrato = idcontrato;
        this.idinquilino = idinquilino;
        this.datosinquilino = datosinquilino;


        this.idinmueble = idinmueble;
        this.datosinmueble =  datosinmueble;

        this.fdesde = fdesde;
        this.fhasta = fhasta;
        this.importe = importe;
        this.borrado = borrado;
    }


    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public int getIdinquilino() {
        return idinquilino;
    }

    public void setIdinquilino(int idinquilino) {
        this.idinquilino = idinquilino;
    }





    public int getIdinmueble() {
        return idinmueble;
    }

    public Inquilino getDatosinquilino() {
        return datosinquilino;
    }

    public void setDatosinquilino(Inquilino datosinquilino) {
        this.datosinquilino = datosinquilino;
    }

    public Inmueble getDatosinmueble() {
        return datosinmueble;
    }

    public void setDatosinmueble(Inmueble datosinmueble) {
        this.datosinmueble = datosinmueble;
    }

    public void setIdinmueble(int idinmueble) {
        this.idinmueble = idinmueble;
    }


    public Date getFdesde() {
        return fdesde;
    }

    public void setFdesde(Date fdesde) {
        this.fdesde = fdesde;
    }

    public Date getFhasta() {
        return fhasta;
    }

    public void setFhasta(Date fhasta) {
        this.fhasta = fhasta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
}
