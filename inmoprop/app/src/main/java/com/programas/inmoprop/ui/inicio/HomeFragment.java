package com.programas.inmoprop.ui.inicio;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.programas.inmoprop.R;
import com.programas.inmoprop.databinding.FragmentContratoBinding;
import com.programas.inmoprop.databinding.FragmentHomeBinding;
import com.programas.inmoprop.ui.contrato.ContratoViewModel;

//public class HomeFragment extends Fragment implements OnMapReadyCallback {
    public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mapa;
    private HomeViewModel vm;
    private FragmentHomeBinding binding;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        vm.getmMapaActual().observe(getViewLifecycleOwner(), new Observer<HomeViewModel.MapaActual>() {
            @Override
            public void onChanged(HomeViewModel.MapaActual mapaActual) {
                SupportMapFragment mapFragment = (SupportMapFragment)
                        getChildFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(mapaActual);
                }

            }
        });

vm.cargarMapa();
        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        Toast.makeText(this.getContext(), "hola", Toast.LENGTH_SHORT).show();
    }

}