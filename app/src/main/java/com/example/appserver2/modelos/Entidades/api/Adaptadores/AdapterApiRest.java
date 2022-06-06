package com.example.appserver2.modelos.Entidades.api.Adaptadores;

import com.example.appserver2.modelos.Entidades.api.ConstantesApiRest;
import com.example.appserver2.modelos.Entidades.api.Deserializadores.DeserializarServidoresProdApi;
import com.example.appserver2.modelos.Entidades.api.IEndPointsApiRest;
import com.example.appserver2.modelos.Entidades.api.ServidoresProdResponseApiRest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterApiRest {

    public IEndPointsApiRest conexionApiRest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesApiRest.URL)
                .addConverterFactory(GsonConverterFactory.create(construirGsonDeserializadorContenido()))
                .build();
        return retrofit.create(IEndPointsApiRest.class);
    }

    private Gson construirGsonDeserializadorContenido() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ServidoresProdResponseApiRest.class, new DeserializarServidoresProdApi());
        return gsonBuilder.create();
    }
}
