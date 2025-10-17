package com.programas.inmoprop.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentDetalleContratoBinding;
import com.programas.inmoprop.databinding.FragmentDetalleInmuebleBinding;
import com.programas.inmoprop.modelos.Inmueble;

public class DetalleInmuebleFragment extends Fragment {
private DetalleInmuebleViewModel vm;
    private FragmentDetalleInmuebleBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm= new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View root=binding.getRoot();
        Bundle bundle= getArguments();
        vm.getmInmueble().observe(getActivity(), new androidx.lifecycle.Observer<Inmueble>() {
    @Override
    public void onChanged(Inmueble i) {
        binding.tvDireccion.setText("Dir:" + i.getDireccion());
        binding.tvAmbientes.setText("Ambientes" + i.getAmbientes());
        binding.tvSuperficie.setText("Sup.:" + i.getSuperficie());
        binding.tvUbicacion.setText("Lat: " + i.getLatitud() + " Long: " + i.getLongitud());
        binding.tvImporte.setText("importe" + i.getImporte() + "");
        binding.tvTipoAmbiente.setText("Tipo Ambiente:"+i.getTipoinmueble());
        binding.tvHabilitado.setText(i.getHabilitado());
        vm.obtenerHabilitado(binding.tvHabilitado.getText().toString());
    }
    });

vm.getmHabilitado().observe(getActivity(), new Observer<String>() {
    @Override
    public void onChanged(String s) {
        binding.tvHabilitado.setText(s);
    }
});
vm.getmEstado().observe(getActivity(), new Observer<Boolean>() {
    @Override
    public void onChanged(Boolean aBoolean) {
        binding.checkBox.setChecked(aBoolean);
    }
});
binding.checkBox.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
vm.cambiarEstadoClick(binding.checkBox.isChecked());
                                    }
                                });
vm.getmText().observe(getActivity(), new androidx.lifecycle.Observer<String>() {
    @Override
    public void onChanged(String s) {
        binding.tvMensaje.setText(s);
    }
});
vm.datosInmueble(bundle);
binding.btGuardarCambios.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Inmueble i= (Inmueble) bundle.getSerializable("inmueble");

        i.setHabilitado(binding.tvHabilitado.getText().toString());
        vm.actualizarInmueble(i,getContext());
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