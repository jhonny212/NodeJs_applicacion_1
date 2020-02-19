/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class sql {
    private String consulta, path;
    private ArrayList<condiciones> cond;
    private ArrayList<String> columnas;
    private ArrayList<String> valores;
    private ArrayList<asignaciones> asig;

    public ArrayList<asignaciones> getAsig() {
        return asig;
    }
    
    public String getConsulta() {
        return consulta;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<condiciones> getCond() {
        return cond;
    }

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public ArrayList<String> getValores() {
        return valores;
    }

    public sql(String consulta, String path, ArrayList<condiciones> cond, ArrayList<String> columnas, ArrayList<String> valores
    ,ArrayList<asignaciones> asig) {
        this.consulta = consulta;
        this.path = path;
        this.cond = cond;
        this.columnas = columnas;
        this.valores = valores;
        this.asig=asig;
    }
    public sql(){
    }
    
   
}
