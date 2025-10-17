package com.programas.inmoprop.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltaInmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<String> mCartel;
    public MutableLiveData<Boolean> mBotonHabilitado;

    public LiveData<Boolean> getmBotonHabilitado() {
        if (mBotonHabilitado == null) {
            mBotonHabilitado = new MutableLiveData<>();
        }
        return mBotonHabilitado;
    }

    public LiveData<String> getmCartel() {
        if (mCartel == null) {
            mCartel = new MutableLiveData<>();
        }
        return mCartel;
    };

   public LiveData<Inmueble> getInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
            mBotonHabilitado.setValue(false);
            mCartel.setValue("");

        }
        return mInmueble;
    }

    /*
    public AltaInmuebleViewModel(@NonNull Application application) {
        super(application);
    mBotonHabilitado.setValue(true);
    mCartel.setValue("");
    }
*/

    public void altaInmueble(String direccion, String ambientes, String superficie, String latitud, String longitud, String idpropietario, Boolean borrado, String importe, String estado, String tipoinmueble, String habilitado,Context context) {
     if(direccion.isBlank()||ambientes.isBlank()||superficie.isBlank()||latitud.isBlank()||longitud.isBlank()||idpropietario.isBlank()||importe.isBlank()||estado.isBlank()||tipoinmueble.isBlank()||habilitado.isBlank()){
         mCartel.setValue("Faltan Ingresar Datos Corrobore");
     } else {
         String token = ApiClient.getToken(context);
         ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
         Call<String> llamada = api.altaInmueble(
                 direccion,
                 Integer.parseInt(ambientes),
                 Integer.parseInt(superficie),
                 Double.parseDouble(latitud),
                 Double.parseDouble(longitud),
                 Integer.parseInt(idpropietario),
                 tipoinmueble,
                 estado,
                 habilitado,
                 Double.parseDouble(importe),
                 borrado,
                 token
                 );
         llamada.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 if (response.isSuccessful()) { //pregunto si la respuesta es satisfactoria
                     mCartel.postValue("Alta realizada Correctamente");
                     mBotonHabilitado.postValue(false);
                 } else {
                     mCartel.postValue("No se ha podido dar de Alta.");
                 }
             }
             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 mCartel.setValue("Error de Servidor");
             }
         });
     }
    }

}
