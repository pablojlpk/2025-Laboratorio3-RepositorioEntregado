package com.programas.inmoprop.ui.inmuebles;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Propietario;
import com.programas.inmoprop.request.ApiClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private  MutableLiveData<List<Inmueble>> mListado;

    public LiveData<List<Inmueble>> getmListado() {
        if (mListado==null){
            mListado= new MutableLiveData();
        }

        return mListado;
    }


    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Inmuebles");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public void obtenerPropiedadesxPropietario(int id_){
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
        Call<List<Inmueble>> llamada = api.obtenerPropiedadesxPropietario(id_);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    List<Inmueble> lista=response.body();
                    /*
                    StringBuilder sb = new StringBuilder();

                    for (Inmueble i : lista) {
                        sb.append(i.getDireccion().toString()).append("\n\n");
                        //sb.append(i.toString()).append("\n\n");
                    }

                    mText.setValue(sb.toString());
                   */
                    mListado.setValue(lista);

                    // mText.setValue("Datos Obtenidos: "+lista.size());
                    Log.d("listainmuebles",lista.toString());
                }
                else {
                    mText.setValue("No posee Propiedades");
                }
            }
            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                mText.setValue("Error de Servidor");

            }
        });
    }
//
}