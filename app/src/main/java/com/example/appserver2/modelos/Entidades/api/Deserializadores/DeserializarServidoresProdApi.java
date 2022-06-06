package com.example.appserver2.modelos.Entidades.api.Deserializadores;

import android.util.Log;
import android.widget.Toast;

import com.example.appserver2.modelos.Entidades.ServidoresProd;
import com.example.appserver2.modelos.Entidades.api.ConstantesApiRest;
import com.example.appserver2.modelos.Entidades.api.ServidoresProdResponseApiRest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class DeserializarServidoresProdApi implements JsonDeserializer<ServidoresProdResponseApiRest> {
    @Override
    public ServidoresProdResponseApiRest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeysApiRest.MEDIA_ARRAY);
        Gson gson = new Gson();
        ServidoresProdResponseApiRest servidoresProdResponseApiRest =
                gson.fromJson(json, ServidoresProdResponseApiRest.class);
        servidoresProdResponseApiRest.setListaServidoresProd(deserializarJsonServidores(jsonArray));

        return servidoresProdResponseApiRest;
    }

    private LinkedList<ServidoresProd> deserializarJsonServidores(JsonArray jsonArray) {
        LinkedList<ServidoresProd> servidoresProdLinkedList = new LinkedList<>();

        for (int i=0; i< jsonArray.size(); i++){
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            String dependencia = jsonObject.get(JsonKeysApiRest.MEDIA_DEPENDENCIA).getAsString();
            String marca = jsonObject.get(JsonKeysApiRest.MEDIA_MARCA).getAsString();
            String modelo = jsonObject.get(JsonKeysApiRest.MEDIA_MODELO).getAsString();
            String n_serie = jsonObject.get(JsonKeysApiRest.MEDIA_NSERIE).getAsString();
            String so = jsonObject.get(JsonKeysApiRest.MEDIA_SO).getAsString();
            String lic = jsonObject.get(JsonKeysApiRest.MEDIA_LIC).getAsString();

            ServidoresProd servidoresProd = new ServidoresProd(dependencia, marca,
                    modelo, n_serie, so, lic);

            servidoresProdLinkedList.add(servidoresProd);
        }
        return  servidoresProdLinkedList;
    }
}
