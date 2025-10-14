package com.programas.inmoprop.modelos;

import java.util.Date;

public class Pago {
    private int idpago;
    private int idcontrato;
    private Contrato datosContrato;
    private double importe;
    private Date fpago;

    private boolean borrado;
    private boolean anulado;
    private String detalle;

    public Pago() {
    }

    public Pago(int idpago, int idcontrato, Contrato datosContrato, double importe, Date fpago, boolean borrado, boolean anulado, String detalle) {
        this.idpago = idpago;
        this.idcontrato = idcontrato;
        this.datosContrato = datosContrato;
        this.importe = importe;
        this.fpago = fpago;
        this.borrado = borrado;
        this.anulado = anulado;
        this.detalle = detalle;
    }


    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contrato getDatosContrato() {
        return datosContrato;
    }

    public void setDatosContrato(Contrato datosContrato) {
        this.datosContrato = datosContrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFpago() {
        return fpago;
    }

    public void setFpago(Date fpago) {
        this.fpago = fpago;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
