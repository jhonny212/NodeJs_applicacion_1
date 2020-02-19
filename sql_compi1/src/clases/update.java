/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jhonny
 */
public class update extends sql {

    ArrayList<String[]> data;
    private String p;

    public ArrayList<String[]> getData() {
        return data;
    }

    public String getP() {
        return p;
    }

    public update() {
        data = new ArrayList();
        p = "";
    }

    public String actualizar(sql sql) {
        String msj = "0";
        int count = 0;
        String cond = "";
        String letter = "";
        tabla tmp = new tabla(sql.getPath());
        String type = "";
        if (tmp.llenarTabla()) {
            p = tmp.getPaths();
            //---------------->llenar columnas de update
            String pathss[] = new String[tmp.getColumnas().size()];
            for (int i = 0; i < tmp.getColumnas().size(); i++) {
                pathss[i] = tmp.getColumnas().get(i);

            }
            data.add(pathss);
            //-------------------> iniciar recorrido
            for (int i = 0; i < tmp.getValores().size(); i++) {
                cond = "";
                ArrayList<cuadrosql> cuadro = tmp.getValores().get(i);
                String datas[] = new String[cuadro.size()];
                String datas2[] = new String[cuadro.size()];

                for (int j = 0; j < cuadro.size(); j++) {
                    datas[j] = cuadro.get(j).getValor();
                    datas2[j] = cuadro.get(j).getValor();
                    for (int l = 0; l < sql.getCond().size(); l++) {
                                    if(sql.getCond().get(l).getColumna().equals(cuadro.get(j).getColumna())){
                                      cond=cond+ cuadro.get(j).condicion(sql.getCond().get(l).getOp_relacionar(), sql.getCond().get(l).getCondicion()) + " ";
                                      type = sql.getCond().get(l).getType();
                                      break;
                                    }
                        }
                    for (int k = 0; k < sql.getAsig().size(); k++) {
                        if (cuadro.get(j).getColumna().equals(sql.getAsig().get(k).getColumna())) {
                            datas2[j] = sql.getAsig().get(k).getCondicion();
                            if (sql.getCond().isEmpty()) {
                                cond = "true";
                            }
                        }

                    }
                }
                //   System.out.println(cond+" "+type);
                                    
                
                if (type.equals("Y")) {
                    if (!cond.contains("false")) {
                         
                        msj = "Datos actualizados";
                        data.add(datas2);
                        
                    } else {
                       data.add(datas);
                    }
                } else {
                    if (type.equals("OR")) {
                        if (cond.contains("true")) {
                            msj = "Datos actualizados";
                            data.add(datas2);
                        } else {
                            data.add(datas);
                        }

                    } else {
                        if (cond.contains("true")) {
                            data.add(datas2);
                            msj = "Datos actualizados";
                        } else {
                            data.add(datas);
                        }
                    }
                }

            }
        } else {
            msj = "1";
        }

        return msj;
    }


}