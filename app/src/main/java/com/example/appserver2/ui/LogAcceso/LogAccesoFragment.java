package com.example.appserver2.ui.LogAcceso;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appserver2.R;
import com.example.appserver2.ui.ListadoServidores.ListadoServidoresFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class LogAccesoFragment extends Fragment {

    private LinkedList<String> listaHoras = new LinkedList();
    private ListView lv_listadoLog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_acceso, container, false);

        try {
            InputStreamReader archivo = new InputStreamReader(getActivity().openFileInput("LogAccesos.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();

            while (linea != null){
                listaHoras.add(linea);
                linea = br.readLine();
            }

            br.close();
            archivo.close();

        }catch (IOException e){
            Toast.makeText(getActivity(), "No se pudo leer el archivo", Toast.LENGTH_SHORT).show();
        }

        lv_listadoLog = view.findViewById(R.id.lv_listadoLog);
        adaptadorLog adapter = new adaptadorLog();
        lv_listadoLog.setAdapter(adapter);

        return view;
    }

    public class adaptadorLog extends BaseAdapter {

        @Override
        public int getCount() {
            return listaHoras.size();
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
            iv_card.setVisibility(View.GONE);
            tv_codigo.setText(listaHoras.get(position).toString());
            tv_funcion.setVisibility(View.GONE);
            tv_ip_privada.setVisibility(View.GONE);;

            return convertView;
        }
    }
}