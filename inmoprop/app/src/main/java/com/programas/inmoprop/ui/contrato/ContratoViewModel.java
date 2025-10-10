package com.programas.inmoprop.ui.contrato;

import android.app.Application;
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

    public void obtenerContratos(int id) {
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
        Call<List<Contrato>> llamada = api.obtenerContratosActuales(id);
        llamada.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful()) {
                    List<Contrato> lista=response.body();
/*
                    StringBuilder sb = new StringBuilder();
                    for (Contrato c : lista) {
                        sb.append("Contrato;"+c.getIdcontrato()).append("\n");
                        sb.append("Inmueble:"+c.getDatosinmueble().getIdinmueble()).append("\n");
                        sb.append("Inquilino:"+c.getDatosinquilino().getApellido()).append("\n");
                        sb.append("").append("\n\n");
                    }
                    mText.setValue(sb.toString());
*/
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