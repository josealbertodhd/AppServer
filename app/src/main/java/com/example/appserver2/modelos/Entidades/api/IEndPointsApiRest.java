package com.example.appserver2.modelos.Entidades.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IEndPointsApiRest {

    @GET(ConstantesApiRest.GET_MEDIA_ALL)
    Call<ServidoresProdResponseApiRest> getContenido();

}
