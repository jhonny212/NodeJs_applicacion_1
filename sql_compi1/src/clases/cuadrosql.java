/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author jhonny
 */
public class cuadrosql {

    private String valor, columna;
    private int lenght;

    public cuadrosql(String valor, String columna, int lenght) {
        this.valor = valor;
        this.columna = columna;
        this.lenght = lenght;
    }

    public String getValor() {
        return valor;
    }

    public String getColumna() {
        return columna;
    }

    public int getLenght() {
        return lenght;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
    

    public String condicion(String tipo,  String igualdad) {
        String validar = "false";
        int cant=0;
        try{cant=Integer.parseInt(igualdad);
        }catch(Exception e){
        }
        switch (tipo) {
            case ">":
                if(lenght>cant) validar ="true";
                break;
            case "<":
                 if(lenght<cant) validar ="true";
                break;
            case ">=":
                 if(lenght>=cant) validar ="true";
                break;
            case "<=":
                 if(lenght<=cant) validar ="true";
                break;
            case "=":
       
                 if(valor.equals(igualdad)) validar ="true";
                
                break;
            case "<>":
                 if(lenght!=cant) validar ="true";
                break;

        }
     
        return validar;
    }
      public static String condicion(String tipo,  String igualdad,String letra) {
        String validar = "false";
        int longi=letra.length();
        int cant=0;
        try{cant=Integer.parseInt(igualdad);}catch(Exception e){}
        switch (tipo) {
            case ">":
                if(longi>cant) validar ="true";
                break;
            case "<":
                 if(longi<cant) validar ="true";
                break;
            case ">=":
                 if(longi>=cant) validar ="true";
                break;
            case "<=":
                 if(longi<=cant) validar ="true";
                break;
            case "=":
                 if(letra.equals(igualdad)) validar ="true";
                
                break;
            case "<>":
                 if(longi!=cant) validar ="true";
                break;

        }
     
        return validar;
    }
}
