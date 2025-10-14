package com.programas.inmoprop.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Pago;
import com.programas.inmoprop.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosContratoViewModel extends AndroidViewModel {

private MutableLiveData<List<Pago>> mListado;
private MutableLiveData<String> mText;
private Context context;

    public PagosContratoViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();


    }

    public LiveData<List<Pago>> getmListado(){
    if(mListado==null){
        mListado = new MutableLiveData<>();
        }
    return  mListado;
}
public LiveData<String> getmText(){
    if(mText==null){
        mText=new MutableLiveData<>();
    }
    return  mText;
    }

    public void obtenerPagosContrato(int id_){
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
        Call<List<Pago>> llamada = api.obtenerPagosContrato(id_,ApiClient.getToken(context));
        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful()) {
                    List<Pago> lista=response.body();
                    mListado.postValue(lista);
                    mText.postValue("Pagos Realizados");

                }
                else {
                    mText.postValue("No posee Pagos");
                }
            }
            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                mText.postValue("Error de Servidor");

            }
        });
    }

}