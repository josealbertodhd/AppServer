package com.example.appserver2.modelos.Entidades;

public class Mantenimientos {

    private int codigo_inventario;
    private String tipo;
    private String realizado;
    private String fecha;

    public Mantenimientos() {
    }

    public Mantenimientos(int codigo_inventario, String tipo, String realizado, String fecha) {
        this.codigo_inventario = codigo_inventario;
        this.tipo = tipo;
        this.realizado = realizado;
        this.fecha = fecha;
    }

    public int getCodigo_inventario() {
        return codigo_inventario;
    }

    public void setCodigo_inventario(int codigo_inventario) {
        this.codigo_inventario = codigo_inventario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
