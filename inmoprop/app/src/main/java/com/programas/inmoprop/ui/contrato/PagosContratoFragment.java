package com.programas.inmoprop.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentPagosContratoBinding;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Pago;
import com.programas.inmoprop.request.ApiClient;
import com.programas.inmoprop.ui.inmuebles.DetalleInmuebleViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosContratoFragment extends Fragment {

    private PagosContratoViewModel vm;
    private FragmentPagosContratoBinding binding;
    private View root;
    Context context;


    public static PagosContratoFragment newInstance() {
        return new PagosContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm= new ViewModelProvider(this).get(PagosContratoViewModel.class);
        binding=FragmentPagosContratoBinding.inflate(inflater,container,false);
        context=getContext();
        Bundle bundle=getArguments();
        Integer codigocontrato=  bundle.getInt("codigocontrato");

        root = binding.getRoot();
        vm.getmText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.mText.setText(s);
            }
        });
        vm.getmListado().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                PagoAdapter pa= new PagoAdapter(pagos, getContext(), getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);//si quiero que la muetre en orden inverso true
                binding.lista.setLayoutManager(glm);
                binding.lista.setAdapter(pa);

            }
        });
        vm.obtenerPagosContrato(codigocontrato);



        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}