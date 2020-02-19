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
public class delete extends sql {

    public delete(String consulta, String path, ArrayList<condiciones> cond, ArrayList<String> columnas, ArrayList<String> valores, ArrayList<asignaciones> asig) {
        super(consulta, path, cond, columnas, valores, asig);
    }

    public delete() {
    }

    public String eliminar(sql sql) {
        String msj = "No se pudo eliminar ";
        String cod = "";
        String type = "";
        tabla table = new tabla(sql.getPath());
        int count = 0;
        if (table.llenarTabla()) {
            ArrayList<String[]> list = new ArrayList();
            String[] index = new String[table.getValues().size()];

            if (sql.getCond().isEmpty()) {
                String array[] = new String[table.getColumnas().size()];
                for (int i = 0; i < table.getColumnas().size(); i++) {
                    array[i] = table.getColumnas().get(i);

                }
                CSV.escribir(table.getPaths(), array);
                msj="Archivo eliminado";
            } else {

                for (int k = 0; k < table.getValues().size(); k++) {
                    String array[] = table.getValues().get(k);
                    count = 0;
                    cod="";
                        for (int i = 0; i < table.getColumnas().size(); i++) {
                            for (int j = 0; j < sql.getCond().size(); j++) {
                                
                                if (table.getColumnas().get(i).equals(sql.getCond().get(j).getColumna())) {
                                    System.out.println(table.getColumnas().get(i)+":");
                                    cod += cuadrosql.condicion(sql.getCond().get(j).getOp_relacionar(), sql.getCond().get(j).getCondicion(), array[i])+" ";
                                    type = sql.getCond().get(j).getType();
                                }
                            }
                        }
                System.out.println(type+":"+cod);
                    
                                         if (type.equals("Y")) {
                                        if (!cod.contains("false")) {
                                            index[k] = String.valueOf(k);
                                            msj="Eliminado";
                                            count++;
                                        }

                                    } else {
                                        if(type.equals("OR")){
                                        if (cod.contains("true")) {
                                            index[k] = String.valueOf(k);
                                            msj="Eliminado";
                                            
                                            count++;
                                        }
                                        }else{
                                        if (cod.contains("true")) {
                                            index[k] = String.valueOf(k);
                                            msj="Eliminado";
                                            
                                            count++;
                                        }
                                        }
                                    }
                }
                for (int i = 0; i < index.length; i++) {
                    try {
                        index[i].isEmpty();
                        msj="Eliminado";
                    } catch (NullPointerException e) {
                        list.add(table.getValues().get(i));
                    }
                }
                CSV.escribir(table.getPaths(), list);

            }
        } else {
            msj = "Verifique la ruta";
        }

        return msj;
    }

}
