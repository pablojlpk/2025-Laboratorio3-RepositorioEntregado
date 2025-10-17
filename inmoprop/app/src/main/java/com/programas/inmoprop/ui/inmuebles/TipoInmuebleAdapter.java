package com.programas.inmoprop.ui.inmuebles;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TipoInmuebleAdapter extends ArrayAdapter<String> {
    private static List<String> tipoinmueble = new ArrayList<>();
    static {

        tipoinmueble.add("Comercial");
        tipoinmueble.add("residencial");
        tipoinmueble.add("Casa");
        tipoinmueble.add("Comercio");
        tipoinmueble.add("Departamento");
        tipoinmueble.add("Depósito");
        tipoinmueble.add("Local");
        tipoinmueble.add("Terreno");


    }


    public TipoInmuebleAdapter(@NonNull Context context) {

        super(context, android.R.layout.simple_spinner_item, tipoinmueble);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
