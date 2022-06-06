package com.example.appserver2.modelos.Entidades;

public class Aplicaciones {

    private int codigo_inventario;
    private String nombre;
    private String version;
    private String fecha_instalacion;
    private String descripcion;

    public Aplicaciones() {
    }

    public Aplicaciones(int codigo_inventario, String nombre, String version, String fecha_instalacion, String descripcion) {
        this.codigo_inventario = codigo_inventario;
        this.nombre = nombre;
        this.version = version;
        this.fecha_instalacion = fecha_instalacion;
        this.descripcion = descripcion;
    }

    public int getCodigo_inventario() {
        return codigo_inventario;
    }

    public void setCodigo_inventario(int codigo_inventario) {
        this.codigo_inventario = codigo_inventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(String fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
