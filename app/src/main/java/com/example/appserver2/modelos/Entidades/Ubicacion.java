package com.example.appserver2.modelos.Entidades;

public class Ubicacion {

    private int codigo_inventario;
    private String area;
    private String responsable;
    private String fecha_instalacion;

    public Ubicacion() {
    }

    public Ubicacion(int codigo_inventario, String area, String responsable, String fecha_instalacion) {
        this.codigo_inventario = codigo_inventario;
        this.area = area;
        this.responsable = responsable;
        this.fecha_instalacion = fecha_instalacion;
    }

    public int getCodigo_inventario() {
        return codigo_inventario;
    }

    public void setCodigo_inventario(int codigo_inventario) {
        this.codigo_inventario = codigo_inventario;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFecha_instalacion() {
        return fecha_instalacion;
    }

    public void setFecha_instalacion(String fecha_instalacion) {
        this.fecha_instalacion = fecha_instalacion;
    }
}
