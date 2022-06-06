package com.example.appserver2.modelos.BDServidoresProds;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appserver2.modelos.Entidades.InfoGeneral;
import com.example.appserver2.modelos.Entidades.ServidoresProd;

import java.util.LinkedList;

public class BDApi  extends SQLiteOpenHelper implements AlmacenDatosApi{

    public BDApi(@Nullable Context context) {
        super(context, "ApiServidores", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE servidor(" +
                "dependencia TEXT," +
                "marca TEXT," +
                "modelo TEXT," +
                "n_serie TEXT," +
                "so TEXT," +
                "lic TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void guardarInformacionApi(String dependencia, String marca, String modelo, String n_serie, String so, String lic) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO servidor VALUES ('" +
                dependencia +"', '" +
                marca +"', '" +
                modelo + "','"+
                n_serie +"', '" +
                so +"', '"+
                lic +"')");
    }

    public void mostrarInformacionApi(LinkedList<ServidoresProd> lista_Api) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM servidor", null);

        if(cursorLista.moveToFirst()){
            do{
                lista_Api.add(new ServidoresProd(
                        cursorLista.getString(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3),
                        cursorLista.getString(4),
                        cursorLista.getString(5)));

            }while (cursorLista.moveToNext());
        }
    }
}
