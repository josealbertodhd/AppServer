package com.example.appserver2.ui.ListadoServidores;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.appserver2.R;
import com.example.appserver2.modelos.AlmacenDatos;
import com.example.appserver2.modelos.BDServidor;
import com.example.appserver2.modelos.Entidades.InfoGeneral;
import com.example.appserver2.modelos.Entidades.Configuracion_red;
import com.example.appserver2.ui.ModificarServidor.ModificarFragment;

import java.util.LinkedList;


public class ListadoServidoresFragment extends Fragment {

    private ListView lv_listado;
    private Fragment fragment_modificar;
    private FragmentTransaction fragmentTransaction;
    public static AlmacenDatos almacenDatos;
    private LinkedList<InfoGeneral> listado_servidores = new LinkedList<>();
    private LinkedList<Configuracion_red> listado_servidores_red = new LinkedList<>();
    private ImageView iv_card;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado_servidores, container, false);

        almacenDatos = new BDServidor(getActivity());
        almacenDatos.mostrarInformacionGeneral(listado_servidores);
        almacenDatos.mostrarConfiguracionRed(listado_servidores_red);

       lv_listado = view.findViewById(R.id.lv_listado);
       myAdapter adapter = new myAdapter();
       lv_listado.setAdapter(adapter);
       fragment_modificar = new ModificarFragment();

       lv_listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View views, int position, long id) {

               PopupMenu popupMenu = new PopupMenu(getActivity(), views);
               popupMenu.getMenuInflater().inflate(R.menu.popuplistado, popupMenu.getMenu());
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.pop_modificar:
                               Bundle bundle = new Bundle();
                               bundle.putInt("position", position);
                               fragment_modificar.setArguments(bundle);
                               fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                               fragmentTransaction.replace(R.id.nav_listado_servidores, fragment_modificar).commit();
                               fragmentTransaction.setReorderingAllowed(true);
                               fragmentTransaction.addToBackStack(null);
                               lv_listado.setVisibility(View.GONE);

                               break;
                       }
                       return false;
                   }
               });
               popupMenu.show();

           }
       });

       return view;

    }

    public class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listado_servidores.size();
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
            iv_card = convertView.findViewById(R.id.iv_card);
            tv_codigo = convertView.findViewById(R.id.tv_codigo_servidor);
            tv_funcion = convertView.findViewById(R.id.tv_funcion);
            tv_ip_privada = convertView.findViewById(R.id.tv_ip_privada);
            tv_codigo.setText(Integer.toString(listado_servidores.get(position).getCodigo_inventario()).toString());
            tv_funcion.setText(listado_servidores.get(position).getFuncion_servidor().toString());
            tv_ip_privada.setText(listado_servidores_red.get(position).getIp_privada().toString());

            if(listado_servidores.get(position).getFuncion_servidor().toString().equals("DNS")){
                iv_card.setImageResource(R.drawable.ic_dns);
            }else if(listado_servidores.get(position).getFuncion_servidor().toString().equals("Web")){
                iv_card.setImageResource(R.drawable.ic_web);
            }else if (listado_servidores.get(position).getFuncion_servidor().toString().equals("BD")){
                iv_card.setImageResource(R.drawable.ic_database);
            }else if (listado_servidores.get(position).getFuncion_servidor().toString().equals("Directorio Activo")){
                iv_card.setImageResource(R.drawable.ic_desktop);
            }

            return convertView;
        }
    }
}