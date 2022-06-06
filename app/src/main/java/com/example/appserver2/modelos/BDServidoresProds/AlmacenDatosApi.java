package com.example.appserver2.modelos.BDServidoresProds;

import com.example.appserver2.modelos.Entidades.ServidoresProd;

import java.util.LinkedList;

public interface AlmacenDatosApi {

    void guardarInformacionApi(String dependencia, String marca, String modelo, String n_serie,
                                   String so, String lic);

    public void mostrarInformacionApi(LinkedList<ServidoresProd> lista_Api);
}
