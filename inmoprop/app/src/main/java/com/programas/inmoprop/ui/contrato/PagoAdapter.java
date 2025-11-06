package com.programas.inmoprop.ui.contrato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.programas.inmoprop.R;
import com.programas.inmoprop.modelos.Pago;

import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder> {
    private List<Pago> listado;
    private Context context;
    private LayoutInflater li;
    private java.text.SimpleDateFormat formatofecha=new java.text.SimpleDateFormat("dd/MM/yyyy");
    public PagoAdapter(List<Pago> listado, Context context, LayoutInflater li){
        this.listado=listado;
        this.context=context;
        this.li=li;
    }
    public class PagoViewHolder extends RecyclerView.ViewHolder{
        EditText idpago;
        EditText fpago;
        EditText importe;
        CheckBox anulado;
        EditText detalle;
        public PagoViewHolder(@NonNull View itemView) {
            super(itemView);
            idpago=itemView.findViewById(R.id.etIdPago);
            fpago=itemView.findViewById(R.id.etfechapago);
            importe=itemView.findViewById(R.id.etImporte);
            anulado=itemView.findViewById(R.id.checkAnulado);
            detalle=itemView.findViewById(R.id.etDetalle);
        }


    }

    @NonNull
    @Override
    public PagoAdapter.PagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =li.inflate(R.layout.item_pagos, parent, false);
        return new PagoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.PagoViewHolder holder, int position) {
        Pago pagoactual=listado.get(position);
        holder.idpago.setText("Recibo Nro: "+pagoactual.getIdpago());
        holder.fpago.setText("Fecha Pago: "+formatofecha.format(pagoactual.getFpago()));
        //holder.fpago.setText("Fecha Pago: "+pagoactual.getFpago().toString());
        holder.importe.setText("Importe: "+pagoactual.getImporte());
        holder.anulado.setChecked(pagoactual.getAnulado());
        holder.detalle.setText("Detalle: "+pagoactual.getDetalle());

    }

    @Override
    public int getItemCount() {
        return  listado.size();
    }
}
