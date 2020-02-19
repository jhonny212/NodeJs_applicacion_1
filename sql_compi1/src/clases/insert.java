/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jhonny
 */
public class insert extends sql {

    private ArrayList<String> c;

    public ArrayList<String> getC() {
        return c;
    }

    public insert() {
    }

    public String insertar(sql sql) {
        String msj="";
        int count=0;
        int typo=0;
        boolean validar=true;
        tabla tmp = new tabla(sql.getPath());
        if(tmp.llenarTabla()){
        this.c=tmp.getColumnas();
        String [] newdatos=new String[c.size()];
        if(sql.getColumnas().isEmpty()){
            typo=1;
        if(sql.getValores().size()==c.size()){
               msj="Registro realizado";
            for (int i = 0; i < sql.getValores().size(); i++) {
                 newdatos[i]=sql.getValores().get(i);
                 count++;
            }
        }else{msj="Ingrese la cantidad exacta de los valores \n"
                + "valores ingresados :"+sql.getValores().size()+"\n"
                + "cantidad de columnas aceptadas:"+c.size();
        
        }
        }else{
            typo=2;
            if(sql.getColumnas().size()==c.size() && c.size()==sql.getValores().size()){
                msj="Registro realizado";
               for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < sql.getColumnas().size(); j++) {
                  if(sql.getColumnas().get(j).equals(c.get(i)))
                  {
                  newdatos[i]=sql.getValores().get(j);
                  count++;
                  }
            }
        }
            }else{
            msj="Ingrese la cantidad exacta de los valores \n"
                + "cantidad de columnas ingresadas:"+sql.getColumnas().size()+"\n"
                + "cantidad de columnas aceptadas:"+c.size()+"\n"
                + "cantidad de valores ingresados"+sql.getValores().size();
            
            }
      
         
        }
        if(count==c.size()){
           
                    ArrayList<String[]>datos=new ArrayList();
            for (int i = 0; i < tmp.getValues().size(); i++) {
                datos.add(tmp.getValues().get(i));
                
            }
            String array[]=new String[newdatos.length];
            int j=0;
            for (int i = newdatos.length-1; i >= 0; i--) {
                array[j]=newdatos[i];
                j++;
            }
            if(typo==1){
            datos.add(array);
            }else if(typo==2){datos.add(newdatos);}
            
            CSV.escribir(tmp.getPaths(), datos);
        }else{
        msj+="\n"
                + "verifique que el nombre de las columnas sean correctas";
        }}else{msj="Verificar ruta";}
  return msj;  }
}
