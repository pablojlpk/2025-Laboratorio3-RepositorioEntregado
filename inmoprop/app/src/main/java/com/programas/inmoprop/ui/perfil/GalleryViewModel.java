package com.programas.inmoprop.ui.perfil;

import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Propietario;
import com.programas.inmoprop.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public GalleryViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void actualizarPropietario(Propietario propietario, Context context) {
        {
            if (propietario.getClave().isEmpty() == true) {
                mText.setValue("debe ingresar una clave");
            } else {
                String token = ApiClient.getToken(context);
                ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
                Call<String> llamada = api.actualizarPropietariot(
                        propietario.getIdpropietario(),
                        propietario.getNombre(),
                        propietario.getApellido(),
                        propietario.getDni(),
                        propietario.getMail(),
                        propietario.getClave(),
                        propietario.getBorrado(),
                        token
                );
                llamada.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) { //pregunto si la respuesta es satisfactoria
                            mText.setValue("Datos Actualizados Correctamente");
                        } else {
                            mText.setValue("No se ha podido actualizar. reintente");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mText.setValue("Error de Servidor");
                    }
                });
            }
        }
    }
}

