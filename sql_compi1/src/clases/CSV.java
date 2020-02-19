/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import interfaz.project;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhonny
 */
public class CSV {
    public static boolean escribir(String path, String [] dato){
      boolean validar=true;

String archCSV = path;
CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(archCSV));
        } catch (IOException ex) {
            validar=false;
        }

writer.writeNext(dato);

        try {
            writer.close();
            validar=true;
        } catch (IOException ex) {
              validar=false;
        }
    return validar;}
     public static boolean escribir(String path, ArrayList<String []> dato){
      boolean validar=true;

String archCSV = path;
CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(archCSV));
        } catch (IOException ex) {
            validar=false;
        }

writer.writeAll(dato);

        try {
            writer.close();
            validar=true;
        } catch (IOException ex) {
              validar=false;
        }
    return validar;}
    
           public static String leer(){
    String archCSV = project.absoluthepath;
CSVReader csvReader = null;
String txt="";
        try {
            csvReader = new CSVReader(new FileReader(archCSV));
        } catch (FileNotFoundException ex) {
        }
        try {
            List<String[]> datos = csvReader.readAll();
            for (int i = 0; i < datos.size(); i++) {
                String array[]=datos.get(i);
                for (int j = 0; j < array.length; j++) {
                    if(j==array.length-1) txt+=array[j];
                    else txt+=array[j] +",";
                }
                    txt+="\n";
                
                
            }
        } catch (IOException | CsvException ex) {
        }
    return txt;}
}
