
package com.programas.inmoprop.ui.inquilino;



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


/*
    public InquilinoViewModel() {
        //mText = new MutableLiveData<>();
        mListadoInquilinos = new MutableLiveData<>();
        mText.setValue("");
    }

 */
    public LiveData<String> getmText() {
        if(mText==null){
            mText = new MutableLiveData<>();
            mText.setValue("Listado de Inquilinos");
        }
        return mText;
    }
    public void obtenerInquilinos(int id_){
        ApiClient.InmmobiliariaSetvice api = ApiClient.getApiInmobiliaria();

        Call<List<Contrato>> llamada = api.obtenerInquilinosxContrato(id_);
        llamada.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if (response.isSuccessful()) {
                    List<Contrato> lista=response.body();
                    List<Inquilino> listainquilinos = new ArrayList<>();
                    Inquilino i=new Inquilino(25,"pablo","Lopez",23412222, "pp@pp.com","",false);
                    StringBuilder sb = new StringBuilder();

                    for (Contrato c : lista) {
                        listainquilinos.add(c.getDatosinquilino());
/*
                        sb.append(c.getDatosinquilino().getApellido()+","
                                        + c.getDatosinquilino().getNombre()+"\n\n"

                                        +"Inmueble Alquilado: "
                                        +c.getDatosinmueble().getIdinmueble()
                                        +c.getDatosinmueble().getDireccion())
                                .append("\n\n");


                        //sb.append(i.getDireccion().toString()).append("\n\n");
*/
                    }

                    //mText.setValue(sb.toString());
                    mListadoInquilinos.setValue(listainquilinos);

                }
                else {
                    mText.setValue("No posee Inquilinos actuales");
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                mText.setValue("Error de Servidor");

            }

        });
    }
}
//


