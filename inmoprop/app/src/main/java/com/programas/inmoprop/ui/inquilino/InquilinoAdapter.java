package com.programas.inmoprop.ui.inquilino;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.programas.inmoprop.R;
import com.programas.inmoprop.modelos.Inquilino;
import com.programas.inmoprop.ui.inmuebles.InmuebleAdapter;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.InquilinoViewHolder> {

    private List<Inquilino> listado;
    private Context context;
    private LayoutInflater li;

public InquilinoAdapter(List<Inquilino> listado, Context context) {
        this.listado = listado;
        this.context = context;
        li = LayoutInflater.from(context);
    }
    public class InquilinoViewHolder extends RecyclerView.ViewHolder {
    TextView nombre;
    TextView apellido;
    TextView mail;
    TextView dni;


        public InquilinoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.etNombre);
            apellido=itemView.findViewById(R.id.etApellido);
            mail=itemView.findViewById(R.id.etMail);
            dni= itemView.findViewById(R.id.etDni);

        }

    }

    @NonNull
    @Override
    public InquilinoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = li.inflate(R.layout.item_inquilino, parent, false);

    return new InquilinoViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoViewHolder holder, int position) {
        Inquilino inquilinoActual = listado.get(position);
        holder.nombre.setText("Nombre: "+inquilinoActual.getNombre());
        holder.apellido.setText("Apellido: "+inquilinoActual.getApellido());
        holder.mail.setText("Mail: "+inquilinoActual.getMail());
        holder.dni.setText("DNI: "+inquilinoActual.getDni());
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

}
