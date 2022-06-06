package com.example.appserver2.modelos.Entidades.api;

import com.example.appserver2.modelos.Entidades.ServidoresProd;

import java.util.LinkedList;

public class ServidoresProdResponseApiRest {
    private LinkedList<ServidoresProd> listaServidoresProd;

    public LinkedList<ServidoresProd> getListaServidoresProd() {
        return listaServidoresProd;
    }

    public void setListaServidoresProd(LinkedList<ServidoresProd> listaServidoresProd) {
        this.listaServidoresProd = listaServidoresProd;
    }
}
