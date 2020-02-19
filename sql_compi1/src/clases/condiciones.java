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
public class condiciones {
    private String columna, condicion, op_relacionar,type;

    public String getColumna() {
        return columna;
    }

    public String getCondicion() {
        return condicion;
    }

    public String getOp_relacionar() {
        return op_relacionar;
    }


    public condiciones(String columna, String condicion, String op_relacionar,String type) {
        this.columna = columna;
        this.condicion = condicion;
        this.op_relacionar = op_relacionar;
        this.type=type;
    }

    public String getType() {
        return type;
    }
    
    
    
}
