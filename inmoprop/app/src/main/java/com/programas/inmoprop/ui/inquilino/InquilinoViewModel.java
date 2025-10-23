
package com.programas.inmoprop.ui.inquilino;



import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Contrato;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Inquilino;
import com.programas.inmoprop.request.ApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public MutableLiveData<List<Inquilino>> mListadoInquilinos;
    public LiveData<List<Inquilino>> getmListadoInquilinos() {
        if(mListadoInquilinos==null){
            mListadoInquilinos = new MutableLiveData<>();
        }
        return mListadoInquilinos;

    }

    public LiveData<String> getmText() {
        if(mText==null){
            mText = new MutableLiveData<>();
            mText.setValue("Listado de Inquilinos");
        }
        return mText;
    }
    public void obtenerInquilinos(int id_, Context context){
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
String token=ApiClient.getToken(context);
        Call<List<Contrato>> llamada = api.obtenerInquilinosxContrato(id_, token);
        llamada.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful()) {
                    List<Contrato> lista=response.body();
                    List<Inquilino> listainquilinos = new ArrayList<>();
                    //StringBuilder sb = new StringBuilder();

                    for (Contrato c : lista) {
                        listainquilinos.add(c.getDatosinquilino());
                    }
                   mListadoInquilinos.postValue(listainquilinos);

                }
                else {
                    mText.postValue("No posee Inquilinos actuales");
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                mText.postValue("Error de Servidor");
                }
});
    }
}



