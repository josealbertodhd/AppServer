package com.example.appserver2.modelos;

import com.example.appserver2.modelos.Entidades.Aplicaciones;
import com.example.appserver2.modelos.Entidades.InfoGeneral;
import com.example.appserver2.modelos.Entidades.Configuracion_red;
import com.example.appserver2.modelos.Entidades.Mantenimientos;
import com.example.appserver2.modelos.Entidades.SistemaOperativo;
import com.example.appserver2.modelos.Entidades.Ubicacion;

import java.util.LinkedList;

public interface AlmacenDatos {

    void guardarInformacionGeneral(int codigo, String tipo, String caracteristicas, String ambiente,
                                   String modelo, String marca, String funcion);

    void guardarConfiguracionRed(int codigo, String ip_privada, String direccion_publica, String nombre_Red,
                                 String dominio_Red);

    void guardarSistemaOperativo(int codigo, String nombre, String version, String fecha_instalacion,
                                 String licencia);

    void guardarAplicaciones(int codigo, String nombre, String version, String fecha_instalacion,
                                 String descripcion);

    void guardarUbicacion(int codigo, String area, String responsable, String fecha_instalacion);

    void guardarMantenimientos(int codigo, String tipo, String realizado, String fecha);

    void mostrarInformacionGeneral(LinkedList<InfoGeneral> lista_info_general);

    void mostrarConfiguracionRed(LinkedList<Configuracion_red> lista_red);

    void modificarInformacionGeneral(int codigo, String tipo, String caracteristicas,
                                            String ambiente, String modelo, String marca, String
                                                    funcion);

    void modificarConfiguracionRed(int codigo, String ip_privada, String direccion_publica,
                                          String nombre_Red, String dominio_Red) ;

    void modificarSistemaOperativo(int codigo, String nombre, String version,
                                          String fecha_instalacion, String licencia);

    void modificarAplicaciones(int codigo, String nombre, String version,
                                      String fecha_instalacion, String descripcion);

    void modificarUbicacion(int codigo, String area, String responsable,
                                   String fecha_instalacion);

    void modificarMantenimientos(int codigo, String tipo, String realizado,
                                        String fecha);

    void mostrarSistemaOperativo(LinkedList<SistemaOperativo> lista_sistema);

    void mostrarAplicaciones(LinkedList<Aplicaciones> lista_aplicaciones);

    void mostrarUbicacion(LinkedList<Ubicacion> lista_ubicaciones);

    void mostrarMantenimientos(LinkedList<Mantenimientos> lista_mantenimientos);

}
