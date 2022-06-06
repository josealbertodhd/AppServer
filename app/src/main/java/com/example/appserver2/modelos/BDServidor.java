package com.example.appserver2.modelos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appserver2.modelos.Entidades.Aplicaciones;
import com.example.appserver2.modelos.Entidades.InfoGeneral;
import com.example.appserver2.modelos.Entidades.Configuracion_red;
import com.example.appserver2.modelos.Entidades.Mantenimientos;
import com.example.appserver2.modelos.Entidades.SistemaOperativo;
import com.example.appserver2.modelos.Entidades.Ubicacion;

import java.util.LinkedList;

public class BDServidor extends SQLiteOpenHelper implements AlmacenDatos {

    public BDServidor(@Nullable Context context) {
        super(context, "Servidores", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE info_general(" +
                "codigo INTEGER PRIMARY KEY," +
                "tipo TEXT," +
                "caracteristicas TEXT," +
                "ambiente TEXT," +
                "modelo TEXT," +
                "marca TEXT," +
                "funcion TEXT)");

        db.execSQL("CREATE TABLE configuracion_red(" +
                "codigo INTEGER," +
                "ip_privada TEXT," +
                "direccion_publica TEXT," +
                "nombre_red TEXT," +
                "dominio_red TEXT)");

        db.execSQL("CREATE TABLE sistema_operativo(" +
                "codigo INTEGER," +
                "nombre TEXT," +
                "version TEXT," +
                "fecha_instalacion TEXT," +
                "licencia TEXT)");

        db.execSQL("CREATE TABLE aplicaciones(" +
                "codigo INTEGER," +
                "nombre TEXT," +
                "version TEXT," +
                "fecha_instalacion TEXT," +
                "descripcion TEXT)");

        db.execSQL("CREATE TABLE ubicacion(" +
                "codigo INTEGER," +
                "area TEXT," +
                "responsable TEXT," +
                "fecha_instalacion TEXT)");

        db.execSQL("CREATE TABLE mantenimientos(" +
                "codigo INTEGER," +
                "tipo TEXT," +
                "realizado TEXT," +
                "fecha TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void guardarInformacionGeneral(int codigo, String tipo, String caracteristicas,
                                          String ambiente, String modelo, String marca, String
                                                      funcion) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO info_general VALUES (" +
                codigo +", '" +
                tipo +"', '" +
                caracteristicas + "','"+
                ambiente+"', '" +
                modelo +"', '"+
                marca +"', '"+
                funcion +"')");
    }

    @Override
    public void guardarConfiguracionRed(int codigo, String ip_privada, String direccion_publica,
                                        String nombre_Red, String dominio_Red) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO configuracion_red VALUES (" +
                codigo +", '" +
                ip_privada +"', '" +
                direccion_publica + "','"+
                nombre_Red +"', '" +
                dominio_Red +"')");

    }

    @Override
    public void guardarSistemaOperativo(int codigo, String nombre, String version,
                                        String fecha_instalacion, String licencia) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO sistema_operativo VALUES (" +
                codigo +", '" +
                nombre +"', '" +
                version + "','"+
                fecha_instalacion +"', '" +
                licencia +"')");
    }

    @Override
    public void guardarAplicaciones(int codigo, String nombre, String version,
                                    String fecha_instalacion, String descripcion) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO aplicaciones VALUES (" +
                codigo +", '" +
                nombre +"', '" +
                version + "','"+
                fecha_instalacion +"', '" +
                descripcion +"')");
    }

    @Override
    public void guardarUbicacion(int codigo, String area, String responsable,
                                 String fecha_instalacion) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO ubicacion VALUES (" +
                codigo +", '" +
                area +"', '" +
                responsable + "','"+
                fecha_instalacion +"')");
    }

    @Override
    public void guardarMantenimientos(int codigo, String tipo, String realizado,
                                      String fecha) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO mantenimientos VALUES (" +
                codigo +", '" +
                tipo +"', '" +
                realizado + "','"+
                fecha +"')");

    }

    @Override
    public void mostrarInformacionGeneral(LinkedList<InfoGeneral> lista_info_general) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM info_general", null);
        if(cursorLista.moveToFirst()){
            do{
                lista_info_general.add(new InfoGeneral(cursorLista.getInt(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3),
                        cursorLista.getString(4),
                        cursorLista.getString(5),
                        cursorLista.getString(6)));

            }while (cursorLista.moveToNext());
        }
    }

    @Override
    public void mostrarConfiguracionRed(LinkedList<Configuracion_red> lista_red) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM configuracion_red", null);
        if(cursorLista.moveToFirst()){
            do{
                lista_red.add(new Configuracion_red(cursorLista.getInt(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3),
                        cursorLista.getString(4)));

            }while (cursorLista.moveToNext());
        }
    }

    public void mostrarSistemaOperativo(LinkedList<SistemaOperativo> lista_sistema) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM sistema_operativo", null);
        if(cursorLista.moveToFirst()){
            do{
                lista_sistema.add(new SistemaOperativo(cursorLista.getInt(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3),
                        cursorLista.getString(4)));

            }while (cursorLista.moveToNext());
        }
    }

    public void mostrarAplicaciones(LinkedList<Aplicaciones> lista_aplicaciones) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM aplicaciones", null);
        if(cursorLista.moveToFirst()){
            do{
                lista_aplicaciones.add(new Aplicaciones(cursorLista.getInt(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3),
                        cursorLista.getString(4)));

            }while (cursorLista.moveToNext());
        }
    }

    public void mostrarUbicacion(LinkedList<Ubicacion> lista_ubicaciones) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM ubicacion", null);
        if(cursorLista.moveToFirst()){
            do{
                lista_ubicaciones.add(new Ubicacion(cursorLista.getInt(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3)));

            }while (cursorLista.moveToNext());
        }
    }

    public void mostrarMantenimientos(LinkedList<Mantenimientos> lista_mantenimientos) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorLista;
        cursorLista = db.rawQuery("SELECT * FROM mantenimientos", null);
        if(cursorLista.moveToFirst()){
            do{
                lista_mantenimientos.add(new Mantenimientos(cursorLista.getInt(0),
                        cursorLista.getString(1),
                        cursorLista.getString(2),
                        cursorLista.getString(3)));

            }while (cursorLista.moveToNext());
        }
    }


    public void modificarInformacionGeneral(int codigo, String tipo, String caracteristicas,
                                          String ambiente, String modelo, String marca, String
                                                  funcion) {

        SQLiteDatabase db = getWritableDatabase();

        try{
            db.execSQL("UPDATE info_general SET codigo = '"+ codigo + "', tipo = '"+ tipo +"' , " +
                    "caracteristicas = '"+ caracteristicas +"', ambiente = '"+ ambiente + "', modelo = '"+
                    modelo+"'," + "marca = '"+ marca +"', funcion = '"+ funcion +"' WHERE codigo = '" +
                    codigo +"'");

        }catch (Exception e){
            e.toString();
        } finally {
            db.close();
        }
    }

    public void modificarConfiguracionRed(int codigo, String ip_privada, String direccion_publica,
                                        String nombre_Red, String dominio_Red) {

        SQLiteDatabase db = getWritableDatabase();

        try{
            db.execSQL("UPDATE configuracion_red SET codigo = '"+ codigo + "', ip_privada = '"+ ip_privada +"' , " +
                    "direccion_publica = '"+ direccion_publica +"', nombre_red = '"+ nombre_Red + "', dominio_red = '"+
                    dominio_Red +"' WHERE codigo = '" +
                    codigo +"'");

        }catch (Exception e){
            e.toString();
        } finally {
            db.close();
        }

    }

    public void modificarSistemaOperativo(int codigo, String nombre, String version,
                                        String fecha_instalacion, String licencia) {

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("UPDATE sistema_operativo SET codigo = '"+ codigo + "', nombre = '"+ nombre +"' , " +
                    "version = '"+ version +"', fecha_instalacion = '"+ fecha_instalacion + "', licencia = '"+
                    licencia +"' WHERE codigo = '" +
                    codigo +"'");

        }catch (Exception e){
            e.toString();
        } finally {
            db.close();
        }
    }

    public void modificarAplicaciones(int codigo, String nombre, String version,
                                    String fecha_instalacion, String descripcion) {

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("UPDATE aplicaciones SET codigo = '"+ codigo + "', nombre = '"+ nombre +"' , " +
                    "version = '"+ version +"', fecha_instalacion = '"+ fecha_instalacion + "', descripcion = '"+
                    descripcion +"' WHERE codigo = '" +
                    codigo + "'");

        }catch (Exception e){
            e.toString();
        } finally {
            db.close();
        }

    }

    public void modificarUbicacion(int codigo, String area, String responsable,
                                 String fecha_instalacion) {

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("UPDATE ubicacion SET codigo = '"+ codigo + "', area = '"+ area +"' , " +
                    "responsable = '"+ responsable +"', fecha_instalacion = '"+ fecha_instalacion +
                    "' WHERE codigo = '" +
                    codigo +"'");

        }catch (Exception e){
            e.toString();
        } finally {
            db.close();
        }
    }

    public void modificarMantenimientos(int codigo, String tipo, String realizado,
                                      String fecha) {

        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("UPDATE mantenimientos SET codigo = '"+ codigo + "', tipo = '"+ tipo +"' , " +
                    "realizado = '"+ realizado +"', fecha = '"+ fecha +
                    "' WHERE codigo = '" +
                    codigo +"'");

        }catch (Exception e){
            e.toString();
        } finally {
            db.close();
        }

    }



}
