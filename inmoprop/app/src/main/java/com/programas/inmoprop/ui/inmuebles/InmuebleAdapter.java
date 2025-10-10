package com.programas.inmoprop.ui.inmuebles;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.programas.inmoprop.R;
import com.programas.inmoprop.modelos.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {
    private List<Inmueble> listado;
    private Context context;
    private LayoutInflater li;

    public InmuebleAdapter (List<Inmueble> listado, Context context, LayoutInflater li) {
        this.listado = listado;
        this.context = context;
        this.li = li;
    }
    public class InmuebleViewHolder extends RecyclerView.ViewHolder {
        TextView direccion;
        TextView ubicacion;
        TextView importe;
        Button btdetalle;

        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            direccion=itemView.findViewById(R.id.tvDireccion);
            ubicacion = itemView.findViewById(R.id.tvUbicacion);
            importe=    itemView.findViewById(R.id.tvImporte);
            btdetalle= itemView.findViewById(R.id.btAltaInmueble);
            }

    }

    @NonNull
    @Override
    public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = li.inflate(R.layout.item_inmueble, parent, false);

        return new InmuebleViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.InmuebleViewHolder holder, int position) {
        Inmueble inmuebleactual = listado.get(position);
        holder.direccion.setText("Dirección: "+inmuebleactual.getDireccion());
        holder.ubicacion.setText("Ubicación: Lat: "+inmuebleactual.getLatitud()+"/Long: "+inmuebleactual.getLongitud());
        holder.importe.setText("Importe: "+ inmuebleactual.getImporte());
        holder.btdetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //para navegar a otro fragment puedo usar el objeto navigation.
                Bundle bundle = new Bundle();
                Inmueble inmueble=inmuebleactual;
                bundle.putSerializable("inmueble", inmueble);
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.detalleInmuebleFragment,bundle);// se pone donde se va a inflar el fragment
                //en la segunda opción pongo donde quiero que me  lleve.
                //Toast.makeText(context, "ID Inmueble"+inmuebleactual.getIdinmueble(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }
}
