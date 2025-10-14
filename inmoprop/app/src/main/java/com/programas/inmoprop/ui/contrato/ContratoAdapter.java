package com.programas.inmoprop.ui.contrato;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.programas.inmoprop.R;
import com.programas.inmoprop.modelos.Contrato;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ContratoViewHolder> {

    private List<Contrato> listado;
    private Context context;
    private LayoutInflater li;

    public ContratoAdapter(List<Contrato> listado, Context context, LayoutInflater li){
        this.listado=listado;
        this.context=context;
        this.li=li;
    }
public class ContratoViewHolder extends RecyclerView.ViewHolder{
        EditText contrato;
        EditText inquilino;
        EditText fecha;
        Button btdetalle;

    public ContratoViewHolder(@NonNull View itemView) {
        super(itemView);
        contrato =itemView.findViewById(R.id.etContrato);
        inquilino=itemView.findViewById(R.id.etInquilino);
        fecha=itemView.findViewById(R.id.etFechaContrato);
        btdetalle=itemView.findViewById(R.id.btDetalleContrato);
    }
}

    @NonNull
    @Override
    public ContratoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =li.inflate(R.layout.item_contrato, parent, false);
    return new ContratoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoViewHolder holder, int position) {
        Contrato contratoactual=listado.get(position);
        holder.contrato.setText("Contrato Nro:"+
                contratoactual.getIdcontrato()+
                "Dirección Inmueble: "+
                contratoactual.getDatosinmueble().getDireccion());
        holder.inquilino.setText("Inquilino: "+
                contratoactual.getDatosinquilino().getApellido()
        +", "+ contratoactual.getDatosinquilino().getNombre());
        holder.fecha.setText("Fecha: "+contratoactual.getFhasta().toString());

        holder.btdetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                Contrato contrato = contratoactual;
                bundle.putSerializable("contrato", contrato);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.detalleContratoFragment,bundle);


            }
        });

    }


    @Override
    public int getItemCount() {
        return listado.size();
    }
}
