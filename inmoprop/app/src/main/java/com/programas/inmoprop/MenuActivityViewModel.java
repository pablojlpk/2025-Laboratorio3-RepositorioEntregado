package com.programas.inmoprop;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.navigation.NavigationView;

public class MenuActivityViewModel extends AndroidViewModel {
private MutableLiveData<String> mCartelUsuario;
    public MenuActivityViewModel(@NonNull Application application) {
        super(application);



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

}
