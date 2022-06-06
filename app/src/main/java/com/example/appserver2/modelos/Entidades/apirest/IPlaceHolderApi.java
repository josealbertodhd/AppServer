package com.example.appserver2.modelos.Entidades.apirest;

import com.example.appserver2.modelos.Entidades.ServidoresProd;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPlaceHolderApi {

    @GET(ConstantesApi.GET_MEDIA_ALL)
    Call<LinkedList<ServidoresProd>> getServidoresProds();

}
