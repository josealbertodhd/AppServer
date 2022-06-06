package com.example.appserver2.modelos.Entidades;

public class Configuracion_red {
    private int codigo_inventario;
    private String ip_privada;
    private String direccion_publica;
    private String nombre_red;
    private String dominio_red;

    public Configuracion_red(int codigo_inventario, String ip_privada,
                             String direccion_publica, String nombre_red,
                             String dominio_red) {

        this.codigo_inventario = codigo_inventario;
        this.ip_privada = ip_privada;
        this.direccion_publica = direccion_publica;
        this.nombre_red = nombre_red;
        this.dominio_red = dominio_red;
    }

    public Configuracion_red() {
    }

    public int getCodigo_inventario() {
        return codigo_inventario;
    }

    public void setCodigo_inventario(int codigo_inventario) {
        this.codigo_inventario = codigo_inventario;
    }

    public String getIp_privada() {
        return ip_privada;
    }

    public void setIp_privada(String ip_privada) {
        this.ip_privada = ip_privada;
    }

    public String getDireccion_publica() {
        return direccion_publica;
    }

    public void setDireccion_publica(String direccion_publica) {
        this.direccion_publica = direccion_publica;
    }

    public String getNombre_red() {
        return nombre_red;
    }

    public void setNombre_red(String nombre_red) {
        this.nombre_red = nombre_red;
    }

    public String getDominio_red() {
        return dominio_red;
    }

    public void setDominio_red(String dominio_red) {
        this.dominio_red = dominio_red;
    }
}
