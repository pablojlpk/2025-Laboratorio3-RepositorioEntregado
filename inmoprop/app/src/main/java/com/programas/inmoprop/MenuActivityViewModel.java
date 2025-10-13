package com.programas.inmoprop;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.ContentInfo;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.programas.inmoprop.modelos.Propietario;
import com.programas.inmoprop.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivityViewModel extends AndroidViewModel {
private MutableLiveData<String> mCartelUsuario;
private MutableLiveData<Propietario> mPropietario;
private Context context;
    public MenuActivityViewModel(@NonNull Application application) {
        super(application);
        context=getApplication();
        obtenerPropietario();

    }
public LiveData<Propietario> getmPropietario(){
    if(mPropietario==null){
        mPropietario = new MutableLiveData<>();
    }
    return mPropietario;
}
    public LiveData<String> getmCartelUsuario() {
        if(mCartelUsuario==null){
            mCartelUsuario = new MutableLiveData<>();
        }
        return mCartelUsuario;
    }

    public void setmCartelUsuario(String cartel){



        mCartelUsuario.setValue(cartel);
    }
    public void obtenerPropietario(){
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();
        Log.d("tokenobtendiodesdeobtenerPropietario",ApiClient.getToken(context));
        Call<String> llamada =api.obtenerP(ApiClient.getToken(context));
        llamada.enqueue(new Callback<String>() {
           @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    String json = response.body();
                    Gson gson = new Gson();
                    Propietario p = gson.fromJson(json, Propietario.class);
                    mPropietario.postValue(p);
                    Log.d("tokenobtenidopropietario",p.getApellido()+"-"+p.getIdpropietario());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mCartelUsuario.setValue("Error de Servidor");
            }
        });
    }
}
