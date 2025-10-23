package com.programas.inmoprop.ui.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogoutViewModel extends ViewModel {



    private MutableLiveData<String> mText;


    public LogoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");

    }


    public LiveData<String> getmText() {
        if(mText==null){
            mText = new MutableLiveData<>();
        }
        return mText;
    }
}