package com.example.appserver2.modelos.Entidades;

public class InfoGeneral {
    private int codigo_inventario;
    private String tipo_servidor;
    private String caracteristicas_servidor;
    private String ambiente_servidor;
    private String modelo_servidor;
    private String marca_servidor;
    private String funcion_servidor;

    public InfoGeneral(int codigo_inventario, String tipo_servidor,
                       String caracteristicas_servidor, String ambiente_servidor,
                       String modelo_servidor, String marca_servidor,
                       String funcion_servidor) {

        this.codigo_inventario = codigo_inventario;
        this.tipo_servidor = tipo_servidor;
        this.caracteristicas_servidor = caracteristicas_servidor;
        this.ambiente_servidor = ambiente_servidor;
        this.modelo_servidor = modelo_servidor;
        this.marca_servidor = marca_servidor;
        this.funcion_servidor = funcion_servidor;
    }

    public InfoGeneral() {
    }

    public int getCodigo_inventario() {
        return codigo_inventario;
    }

    public void setCodigo_inventario(int codigo_inventario) {
        this.codigo_inventario = codigo_inventario;
    }

    public String getTipo_servidor() {
        return tipo_servidor;
    }

    public void setTipo_servidor(String tipo_servidor) {
        this.tipo_servidor = tipo_servidor;
    }

    public String getCaracteristicas_servidor() {
        return caracteristicas_servidor;
    }

    public void setCaracteristicas_servidor(String caracteristicas_servidor) {
        this.caracteristicas_servidor = caracteristicas_servidor;
    }

    public String getAmbiente_servidor() {
        return ambiente_servidor;
    }

    public void setAmbiente_servidor(String ambiente_servidor) {
        this.ambiente_servidor = ambiente_servidor;
    }

    public String getModelo_servidor() {
        return modelo_servidor;
    }

    public void setModelo_servidor(String modelo_servidor) {
        this.modelo_servidor = modelo_servidor;
    }

    public String getMarca_servidor() {
        return marca_servidor;
    }

    public void setMarca_servidor(String marca_servidor) {
        this.marca_servidor = marca_servidor;
    }

    public String getFuncion_servidor() {
        return funcion_servidor;
    }

    public void setFuncion_servidor(String funcion_servidor) {
        this.funcion_servidor = funcion_servidor;
    }

    @Override
    public String toString() {
        return "InfoGeneral{" +
                "codigo_inventario=" + codigo_inventario +
                ", tipo_servidor='" + tipo_servidor + '\'' +
                ", caracteristicas_servidor='" + caracteristicas_servidor + '\'' +
                ", ambiente_servidor='" + ambiente_servidor + '\'' +
                ", modelo_servidor='" + modelo_servidor + '\'' +
                ", marca_servidor='" + marca_servidor + '\'' +
                ", funcion_servidor='" + funcion_servidor + '\'' +
                '}';
    }
}
