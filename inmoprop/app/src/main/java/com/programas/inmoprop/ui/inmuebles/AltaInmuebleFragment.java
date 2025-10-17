package com.programas.inmoprop.ui.inmuebles;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.programas.inmoprop.MenuActivity;
import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentAltaInmuebleBinding;
import com.programas.inmoprop.modelos.Inmueble;

public class AltaInmuebleFragment extends Fragment {

    private AltaInmuebleViewModel vm;
    private FragmentAltaInmuebleBinding binding;
    private Context context;
    private String itemseleccionado;

    //Context context;
    /*public static AltaInmuebleFragment newInstance() {
        return new AltaInmuebleFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(AltaInmuebleViewModel.class);
        binding = FragmentAltaInmuebleBinding.inflate(inflater, container, false);
        context = getContext();
        itemseleccionado="";
        View root = binding.getRoot();
        MenuActivity menuActivity = (MenuActivity) getActivity();
        int idprop = menuActivity.getIdprop();
        Log.d("plp","AltaInmueblef"+ idprop);
        vm.getmBotonHabilitado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                binding.btGuardar.setEnabled(s);
            }
        });

vm.getmCartel().observe(getViewLifecycleOwner(), new Observer<String>() {
    @Override
    public void onChanged(String s) {
        binding.tvMensaje.setText(s);
        Log.d("plp","cargaCartel"+ idprop);

    }
});
TipoInmuebleAdapter adapter = new TipoInmuebleAdapter(context);
binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //binding.etTipoInmueble.setText(adapter.getItem(position));
                itemseleccionado=adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

binding.btGuardar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        String direccion=binding.etDireccion.getText().toString().toUpperCase();
        String ambientes=(binding.etAmbientes.getText().toString());
        String superficie=(binding.etSuperficie.getText().toString());
        String latitud=binding.etLatitud.getText().toString();
        String longitud=binding.etLongitud.getText().toString();
        String idpropietario=idprop+"";
        Boolean borrado=false;
        String importe=binding.etImporte.getText().toString();
        String estado="Disponible";
        String tipoinmueble=itemseleccionado;
                //binding.etTipoInmueble.getText().toString();
        String habilitado="NO";

vm.altaInmueble(direccion,ambientes,superficie,latitud,longitud,idpropietario,borrado,importe,estado,tipoinmueble,habilitado,context);

    }
});


        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}