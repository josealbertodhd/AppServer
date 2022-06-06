package com.example.appserver2.ui.GestionarServidor;

import static android.text.InputType.TYPE_CLASS_NUMBER;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appserver2.R;
import com.example.appserver2.modelos.AlmacenDatos;
import com.example.appserver2.modelos.BDServidor;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.LinkedList;

public class GestionarServidorFragment extends Fragment {
    View view;
    public static AlmacenDatos almacenDatos;
    private LinkedList<String[]> listaAplicaciones = new LinkedList<>();
    private String[] arregloAplicaciones = new String[4];
    private int contadorVista = 0;
    private TextView tv_titulo;
    private EditText et_date, et_codigo,et_string1, et_string2, et_string3, et_string4;
    private Spinner spn_sppiner1, spn_sppiner2, spn_sppiner3;
    private Button btn_guardar, b_agregar;
    private  ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;
    private int codigo_inventario;
    private String tipo_servidor = "", caracteristicas_servidor = "", ambiente_servidor = "",
    modelo_servidor="", marca_servidor="", funcion_servidor= "", ip_privada = "", direccion_publica = "", nombre_red = "",
            dominio_red="", nombre_sistema = "", version_sistema = "", fecha_sistema= "",
            licencia="", nombre_aplicaciones = "", version_aplicaciones = "", fecha_aplicacion= "",
            descripcion="",  area_ubicacion = "", responsable= "", fecha_ubicacion= "",
            tipo_mantenimiento = "", realizado_por = "", fecha_mantenimiento= "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gestionar_servidor, container, false);

        tv_titulo = view.findViewById(R.id.tv_titulo);
        et_codigo = view.findViewById(R.id.et_codigo);
        spn_sppiner1 = view.findViewById(R.id.spn_sppiner1);
        et_string1 = view.findViewById(R.id.et_string1);
        spn_sppiner2 = view.findViewById(R.id.spn_sppiner2);
        et_string2 = view.findViewById(R.id.et_string2);
        et_string3 = view.findViewById(R.id.et_string3);
        et_string4 = view.findViewById(R.id.et_string4);
        spn_sppiner3 = view.findViewById(R.id.spn_sppiner3);
        et_date = view.findViewById(R.id.et_date);
        btn_guardar = view.findViewById(R.id.btn_guardar);
        b_agregar = view.findViewById(R.id.b_agregar);
        et_date.setVisibility(View.GONE);
        et_string4.setVisibility(View.GONE);
        b_agregar.setVisibility(View.GONE);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        et_date.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.tipo_servidor,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn_sppiner1.setAdapter(adapter1);

        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.ambiente_servidor,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn_sppiner2.setAdapter(adapter2);

        adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.funcion_servidor,
                android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn_sppiner3.setAdapter(adapter3);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contadorVista++;

                if(contadorVista == 1){

                    //Si existe algun dato que no se haya digitado quedate en la misma vista
                    if((et_codigo.getText().toString().equals("")) || (et_string1.getText().toString().equals(""))
                            || (et_string2.getText().toString().equals("")) || (et_string3.getText().toString().equals(""))
                            || (spn_sppiner1.getSelectedItem().toString().equals("Tipo"))
                            || (spn_sppiner2.getSelectedItem().toString().equals("Ambiente"))
                            || (spn_sppiner1.getSelectedItem().toString().equals("Funcion"))){

                        Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                Toast.LENGTH_SHORT).show();
                        contadorVista--;

                    }else{
                        //Se guarda la informacion en las varibles
                        codigo_inventario = Integer.parseInt(et_codigo.getText().toString());
                        tipo_servidor = spn_sppiner1.getSelectedItem().toString();
                        caracteristicas_servidor = et_string1.getText().toString();
                        ambiente_servidor = spn_sppiner2.getSelectedItem().toString();
                        modelo_servidor = et_string2.getText().toString();
                        marca_servidor = et_string3.getText().toString();
                        funcion_servidor = spn_sppiner3.getSelectedItem().toString();

                        //Se configuran los EditText para digitar los items de configuracion de red
                        tv_titulo.setText("Configuracion de Red");
                        et_codigo.setVisibility(View.GONE);

                        et_string1.setText("");
                        et_string1.setHint("IP Privada");
                        et_string2.setText("");
                        et_string2.setHint("Direccion Publica");
                        et_string3.setText("");
                        et_string3.setHint("Nombre de Red");
                        et_string4.setVisibility(View.VISIBLE);
                        et_string4.setText("");
                        et_string4.setHint("Dominio de Red");
                        spn_sppiner1.setVisibility(View.GONE);
                        spn_sppiner2.setVisibility(View.GONE);
                        spn_sppiner3.setVisibility(View.GONE);
                    }

                }else if(contadorVista == 2){

                    if((et_string1.getText().toString().equals("")) || (et_string2.getText().toString().equals(""))
                       || (et_string3.getText().toString().equals("")) || (et_string4.getText().toString().equals(""))){

                        Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                Toast.LENGTH_SHORT).show();
                        contadorVista--;

                    }else{
                        ip_privada = et_string1.getText().toString();
                        direccion_publica = et_string2.getText().toString();
                        nombre_red = et_string3.getText().toString();
                        dominio_red = et_string4.getText().toString();

                        tv_titulo.setText("Sistema Operativo");
                        et_string1.setText("");
                        et_string1.setHint("Nombre");
                        et_string2.setText("");
                        et_string2.setHint("Version");
                        et_string3.setVisibility(View.GONE);
                        et_string4.setVisibility(View.GONE);
                        spn_sppiner3.setVisibility(View.VISIBLE);
                        adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.licencia_servidor,
                                android.R.layout.simple_spinner_item);
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spn_sppiner3.setAdapter(adapter3);
                        et_date.setVisibility(View.VISIBLE);
                        et_date.setText("");
                        et_date.setHint("Fecha de Instalacion");
                    }

                }else if(contadorVista == 3){

                    if((et_string1.getText().toString().equals("")) || (et_string2.getText().toString().equals("")) ||
                            (spn_sppiner3.getSelectedItem().toString().equals("Licencia")) ||
                            (et_date.getText().toString().equals(""))){

                        Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                Toast.LENGTH_SHORT).show();
                        contadorVista--;

                    }else{
                        nombre_sistema = et_string1.getText().toString();
                        version_sistema = et_string2.getText().toString();
                        licencia = spn_sppiner3.getSelectedItem().toString();
                        fecha_sistema = et_date.getText().toString();

                        tv_titulo.setText("Aplicaciones");
                        et_string1.setText("");
                        et_string2.setText("");
                        et_string3.setVisibility(View.VISIBLE);
                        et_string3.setText("");
                        et_string3.setHint("Descripcion");
                        spn_sppiner3.setVisibility(View.GONE);
                        b_agregar.setVisibility(View.VISIBLE);
                        b_agregar.setText("Agregar");
                        et_date.setText("");

                        b_agregar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if((et_string1.getText().toString().equals("")) || (et_string2.getText().toString().equals("")) ||
                                        (et_string3.getText().toString().equals("")) || (et_date.getText().toString().equals(""))){

                                    Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                            Toast.LENGTH_SHORT).show();

                                }else{

                                    arregloAplicaciones[0] = et_string1.getText().toString();
                                    arregloAplicaciones[1] = et_string2.getText().toString();
                                    arregloAplicaciones[2] = et_string3.getText().toString();
                                    arregloAplicaciones[3] = et_date.getText().toString();

                                    listaAplicaciones.add(arregloAplicaciones);
                                    arregloAplicaciones = new String[4];

                                    et_string1.setText("");
                                    et_string2.setText("");
                                    et_string3.setText("");
                                    et_string3.setHint("Descripcion");
                                    et_date.setText("");
                                    b_agregar.setText("Seguir agregando");

                                }
                            }
                        });
                    }

                }else if(contadorVista == 4){

                    if((et_string1.getText().toString().equals("")) || (et_string2.getText().toString().equals("")) ||
                            (et_string3.getText().toString().equals("")) || (et_date.getText().toString().equals(""))){

                        Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                Toast.LENGTH_SHORT).show();
                        contadorVista--;

                    }else {
                        nombre_aplicaciones = et_string1.getText().toString();
                        version_aplicaciones = et_string2.getText().toString();
                        descripcion = et_string3.getText().toString();
                        fecha_aplicacion = et_date.getText().toString();


                        arregloAplicaciones[0] = et_string1.getText().toString();
                        arregloAplicaciones[1] = et_string2.getText().toString();
                        arregloAplicaciones[2] = et_string3.getText().toString();
                        arregloAplicaciones[3] = et_date.getText().toString();

                        listaAplicaciones.add(arregloAplicaciones);

                        b_agregar.setVisibility(View.GONE);
                        tv_titulo.setText("Ubicacion");
                        et_string1.setText("");
                        et_string2.setText("");
                        et_string1.setHint("Area");
                        et_string2.setHint("Responsable");
                        et_string3.setVisibility(View.GONE);
                        et_date.setText("");
                    }

                }else if(contadorVista == 5){

                    if((et_string1.getText().toString().equals("")) || (et_string2.getText().toString().equals(""))
                            || (et_date.getText().toString().equals(""))){

                        Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                Toast.LENGTH_SHORT).show();
                        contadorVista--;

                    }else{
                        area_ubicacion = et_string1.getText().toString();
                        responsable = et_string2.getText().toString();
                        fecha_ubicacion = et_date.getText().toString();

                        tv_titulo.setText("Mantenimientos");
                        et_string1.setVisibility(View.GONE);
                        et_string2.setVisibility(View.GONE);
                        spn_sppiner1.setVisibility(View.VISIBLE);
                        adapter1 = ArrayAdapter.createFromResource(getActivity(),
                                R.array.tipo_mantenimiento, android.R.layout.simple_spinner_item);

                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spn_sppiner1.setAdapter(adapter1);
                        spn_sppiner2.setVisibility(View.VISIBLE);
                        adapter2 = ArrayAdapter.createFromResource(getActivity(),
                                R.array.realizado_mantenimiento, android.R.layout.simple_spinner_item);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spn_sppiner2.setAdapter(adapter2);
                        et_date.setText("");
                        et_date.setHint("Fecha");
                        btn_guardar.setText("Finalizar");
                    }

                }else{

                    if((spn_sppiner1.getSelectedItem().toString().equals("Tipo de Mantenimiento")) ||
                            (spn_sppiner2.getSelectedItem().toString().equals("Realizado por")) ||
                             et_date.getText().toString().equals("")){

                        Toast.makeText(getActivity(), "Digite valores en todos los campos",
                                Toast.LENGTH_SHORT).show();
                        contadorVista--;

                    }else{
                        tipo_mantenimiento = spn_sppiner1.getSelectedItem().toString();
                        realizado_por = spn_sppiner2.getSelectedItem().toString();
                        fecha_mantenimiento = et_date.getText().toString();

                        almacenDatos = new BDServidor(getActivity());
                        almacenDatos.guardarInformacionGeneral(codigo_inventario, tipo_servidor,
                                caracteristicas_servidor, ambiente_servidor, modelo_servidor, marca_servidor,
                                funcion_servidor);
                        almacenDatos.guardarConfiguracionRed(codigo_inventario, ip_privada, direccion_publica,
                                nombre_red, dominio_red);
                        almacenDatos.guardarSistemaOperativo(codigo_inventario, nombre_sistema, version_sistema,
                                fecha_sistema, licencia);

                        for (int i=0 ; i < listaAplicaciones.size(); i++){

                            arregloAplicaciones = new String[4];
                            arregloAplicaciones = listaAplicaciones.get(i);

                           almacenDatos.guardarAplicaciones(codigo_inventario, arregloAplicaciones[0].toString(),
                                    arregloAplicaciones[1].toString(), arregloAplicaciones[3].toString(),
                                    arregloAplicaciones[2].toString()
                                    );

                        }

                        almacenDatos.guardarUbicacion(codigo_inventario, area_ubicacion, responsable, fecha_ubicacion);
                        almacenDatos.guardarMantenimientos(codigo_inventario, tipo_mantenimiento, realizado_por,
                                fecha_mantenimiento);

                        Toast.makeText(getActivity(), "Informacion guardada con Exito", Toast.LENGTH_SHORT).show();

                        tv_titulo.setText("Informacion General");
                        et_codigo.setVisibility(View.VISIBLE);
                        et_codigo.setText("");
                        et_codigo.setHint("Digite Codigo");
                        et_string1.setVisibility(View.VISIBLE);
                        et_string1.setText("");
                        et_string1.setHint("Caracteristicas");
                        et_string2.setVisibility(View.VISIBLE);
                        et_date.setText("");
                        et_string2.setText("");
                        et_string2.setHint("Modelo");
                        et_string3.setVisibility(View.VISIBLE);
                        et_string3.setText("");
                        et_string3.setHint("Marca");
                        adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.tipo_servidor,
                                android.R.layout.simple_spinner_item);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spn_sppiner1.setAdapter(adapter1);

                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.ambiente_servidor,
                                android.R.layout.simple_spinner_item);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spn_sppiner2.setAdapter(adapter2);
                        spn_sppiner3.setVisibility(View.VISIBLE);
                        adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.funcion_servidor,
                                android.R.layout.simple_spinner_item);
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spn_sppiner3.setAdapter(adapter3);
                        et_date.setVisibility(View.GONE);
                        et_string4.setVisibility(View.GONE);
                        btn_guardar.setText("Siguiente");
                        listaAplicaciones = new LinkedList<>();
                        contadorVista = 0;
                    }

                }
            }
        });

        return view;
    }

}