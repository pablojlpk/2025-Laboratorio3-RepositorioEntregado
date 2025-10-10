package com.programas.inmoprop.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.request.ApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleInmuebleViewModel extends ViewModel {

    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<String> mHabilitado;
    private MutableLiveData<String> mText;


public LiveData<String> getmText() {
    if (mText==null) {
        mText=new MutableLiveData<>();
    }
    return mText;
}

    public LiveData<Inmueble> getmInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
            mHabilitado=new MutableLiveData<>();


   }
    return mInmueble;
    }
    public LiveData<String > getmHabilitado() {
        if (mHabilitado == null) {
            mHabilitado = new MutableLiveData<>();
        }
        return mHabilitado;
    }
    public void datosInmueble(Bundle bundle){
        Inmueble i= (Inmueble) bundle.getSerializable("inmueble");
        Log.d("inmuebleRecibido",i.getInmuebletoString());
        if(i==null){
            i=new Inmueble();
        }else{
        mInmueble.setValue(i);
        mHabilitado.setValue(i.getHabilitado());
        mText.setValue(" ");
        }

    }
    public Boolean obtenerEstadoBool(String estado){
        if (estado.equals("SI")) {
            return true;
        }
        else {
            return false;
        }
    }

    public String obtenerHabilitado(Boolean bestado){
    if (bestado==true) {
            return "SI";
        }
        else {
        return "NO";
        }
    }

public void actualizarInmueble(Inmueble inmueble){
        Log.d("inmuebleActualizado",inmueble.getInmuebletoString());

        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
    Call<String> llamada=api.actualizarInmueble(
            inmueble.getIdinmueble(),
            inmueble.getDireccion(),
            inmueble.getAmbientes(),
            inmueble.getSuperficie(),
            inmueble.getLatitud(),
            inmueble.getLongitud(),
            inmueble.getIdpropietario(),
            inmueble.getTipoinmueble(),
            inmueble.isBorrado(),
            inmueble.getImporte(),
            inmueble.getEstado(),
            inmueble.getHabilitado()
    )
    ;
    llamada.enqueue(new Callback<String>() {
        @Override
        public void onResponse(retrofit2.Call<String> call, Response<String> response) {
            if (response.isSuccessful()) { //pregunto si la respuesta es satisfactoria

                mText.postValue("Inmueble Actualizado");
            }
   else {
                mText.postValue("No se ha podido actualizar. reintente");
            }
        }
        @Override
        public void onFailure(retrofit2.Call<String> call, Throwable t) {
            mText.postValue("Error de Servidor");
        }
    });
}


}