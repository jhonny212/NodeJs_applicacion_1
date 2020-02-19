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
public class select extends sql {

    private ArrayList<String[]> values;
    private ArrayList<String> c;
    private String txto, colum;

    public String getTxto() {
        return txto;
    }

    public ArrayList<String[]> getValues() {
        return values;
    }

    public ArrayList<String> getC() {
        return c;
    }

    public select(String consulta, String path, ArrayList<condiciones> cond, ArrayList<String> columnas, ArrayList<String> valores, ArrayList<asignaciones> asig) {
        super(consulta, path, cond, columnas, valores, asig);
    }

    public select() {
        values = new ArrayList();
        c = new ArrayList();
    }

    public void solicitarConsulta(sql sql) {
        txto = "";
        int count = 0;
        String cond = "";
        String letter = "";
        String msj = "";
        tabla tmp = new tabla(sql.getPath());
        String type = "";
        int x = 0;
        if (tmp.llenarTabla()) {
            if (sql.getColumnas().get(0).equals("*")) {
                c = tmp.getColumnas();
                for (int l = 0; l < tmp.getValores().size(); l++) {
                    ArrayList<cuadrosql> lista = tmp.getValores().get(l);
                    letter = "";
                    cond = "";
                    type = "";
                    String datos[] = new String[lista.size()];
                    for (int i = 0; i < lista.size(); i++) {
                        for (int j = 0; j < tmp.getColumnas().size(); j++) {
                            if (lista.get(i).getColumna().equals(tmp.getColumnas().get(j))) {
                                datos[i] = lista.get(i).getValor();
                                letter += lista.get(i).getColumna() + ":" + datos[i] + " ";
                                count++;
                                if (sql.getCond().isEmpty()) {
                                    cond = "true";
                                } else {
                                    for (int k = 0; k < sql.getCond().size(); k++) {
                                        if (sql.getCond().get(k).getColumna().equals(tmp.getColumnas().get(j))) {
                                            type = sql.getCond().get(k).getType();
                                            cond += lista.get(i).condicion(sql.getCond().get(k).getOp_relacionar(), sql.getCond().get(k).getCondicion()) + " ";
                                        }

                                    }
                                }
                            }
                        }

                    }

                    if (type.equals("Y")) {

                        if (!cond.contains("false")) {
                            values.add(datos);

                        }
                    } else {
                        if (type.equals("OR")) {
                            if (cond.contains("true")) {
                                values.add(datos);

                            }

                        } else {
                            if (cond.contains("true")) {
                                values.add(datos);

                            }
                        }
                    }

                }
            } else {
                c = sql.getColumnas();
                for (int l = 0; l < tmp.getValores().size(); l++) {
                    ArrayList<cuadrosql> lista = tmp.getValores().get(l);
                    letter = "";
                    cond = "";
                    count = 0;
                    type = "";
                    String datos[] = new String[lista.size()];

                    for (int i = 0; i < lista.size(); i++) {
                        for (int j = 0; j < sql.getColumnas().size(); j++) {
                            if (lista.get(i).getColumna().equals(sql.getColumnas().get(j))) {
                                datos[i] = lista.get(i).getValor();
                                letter += lista.get(i).getValor() + ",";
                                count++;
                               /* if (sql.getCond().isEmpty()) {
                                    cond = "true";
                                } else {
                                    for (int k = 0; k < sql.getCond().size(); k++) {
                                        if (sql.getCond().get(k).getColumna().equals(sql.getColumnas().get(j))) {
                                            type = sql.getCond().get(k).getType();
                                            cond += lista.get(i).condicion(sql.getCond().get(k).getOp_relacionar(), sql.getCond().get(k).getCondicion()) + " ";
                                        }

                                    }
                                }*/

                            }
                            if (sql.getCond().isEmpty()) {
                                    cond = "true";
                                } else {
                                    for (int k = 0; k < sql.getCond().size(); k++) {
                                        if (sql.getCond().get(k).getColumna().equals(lista.get(i).getColumna())) {
                                            type = sql.getCond().get(k).getType();
                                            cond += lista.get(i).condicion(sql.getCond().get(k).getOp_relacionar(), sql.getCond().get(k).getCondicion()) + " ";
                                        }

                                    }
                                }

                        }

                    }

                    if (type.equals("Y")) {
                        if (cond.contains("false")) {
                        } else {
                            this.txto += letter + "\n";
                            
                            for (int i = 0; i < datos.length; i++) {
                                String dato = datos[i];
                                try{
                                    dato.isEmpty();
                                    values.add(datos);
                                }catch(Exception e){}
                                
                            }
                            

                        }
                    } else {
                        if (type.equals("OR")) {
                            if (cond.contains("true")) {
                                this.txto += letter + "\n";
                            for (int i = 0; i < datos.length; i++) {
                                String dato = datos[i];
                                try{
                                    dato.isEmpty();
                                    values.add(datos);
                                }catch(Exception e){}
                                
                            }}

                        } else {
                            if (cond.contains("true")) {
                                this.txto += letter + "\n";
                          for (int i = 0; i < datos.length; i++) {
                                String dato = datos[i];
                                try{
                                    dato.isEmpty();
                                    values.add(datos);
                                }catch(Exception e){}
                                
                            }  } else {
                            }
                        }
                    }
                }
            }
        } else {
            msj = "Error, verifique la ruta";
        }

    }

}
