package com.programas.inmoprop.ui.contrato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programas.inmoprop.MenuActivity;
import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentContratoBinding;
import com.programas.inmoprop.modelos.Contrato;

import java.util.List;

public class ContratoFragment extends Fragment {

private ContratoViewModel vm;
    private FragmentContratoBinding binding;
    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        vm=new ViewModelProvider(this).get(ContratoViewModel.class);
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context=getContext();
        final TextView textView = binding.textContrato;

        vm.getmText().observe(getViewLifecycleOwner(), new Observer<String>() {
    @Override
    public void onChanged(String s) {
        textView.setText(s);
    }

});
        vm.getmContratos().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                ContratoAdapter ca= new ContratoAdapter(contratos, getContext(), getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);//si quiero que la muetre en orden inverso true
                binding.lista.setLayoutManager(glm);
                binding.lista.setAdapter(ca);

            }
        });

        MenuActivity menuActivity = (MenuActivity) getActivity();
        int idp= menuActivity.getIdprop();
        vm.obtenerContratos( getContext());

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

