package com.programas.inmoprop.ui.contrato;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.programas.inmoprop.modelos.Contrato;

public class DetalleContratoViewModel extends ViewModel {

    private MutableLiveData<Contrato> mContrato;

    public LiveData<Contrato> getmContrato() {
        if (mContrato == null) {
            mContrato = new MutableLiveData<>();
        }
        return mContrato;
    }

    public void obtenerContrato(Bundle bundle) {
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        if (contrato == null) {
            contrato = new Contrato();
        }
        mContrato.setValue(contrato);
    }


}