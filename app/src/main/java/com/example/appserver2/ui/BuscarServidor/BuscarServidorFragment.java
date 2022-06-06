package com.example.appserver2.ui.BuscarServidor;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.appserver2.R;
import com.example.appserver2.modelos.AlmacenDatos;
import com.example.appserver2.modelos.BDServidor;
import com.example.appserver2.modelos.Entidades.InfoGeneral;
import com.example.appserver2.modelos.Entidades.Configuracion_red;

import java.util.LinkedList;


public class BuscarServidorFragment extends Fragment {

    private ListView lv_lista;
    private SearchView sv_buscar;
    private RadioButton rb_codigo, rb_funcion, rb_ip_interna, rb_ip_externa;
    public static AlmacenDatos almacenDatos;
    private LinkedList<InfoGeneral> lista = new LinkedList<>();
    private LinkedList<Configuracion_red> listaRed = new LinkedList<>();
    private LinkedList<InfoGeneral> listaValue = new LinkedList<>();
    private LinkedList<Configuracion_red> listaValueRed= new LinkedList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_buscar_servidor, container, false);
        rb_codigo = view.findViewById(R.id.rb_codigo);
        rb_funcion = view.findViewById(R.id.rb_funcion);
        rb_ip_externa = view.findViewById(R.id.rb_ip_externa);
        rb_ip_interna = view.findViewById(R.id.rb_ip_externa);

        rb_codigo.setChecked(true);

        almacenDatos = new BDServidor(getActivity());
        almacenDatos.mostrarInformacionGeneral(lista);
        listaValue.addAll(lista);
        almacenDatos.mostrarConfiguracionRed(listaRed);
        listaValueRed.addAll(listaRed);
        lv_lista = view.findViewById(R.id.lv_lista);
        adapterBuscar adapter = new adapterBuscar();
        lv_lista.setAdapter(adapter);
        sv_buscar = (SearchView) view.findViewById(R.id.sv_buscar);

        lv_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alerta_entendido);
                dialog.setCanceledOnTouchOutside(false);
                Button b_entendido = dialog.findViewById(R.id.b_entendido);
                ImageView iva_card = dialog.findViewById(R.id.iva_card);
                TextView tva_codigo = dialog.findViewById(R.id.tva_codigo);
                TextView tva_tipo_servidor = dialog.findViewById(R.id.tva_tipo_servidor);
                TextView tva_caracteristicas = dialog.findViewById(R.id.tva_caracteristicas);
                TextView tva_ambiente = dialog.findViewById(R.id.tva_ambiente);
                TextView tva_modelo = dialog.findViewById(R.id.tva_modelo);
                TextView tva_marca = dialog.findViewById(R.id.tva_marca);
                TextView tva_funcion = dialog.findViewById(R.id.tva_funcion);

                tva_codigo.setText("Codigo: " + Integer.toString(lista.get(position).getCodigo_inventario()).toString());
                tva_tipo_servidor.setText("Tipo de Servidor: " + lista.get(position).getTipo_servidor().toString());
                tva_caracteristicas.setText("Caracteristicas: " + lista.get(position).getCaracteristicas_servidor().toString());
                tva_ambiente.setText("Ambiente: " + lista.get(position).getAmbiente_servidor().toString());
                tva_modelo.setText("Modelo: " + lista.get(position).getModelo_servidor().toString());
                tva_marca.setText("Marca: " + lista.get(position).getMarca_servidor().toString());
                tva_funcion.setText("Funcion: " + lista.get(position).getFuncion_servidor().toString());

                if(lista.get(position).getFuncion_servidor().toString().equals("DNS")){
                    iva_card.setImageResource(R.drawable.ic_dns);
                }else if(lista.get(position).getFuncion_servidor().toString().equals("Web")){
                    iva_card.setImageResource(R.drawable.ic_web);
                }else if (lista.get(position).getFuncion_servidor().toString().equals("BD")){
                    iva_card.setImageResource(R.drawable.ic_database);
                }else if (lista.get(position).getFuncion_servidor().toString().equals("Directorio Activo")){
                    iva_card.setImageResource(R.drawable.ic_desktop);
                }

                b_entendido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        sv_buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return view;
    }

    public class adapterBuscar extends BaseAdapter implements Filterable {

        ValueFilter valueFilter;


        @Override
        public int getCount() {
            return lista.size();
        }

        @Override
        public Object getItem(int position) {
            if(rb_ip_externa.isChecked() || rb_ip_interna.isChecked()){
                return listaRed.get(position);
            }else{
                return lista.get(position);
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.card_listado_servidores,
                    parent, false);

            TextView tv_codigo, tv_funcion, tv_ip_privada;
            ImageView iv_card;
            iv_card = convertView.findViewById(R.id.iv_card);
            tv_codigo = convertView.findViewById(R.id.tv_codigo_servidor);
            tv_funcion = convertView.findViewById(R.id.tv_funcion);
            tv_ip_privada = convertView.findViewById(R.id.tv_ip_privada);
            tv_codigo.setText(Integer.toString(lista.get(position).getCodigo_inventario()).toString());
            tv_funcion.setText(lista.get(position).getFuncion_servidor().toString());
            tv_ip_privada.setText(lista.get(position).getMarca_servidor().toString());

            if(lista.get(position).getFuncion_servidor().toString().equals("DNS")){
                iv_card.setImageResource(R.drawable.ic_dns);
            }else if(lista.get(position).getFuncion_servidor().toString().equals("Web")){
                iv_card.setImageResource(R.drawable.ic_web);
            }else if (lista.get(position).getFuncion_servidor().toString().equals("BD")){
                iv_card.setImageResource(R.drawable.ic_database);
            }else if (lista.get(position).getFuncion_servidor().toString().equals("Directorio Activo")){
                iv_card.setImageResource(R.drawable.ic_desktop);
            }

            return convertView;
        }

        @Override
        public Filter getFilter() {
            if(valueFilter == null){
                valueFilter = new ValueFilter();
            }
            return valueFilter;
        }

        private class ValueFilter extends Filter{

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0){

                    LinkedList<InfoGeneral> listaFiltrada = new LinkedList<>();
                    LinkedList<Configuracion_red> listaFiltradaRed = new LinkedList<>();

                    if(rb_codigo.isChecked()){
                        for (int i=0; i<listaValue.size(); i++){
                            if ((Integer.toString(listaValue.get(i).getCodigo_inventario())).toString().contains((constraint.toString().toUpperCase()))){
                                InfoGeneral infoGeneral = listaValue.get(i);
                                listaFiltrada.add(infoGeneral);
                            }
                        }
                    }else if(rb_funcion.isChecked()){
                        for (int i=0; i<listaValue.size(); i++){
                            if ((listaValue.get(i).getFuncion_servidor().toString().toUpperCase()).toString().contains((constraint.toString().toUpperCase()))){
                                InfoGeneral infoGeneral = listaValue.get(i);
                                listaFiltrada.add(infoGeneral);
                            }
                        }
                    }else if (rb_ip_externa.isChecked()){
                        for (int i=0; i<listaValueRed.size(); i++){
                            if ((listaValueRed.get(i).getDireccion_publica().toString().toUpperCase()).toString().contains((constraint.toString().toUpperCase()))){
                                Configuracion_red configuracionRed = listaValueRed.get(i);
                                listaFiltradaRed.add(configuracionRed);
                            }
                        }
                    }else {
                        for (int i=0; i<listaValueRed.size(); i++){
                            if ((listaValueRed.get(i).getIp_privada().toString().toUpperCase()).toString().contains((constraint.toString().toUpperCase()))){
                                Configuracion_red configuracionRed = listaValueRed.get(i);
                                listaFiltradaRed.add(configuracionRed);
                            }
                        }
                    }

                    if(rb_ip_externa.isChecked() || rb_ip_interna.isChecked()){
                        results.count = listaFiltradaRed.size();
                        results.values = listaFiltradaRed;
                    }else{
                        results.count = listaFiltrada.size();
                        results.values = listaFiltrada;
                    }

                }else {

                    if(rb_ip_externa.isChecked() || rb_ip_interna.isChecked()){
                        results.count = listaValueRed.size();
                        results.values = listaValueRed;
                    }else{
                        results.count = listaValue.size();
                        results.values = listaValue;
                    }
                }

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if(rb_ip_externa.isChecked() || rb_ip_interna.isChecked()){

                    listaRed = (LinkedList<Configuracion_red>) results.values;
                }else{
                    lista = (LinkedList<InfoGeneral>) results.values;
                }
                notifyDataSetChanged();

            }
        }
    }
}