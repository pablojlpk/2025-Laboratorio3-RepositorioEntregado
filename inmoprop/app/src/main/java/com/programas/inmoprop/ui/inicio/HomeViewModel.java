package com.programas.inmoprop.ui.inicio;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends AndroidViewModel {



    private MutableLiveData<MapaActual> mMapaActual;


    public HomeViewModel(@NonNull Application application) {
        super(application);



    }

    public LiveData<MapaActual> getmMapaActual() {
        if (mMapaActual == null) {
            mMapaActual = new MutableLiveData<>();
        }
        return mMapaActual;
    }


    public void cargarMapa(){
        MapaActual mp = new MapaActual();
        mMapaActual.setValue(mp);


    }


    public class MapaActual implements OnMapReadyCallback{

        private LatLng inmoUbicacion = new LatLng(-33.295408, -66.28449);


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) { //cuando esté cargado y esté renderizado cargamos las etiquetas donde queremos que se muestre el mapa.

            MarkerOptions marcadorInmobiliaria = new MarkerOptions();
            marcadorInmobiliaria.position(inmoUbicacion);
            marcadorInmobiliaria.title("Inmobiliaria");
            marcadorInmobiliaria.snippet("Inmobiliaria DelPaso");
            marcadorInmobiliaria.draggable(true);

            googleMap.addMarker(marcadorInmobiliaria);
            googleMap.setMapType(googleMap.MAP_TYPE_SATELLITE);
            CameraPosition cameraposition = new CameraPosition.
                    Builder()
                    .target(inmoUbicacion)
                    .zoom(19)
                    .bearing(45)//cambio la rotación de grados
                    .tilt(30)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraposition); //actualizo esta nueva posición
            googleMap.animateCamera(cameraUpdate);
        }
    }

}

