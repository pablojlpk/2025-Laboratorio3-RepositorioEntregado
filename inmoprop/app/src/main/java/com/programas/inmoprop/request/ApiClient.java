package com.programas.inmoprop.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.programas.inmoprop.modelos.Contrato;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Propietario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiClient {


    //public static final String URLBASE ="http://192.168.0.169:5126/api/";// casa
    //public static final String URLBASE = "http://10.0.2.2:5126/api/";//virtual

  public static final String URLBASE = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";//virtual


    public static InmmobiliariaSetvice getApiInmobiliaria() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(InmmobiliariaSetvice.class);

    }
    //gaurdar y leer token metodos crear
    public static void setToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", "Bearer "+token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }


    public interface InmmobiliariaSetvice{


        @FormUrlEncoded//APuntaa la api de prueba retrofit
        @POST("/api/Propietarios/login")
        Call<String> login(@Field("usuario") String usuario_, @Field("clave") String clave_);



        @FormUrlEncoded
        @POST("propietario/login")
        Call<String> obtenerPropietario(@Field("mail") String mail_, @Field("clave") String clave_);
        //metodo abstracto entre comillas nombre campo bd en este caso propietarios con el call le digo que tipo de formato me va a devolver
        //en una interface el metodo astrapto siempre va a ser public por lo que se lo puedo sacar

//obtener propietario con token

        @GET("propietarios")
        Call<Propietario> obtenerP(@Header("Authorization") String  token);
/// /////
        @FormUrlEncoded
        @PUT("propietario/actualizar")
        Call<String> actualizarPropietario(@Field("idpropietario") int id_,@Field("nombre") String nombre_,
                                           @Field("apellido") String apellido_, @Field("dni") int dni_,
                                           @Field("mail") String mail_, @Field("clave") String clave_,
                                           @Field("borrado") boolean borrado_);


        @FormUrlEncoded
        @POST("propietario/propiedadesxpropietario")
        Call <List<Inmueble>> obtenerPropiedadesxPropietario(@Field("idpropietario") int id_);

        @FormUrlEncoded
        @POST("contrato/inquilinos")
        Call <List<Contrato>> obtenerInquilinosxContrato(@Field("idpropietario") int idpropietario_);

        @FormUrlEncoded
        @POST("contrato/actuales")
        Call <List<Contrato>> obtenerContratosActuales(@Field("idpropietario") int idpropietario_);

// actualizar inmueble
        @FormUrlEncoded
        @PUT("inmueble/actualizar")
        Call<String> actualizarInmueble(@Field("idinmueble") int idinmueble_,
                                         @Field("direccion") String direccion_,
                                         @Field ("ambientes") int ambientes_,
                                         @Field ("superficie") int superficie_,
                                         @Field ("latitud") double latitud_,
                                         @Field ("longitud") double longitud_,
                                         @Field ("idpropietario") int idpropietario_,
                                         @Field ("tipoinmueble") String tipoinmueble_,
                                         @Field ("borrado") boolean borrado_,
                                         @Field ("importe") double importe_,
                                         @Field ("estado") String estado_,
                                         @Field ("habilitado") String habilitado_);
        }
}
