package com.programas.inmoprop;

import static com.programas.inmoprop.request.ApiClient.getApiInmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.programas.inmoprop.modelos.Propietario;
import com.programas.inmoprop.request.ApiClient;

import java.io.Serializable;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje;
    private int contador = 0;
    private Context context;


    public LiveData<String> getmMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void setmMensaje() {
        mMensaje.setValue("Cantidad de veces que hizo click: " + contador++);
    }

    public void loginToken(String usuario, String clave){
        ApiClient.InmmobiliariaSetvice api= getApiInmobiliaria();
        Call<String>llamada= api.login(usuario,clave);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) { //pregunto si la respuesta es satisfactoria
                    mMensaje.postValue("Bienvenido ");
                    String token=response.body();
                    ApiClient.setToken(context,"Bearer "+token);
                    Log.d("tokenobtenido",token);


                    //
                    //Bundle bundle = new Bundle();
                    //String usu= "usuario prueba";
                    //String mail="maildeprueba@pp";
                    Intent intent = new Intent(context, MenuActivity.class);
                    //intent.putExtra("usuario", usu);
                    //intent.putExtra("mail", mail);
                    //intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    //

                } else {
                    mMensaje.postValue("Usuario y/o contraseña Incorrecta; reintente");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mMensaje.postValue("Error de Servidor");
            }

    }
        );
    }

    /*
    public void login(String mail, String clave) {
        ApiClient.InmmobiliariaSetvice api = getApiInmobiliaria();
        Call<String> llamada = api.obtenerPropietario(mail, clave);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) { //pregunto si la respuesta es satisfactoria
                    mMensaje.setValue("Bienvenido ");

                    // ver este codigo...

                    String json = response.body();
                    Gson gson = new Gson();
                    Propietario propietario = gson.fromJson(json, Propietario.class);

                    String usu= propietario.getNombre()+" "+propietario.getApellido();
                    String mail=propietario.getMail();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("propietario", propietario);

                    Intent intent = new Intent(context, MenuActivity.class);
                    intent.putExtra("usuario", usu);
                    intent.putExtra("mail", mail);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    mMensaje.setValue("Usuario y/o contraseña Incorrecta; reintente");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mMensaje.setValue("Error de Servidor");
            }
        });



    }
    */
}

