/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import analizadorLexico.AnalizadorLexicoIDE;
import analizadorLexico.parserIde;
import analizador_ide.lexicoSql;
import analizador_ide.parserSql;
import static analizador_ide.parserSql.consultas;
import clases.sql;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author jhonny
 */
public class Sql_compi1 {

    public static ArrayList<String> array;

  

    public Sql_compi1() {

    }

    ;
    /**
     * @param args the command line arguments
     */
       public static void main(String[] args) throws Exception {
//generarCompilador();

        //String ruta1 = "E:/Documentos/NetBeansProjects/AnalizadorLexico/src/codigo/Lexer.flex";
        // String ruta2 = "/home/jhonny/NetBeansProjects/sql_compi1/src/analizador_ide/analizadorLexico.jflex";
        // String[] rutaS = {"-parser2", "parser2", "/home/jhonny/NetBeansProjects/sql_compi1/src/analizador_ide/parser.cup"};
        // parserIde.errorSintatico = new ArrayList();
        //
        /*  AnalizadorLexicoIDE.errorLexico = new ArrayList();
        String txt = "<PROYECTO  nombre=\"pj\">\n" +
"<CARPETA  nombre=\"carpeta\">\n" +
"<ARCHIVO  nombre=\"file\" ubicacion=\"/home/jhonny/Escritorio/pj/carpeta/file.csv\"/>\n" +
"</CARPETA>\n" +
"</PROYECTO>";

        AnalizadorLexicoIDE lexico = new AnalizadorLexicoIDE(new StringReader(txt));
        try {
            new parserIde(lexico).parse();
        } catch (Exception ex) {

        }*/
      
parserSql.arraycolumnas= new ArrayList();
parserSql.arrayvalores= new ArrayList();
parserSql.arraycon= new ArrayList();
parserSql.arrayasig= new ArrayList();
parserSql.consultas= new ArrayList();




        lexicoSql Lexico = new lexicoSql(new StringReader("ACTUALIZAR EN pj1.carpeta.archivosql ASIGNAR nombre=\"f1\" FILTRAR nombre=\"u1\" AND nombre=\"u1\" AND nombre=\"u1\";"));
       
        try {
            new parserSql(Lexico).parse();
        } catch (Exception ex) {
        }
      
        System.out.println(parserSql.consultas.size());
        
        for(int i=0; i<parserSql.consultas.size();i++){
        sql tmp=parserSql.consultas.get(i);
        System.out.println(tmp.getConsulta()+" "+tmp.getPath());
        
        try{
        System.out.println(tmp.getColumnas().size());
        }catch(NullPointerException e){
        }
        try{
        System.out.println(tmp.getCond().size()+":");
        }catch(NullPointerException e){
        }
          try{
        System.out.println(tmp.getValores().size());
        }catch(NullPointerException e){
        }
            try{
        System.out.println(tmp.getAsig().size());
        }catch(NullPointerException e){
        }
        }
     
     
      
    }

    private static void generarCompilador() {
        try {
            String ruta = "src/analizador_ide/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "Lexicosql.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "parserSql", ruta + "parsersql.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void s() {

    }

    public static void generar(String ruta1, String ruta2, String[] rutaS) throws IOException, Exception {
        File archivo;
        archivo = new File(ruta2);
        jflex.Main.generate(archivo);
        // archivo = new File(ruta2);
        //  jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS);

        Path rutaSym = Paths.get("/home/jhonny/NetBeansProjects/sql_compi1/src/analizador_ide/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("/home/jhonny/NetBeansProjects/sql_compi1/sym.java"),
                Paths.get("/home/jhonny/NetBeansProjects/sql_compi1/src/analizador_ide/sym.java")
        );
        Path rutaSin = Paths.get("/home/jhonny/NetBeansProjects/sql_compi1/src/analizador_ide/parser.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("/home/jhonny/NetBeansProjects/sql_compi1/parser.java"),
                Paths.get("/home/jhonny/NetBeansProjects/sql_compi1/src/analizador_ide/parser.java")
        );
    }
    
      private static void m() {
       String txt = "hola\n hola1\n hola2 ";
       String array[]=txt.split("\n");
          for (int i = 0; i < array.length; i++) {
             System.out.println(array[i]+i);
              
          }
    }
}
