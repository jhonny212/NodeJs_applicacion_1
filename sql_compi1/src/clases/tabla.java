/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import interfaz.project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jhonny
 */
public class tabla {

    private ArrayList<String> columnas;
    private ArrayList<ArrayList> valores;
    private List<String[]> values;

    public List<String[]>  getValues() {
        return values;
    }
    
    private String abspath;
    private String paths;
    public tabla(String abspath) {
        this.abspath = abspath;
        columnas = new ArrayList();
        valores = new ArrayList();
        paths="";
    }

    public String getPaths() {
        return paths;
    }

    public boolean llenarTabla() {
        boolean validar=true;
        columnas = new ArrayList();
        ArrayList<cuadrosql> list = new ArrayList();
        paths=getAbspath();
        String archCSV = paths;

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(archCSV));
        } catch (FileNotFoundException ex) {validar=false;
        System.out.println(ex.getMessage()+"1");
        }
        try {
            List<String[]> datos = csvReader.readAll();
            this.values=datos;

            String array1[] = datos.get(0);
            for (int j = 0; j < array1.length; j++) {
                columnas.add(array1[j]);
            }

            for (int i = 1; i < datos.size(); i++) {
                String array[] = datos.get(i);
                 list = new ArrayList();
                for (int j = 0; j < array.length; j++) {
                    cuadrosql tmp = new cuadrosql(array[j], columnas.get(j), array[j].length());
                    list.add(tmp);
                }
                valores.add(list);
            }

        } catch (Exception ex) {
            validar=false;
              System.out.println(ex.getMessage()+"2");
        }
    return validar;}

    public ArrayList<String> getColumnas() {
        return columnas;
    }

    public ArrayList<ArrayList> getValores() {
        return valores;
    }

    public String getAbspath() {
        String pathtmp = folder.getnewpath(project.file.getAbsolutePath(), 2);
        pathtmp += "/";
        String tmp[] = abspath.split("");
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].equals(".")) {
                pathtmp += "/";
            } else {
                pathtmp += tmp[i];
            }
        }
        pathtmp += ".csv";
        this.abspath = pathtmp;
        return abspath;
    }

}

