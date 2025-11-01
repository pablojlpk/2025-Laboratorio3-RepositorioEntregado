package com.programas.inmoprop.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Contrato;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends ViewModel{
    private MutableLiveData<String> mText;
    private MutableLiveData<List<Contrato>> mContratos;

    public LiveData<List<Contrato>> getmContratos(){
        if(mContratos==null){
            mContratos=new MutableLiveData<>();
}
        return mContratos;
    }

    public LiveData<String> getmText() {
        if (mText==null) {
            mText = new MutableLiveData<>();
            mText.setValue("Listado de Contratos");
        }
        return mText;
    }

    public void obtenerContratos(Context context) {
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
        String token = ApiClient.getToken(context);
        Call<List<Contrato>> llamada = api.obtenerContratosActuales(token);
        llamada.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful()) {
                    List<Contrato> lista=response.body();

                    mContratos.setValue(lista);

                }
                else {
                    mText.setValue("No posee Contratos");
                }
            }
            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                mText.setValue("Error de Servidor");
            }

        });
    }
}