package com.example.appserver2.ui.ServidoresProd;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appserver2.R;
import com.example.appserver2.modelos.BDServidoresProds.AlmacenDatosApi;
import com.example.appserver2.modelos.BDServidoresProds.BDApi;
import com.example.appserver2.modelos.Entidades.ServidoresProd;
import com.example.appserver2.modelos.Entidades.apirest.ConstantesApi;
import com.example.appserver2.modelos.Entidades.apirest.IPlaceHolderApi;


import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServidorProdFragment extends Fragment {
     private ListView lv_listado_servidores_prod;
     public static AlmacenDatosApi almacenDatosApi;
     private LinkedList<ServidoresProd> servidoresProds = new LinkedList<>();
     private String dependencia = "", marca = "", modelo = "", n_serie = "", so = "", lic = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_servidor_prod, container, false);
        getServidoresProds();

        lv_listado_servidores_prod = view.findViewById(R.id.lv_listado_servidores_prod);
        adapterServidores adapter = new adapterServidores();
        lv_listado_servidores_prod.setAdapter(adapter);
        lv_listado_servidores_prod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "item: " +
                        servidoresProds.get(position).getDependencia().toString()
                        , Toast.LENGTH_SHORT).show();


                    final Dialog dialogSino = new Dialog(getActivity());
                    dialogSino.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogSino.setContentView(R.layout.alerta_sino);
                    dialogSino.setCanceledOnTouchOutside(false);
                    TextView tv_mensaje_sino = dialogSino.findViewById(R.id.tv_mensaje_sino);
                    tv_mensaje_sino.setText("Se registrara el servidor");

                    Button b_no = dialogSino.findViewById(R.id.b_no);
                    Button b_si = dialogSino.findViewById(R.id.b_si);


                    b_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogSino.dismiss();
                        }
                    });

                    dialogSino.show();

                    b_si.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dependencia = servidoresProds.get(position).getDependencia().toString();
                            marca = servidoresProds.get(position).getMarca().toString();
                            modelo = servidoresProds.get(position).getModelo().toString();
                            n_serie = servidoresProds.get(position).getN_serie().toString();
                            so = servidoresProds.get(position).getSo().toString();
                            lic = servidoresProds.get(position).getLic().toString();
                            almacenDatosApi = new BDApi(getActivity());
                            almacenDatosApi.guardarInformacionApi(dependencia, marca, modelo, n_serie, so, lic);

                            final Dialog dialog2 = new Dialog(getActivity());
                            dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog2.setContentView(R.layout.alerta_entendido);
                            dialog2.setCanceledOnTouchOutside(false);
                            ImageView iva_card = dialog2.findViewById(R.id.iva_card);
                            iva_card.setVisibility(View.GONE);
                            TextView tva_codigo = dialog2.findViewById(R.id.tva_codigo);
                            tva_codigo.setVisibility(View.GONE);
                            TextView tva_tipo_servidor = dialog2.findViewById(R.id.tva_tipo_servidor);
                            tva_tipo_servidor.setVisibility(View.GONE);
                            TextView tva_caracteristicas = dialog2.findViewById(R.id.tva_caracteristicas);
                            tva_caracteristicas.setText("¡Se registro con exito el servidor!");
                            TextView tva_ambiente = dialog2.findViewById(R.id.tva_ambiente);
                            tva_ambiente.setVisibility(View.GONE);
                            TextView tva_modelo = dialog2.findViewById(R.id.tva_modelo);
                            tva_modelo.setVisibility(View.GONE);
                            TextView tva_marca = dialog2.findViewById(R.id.tva_marca);
                            tva_marca.setVisibility(View.GONE);
                            TextView tva_funcion = dialog2.findViewById(R.id.tva_funcion);
                            tva_funcion.setVisibility(View.GONE);
                            Button b_entendido = dialog2.findViewById(R.id.b_entendido);

                            b_entendido.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog2.dismiss();
                                    dialogSino.dismiss();
                                }
                            });
                            dialog2.show();
                        }
                    });
                dialogSino.show();
            }
        });
        return view;
    }

    private void getServidoresProds(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPlaceHolderApi iPlaceHolderApi = retrofit.create(IPlaceHolderApi.class);
        Call<LinkedList<ServidoresProd>> call = iPlaceHolderApi.getServidoresProds();

        call.enqueue(new Callback<LinkedList<ServidoresProd>>() {
            @Override
            public void onResponse(Call<LinkedList<ServidoresProd>> call,
                                   Response<LinkedList<ServidoresProd>> response) {

                if (!response.isSuccessful()){
                    Log.i("Error: " , "No respuesta");
                    return;
                }

                servidoresProds = response.body();
                Toast.makeText(getActivity(), "Espere un momento, objetos de la lista: " + servidoresProds.size()
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LinkedList<ServidoresProd>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al obtener los datos\n" + t + "\n" +
                        call, Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* private void dataApi(){
        AdapterApiRest adapterApiRest = new AdapterApiRest();
        IEndPointsApiRest iEndPointsApiRest = adapterApiRest.conexionApiRest();
        Call<ServidoresProdResponseApiRest> respuestaCall  = iEndPointsApiRest.getContenido();
        respuestaCall.enqueue(new Callback<ServidoresProdResponseApiRest>() {
            @Override
            public void onResponse(Call<ServidoresProdResponseApiRest> call, Response<ServidoresProdResponseApiRest> response) {

                ServidoresProdResponseApiRest responseApiRest = response.body();

                servidoresProds = responseApiRest.getListaServidoresProd();

                Toast.makeText(getActivity(), servidoresProds.size() + "..", Toast.LENGTH_SHORT).show();
                for (ServidoresProd servidoresProd: servidoresProds
                     ) {
                    Toast.makeText(getActivity(), servidoresProd.getModelo(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServidoresProdResponseApiRest> call, Throwable t) {
                Toast.makeText(getActivity(), "Error al obtener los datos\n" + t + "\n" + call, Toast.LENGTH_SHORT).show();
            }
        });

    }*/

    public class adapterServidores extends BaseAdapter {

        @Override
        public int getCount() {
            return servidoresProds.size();
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
            tv_codigo.setText(servidoresProds.get(position).getN_serie().toString());
            tv_funcion.setText(servidoresProds.get(position).getModelo().toString());
            tv_ip_privada.setText(servidoresProds.get(position).getMarca().toString());

            return convertView;
        }
    }

}