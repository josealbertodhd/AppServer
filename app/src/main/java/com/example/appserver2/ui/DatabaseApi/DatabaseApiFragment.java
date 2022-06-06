package com.example.appserver2.ui.DatabaseApi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appserver2.R;
import com.example.appserver2.modelos.BDServidor;
import com.example.appserver2.modelos.BDServidoresProds.AlmacenDatosApi;
import com.example.appserver2.modelos.BDServidoresProds.BDApi;
import com.example.appserver2.modelos.Entidades.ServidoresProd;
import com.example.appserver2.ui.ServidoresProd.ServidorProdFragment;

import java.util.LinkedList;

public class DatabaseApiFragment extends Fragment {
    View view;
    private ListView lv_listado_servidores_api;
    private LinkedList<ServidoresProd> servidoresApi = new LinkedList<>();
    public static AlmacenDatosApi almacenDatosApi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_database_api, container, false);
        almacenDatosApi = new BDApi(getActivity());
        almacenDatosApi.mostrarInformacionApi(servidoresApi);
        lv_listado_servidores_api = view.findViewById(R.id.lv_servidores_api);
        AdapterApi adapter = new AdapterApi();
        lv_listado_servidores_api.setAdapter(adapter);
        return view;
    }

    public class AdapterApi extends BaseAdapter{

        @Override
        public int getCount() {
            return servidoresApi.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card_listado_servidores, parent, false);
            TextView tv_codigo, tv_funcion, tv_ip_privada;
            ImageView iv_card;
            iv_card = convertView.findViewById(R.id.iv_card);
            tv_codigo = convertView.findViewById(R.id.tv_codigo_servidor);
            tv_funcion = convertView.findViewById(R.id.tv_funcion);
            tv_ip_privada = convertView.findViewById(R.id.tv_ip_privada);
            tv_codigo.setTextSize(16);
            tv_codigo.setText(servidoresApi.get(position).getN_serie().toString());
            tv_funcion.setText(servidoresApi.get(position).getModelo().toString());
            tv_ip_privada.setText(servidoresApi.get(position).getMarca().toString());

            return convertView;
        }
    }
}