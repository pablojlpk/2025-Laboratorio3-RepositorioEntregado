package com.programas.inmoprop.ui.contrato;

import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentContratoBinding;
import com.programas.inmoprop.databinding.FragmentDetalleContratoBinding;
import com.programas.inmoprop.modelos.Contrato;

public class DetalleContratoFragment extends Fragment {


    private DetalleContratoViewModel vm;
    private FragmentDetalleContratoBinding binding;
    private java.text.SimpleDateFormat formatofecha=new java.text.SimpleDateFormat("dd/MM/yyyy");



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

    vm=new ViewModelProvider(this).get(DetalleContratoViewModel.class);
    binding=FragmentDetalleContratoBinding.inflate(inflater,container,false);
    View root=binding.getRoot();
    Bundle bundle=getArguments();
        Log.d("bundle_contrato",bundle.toString());
    vm.getmContrato().observe(getActivity(), new androidx.lifecycle.Observer<Contrato>() {
        @Override
        public void onChanged(Contrato contrato) {
            binding.tvTituloContrato.setText(binding.tvTituloContrato.getText()+" " +contrato.getIdcontrato());
            binding.etFechas.setText(("Fecha Desde: "+formatofecha.format(contrato.getFdesde())
                    +" Hasta: "+formatofecha.format(contrato.getFhasta()))
            );
            binding.etImporte.setText("$: "+contrato.getImporte()
            );
            binding.etDatosInmueble.setText("Cod: "+contrato.getDatosinmueble().getIdinmueble()
            +" Dir: "+contrato.getDatosinmueble().getDireccion()
            );
            binding.etUbicacion.setText("Ubicación Lat: "+contrato.getDatosinmueble().getLatitud()
                    + "Long: "+contrato.getDatosinmueble().getLongitud()
            );
            binding.etDatosInmuebleCaracteristica.setText("Amb:"+contrato.getDatosinmueble().getAmbientes()
                    +" Sup: "+contrato.getDatosinmueble().getSuperficie()

            );
        binding.etApeyNombreInq.setText("Apellido y Nombre: "+contrato.getDatosinquilino().getApellido()
                +", "+contrato.getDatosinquilino().getNombre()
                );
        binding.etDni.setText("DNI:" + contrato.getDatosinquilino().getDni());
        binding.etMail.setText("MAIL: "+contrato.getDatosinquilino().getMail());

        binding.btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
              bundle.putInt("codigocontrato",contrato.getIdcontrato());
Log.d("pago",bundle.toString());
                Navigation.findNavController(v).navigate(R.id.action_detalleContratoFragment_to_pagosContratoFragment,bundle);

            }
        });
}
    });
    vm.obtenerContrato(bundle);
    return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}



