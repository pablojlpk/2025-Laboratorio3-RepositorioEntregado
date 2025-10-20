package com.programas.inmoprop.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.programas.inmoprop.MainActivity;
import com.programas.inmoprop.MenuActivity;
import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentSlideshowBinding;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Propietario;

import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private SlideshowViewModel vm;

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //SlideshowViewModel slideshowViewModel =
        //       new ViewModelProvider(this).get(SlideshowViewModel.class);

        vm = new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        context = getContext();
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        vm.getText().observe(getViewLifecycleOwner(), textView::setText);

        MenuActivity menuActivity = (MenuActivity) getActivity();
        int idprop = menuActivity.getIdprop();
        vm.obtenerPropiedadesxPropietario( getContext());
        //int idinmueble = 15;

        vm.getmListado().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InmuebleAdapter ia = new InmuebleAdapter(inmuebles, context, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);//si quiero que la muetre en orden inverso true
                // puedo determinar como quiero que se dispongan las tarjetas, para hacer eso necesito crear un grid
                binding.lista.setLayoutManager(glm);
                binding.lista.setAdapter(ia);
            }
        });
        binding.btAltaInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_menu).navigate(R.id.altaInmuebleFragment);// se pone donde se va a inflar el fragment

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
