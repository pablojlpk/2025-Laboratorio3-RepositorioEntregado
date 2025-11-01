package com.programas.inmoprop.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.programas.inmoprop.modelos.Contrato;
import com.programas.inmoprop.modelos.Inmueble;
import com.programas.inmoprop.modelos.Pago;
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
import retrofit2.http.Path;

public class ApiClient {


    //public static final String URLBASE ="http://192.168.0.169:5126/api/";// casa
    public static final String URLBASE = "http://10.0.2.2:5126/api/";//virtual
    //public static final String URLBASE = "http://10.1.219.78:5126/api/";//virtual
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
        editor.putString("token", token);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        Log.d("tokenobtenidoleido", sp.getString("token", null));

        return sp.getString("token", null);

    }


    public interface InmmobiliariaSetvice{
//obtener propietario con token
        @FormUrlEncoded
        @POST("propietario/login")
        Call<String> login(@Field("mail") String mail_, @Field("clave") String clave_);

        @GET("propietario")
        Call<String> obtenerP(@Header("Authorization") String token);

        //actualizar propietario con token
        @FormUrlEncoded
        @PUT("propietario/actualizar")
        Call<String> actualizarPropietariot(@Field("idpropietario") int id_,@Field("nombre") String nombre_,
                                           @Field("apellido") String apellido_, @Field("dni") int dni_,
                                           @Field("mail") String mail_, @Field("clave") String clave_,
                                           @Field("borrado") boolean borrado_, @Header("Authorization") String token);
        //@FormUrlEncoded//busco pagos
        //@POST("pago/actuales")
        //Call<List<Pago>> obtenerPagosContrato(@Field("idcontrato") int idcontrato_, @Header("Authorization") String token);
        @GET("pago/actuales/{idcontrato}")
        Call<List<Pago>> obtenerPagosContrato(@Path("idcontrato") int idcontrato_, @Header("Authorization") String token);

        @FormUrlEncoded
        @POST("inmueble/alta")
        Call<String> altaInmueble(@Field("direccion") String direccion_,
                                        @Field ("ambientes") int ambientes_,
                                        @Field ("superficie") int superficie_,
                                        @Field ("latitud") double latitud_,
                                        @Field ("longitud") double longitud_,
                                        @Field ("idpropietario") int idpropietario_,
                                        @Field ("tipoinmueble") String tipoinmueble_,
                                        @Field ("estado") String estado_,
                                        @Field ("habilitado") String habilitado_,
                                        @Field ("importe") double importe_,
                                        @Field ("borrado") boolean borrado_,
                                        @Header("Authorization")String token);
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
                                        @Field ("habilitado") String habilitado_,
                                        @Header("Authorization")String token);

        /*
        @FormUrlEncoded
        @POST("propietario/propiedadesxpropietario")
        Call <List<Inmueble>> obtenerPropiedadesxPropietario(@Field("idpropietario") int id_, @Header("Authorization") String token);
        */
        @GET("propietario/propiedadesxpropietario")
        Call <List<Inmueble>> obtenerPropiedadesxPropietario(@Header("Authorization") String token);


        /// /////
        //@GET("contrato/inquilinos/{idpropietario}")
        @GET("contrato/inquilinos")
        Call<List<Contrato>> obtenerInquilinosxContrato( @Header("Authorization") String token);

//@GET("contrato/actuales/{idpropietario}")
@GET("contrato/actuales")
        Call<List<Contrato>> obtenerContratosActuales(@Header("Authorization") String token);
//        @FormUrlEncoded
//        @POST("contrato/actuales")
//        Call <List<Contrato>> obtenerContratosActuales(@Field("idpropietario") int idpropietario_,@Header("Authorization") String token);
        }
}
