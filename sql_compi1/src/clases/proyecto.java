/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import interfaz.project;
import static interfaz.project.estructura;
import static interfaz.project.file;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author jhonny
 */
public class proyecto {

    private final String projectName, path, nameFile, nameFolder;

    public proyecto(String projectName, String path, String nameFile, String nameFolder) {
        this.projectName = projectName;
        this.path = path;
        this.nameFile = nameFile;
        this.nameFolder = nameFolder;
    }
    //crear estructura ide de nuevo proyecto @return String    

    public String crearEstructura(proyecto pj) {
        char c = '"';
        String c2 = String.valueOf(c);
        String estructura = "<PROYECTO nombre=" + c2 + pj.getProjectName() + c2 + ">\n";
        if (!pj.getNameFolder().equals("")) {
            estructura += "<CARPETA nombre=" + c2 + pj.getNameFolder() + c2 + ">\n";
            if (!pj.getNameFile().equals("")) {
                estructura += "<ARCHIVO nombre=" + c2 + pj.getNameFile() + c2 + " ubicacion=" + c2 + pj.getPath() + "/" + pj.getNameFolder() + "/" + pj.getNameFile() + ".csv" + c2 + "/>\n";
            }
            estructura += "</CARPETA>\n";
        } else {
            if (!pj.getNameFile().equals("")) {
                estructura += "<ARCHIVO nombre=" + c2 + pj.getNameFile() + c2 + " ubicacion=" + c2 + pj.getPath() + "/" + pj.getNameFile() + ".csv" + c2 + "/>\n";
            }
        }
        estructura += "</PROYECTO>";

        return estructura;
    }

    public String GenerateProject(proyecto pj, String estructura, ArrayList<String> list) {
        String msj = "";
        File directorio = new File(pj.getPath());
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                msj = "Proyecto creado";
                escribirFichero(pj.getPath(), pj.getProjectName(), "ide", estructura,1);

                if (!pj.getNameFolder().equals("")) {
                    File folder = new File(pj.getPath() + "/" + pj.getNameFolder());
                    if (!folder.exists()) {
                        if (folder.mkdirs()) {
                        }
                    } else {
                        msj += " , sin embargo el folder no pudo ser creado";
                    }
                    if (!pj.getNameFile().equals("")) {
                        File file = new File(pj.getPath() + "/" + pj.getNameFolder() + "/" + pj.getNameFile() + ".csv");
                        if (!file.exists()) {// file.createNewFile();
                            String linearows = "";
                            for (int i = 0; i < list.size(); i++) {
                                if (i == list.size() - 1) {
                                    linearows += list.get(i);
                                } else {
                                    linearows += list.get(i) + ",";
                                }
                            }
                            escribirFichero(pj.getPath(), pj.getNameFolder() + "/" + pj.getNameFile(), "csv", linearows,1);
                        } else {
                            msj += " el archivo .csv no pudo ser creado, cambie de nombre";
                        }
                    }
                } else {
                    if (!pj.getNameFile().equals("")) {
                        File file = new File(pj.getPath() + "/" + pj.getNameFile() + ".csv");
                        if (!file.exists()) { //file.createNewFile();
                            String linearows = "";
                            for (int i = 0; i < list.size(); i++) {
                                if (i == list.size() - 1) {
                                    linearows += list.get(i);
                                } else {
                                    linearows += list.get(i) + ",";
                                }
                            }
                            escribirFichero(pj.getPath(), "/" + pj.getNameFile(), "csv", linearows,1);

                        } else {
                            msj += " el archivo .csv no pudo ser creado, cambie de nombre";
                        }

                    }
                }
            } else {
                msj = "Pruebe de nuevo";
            }
        } else {
            msj = "Cambie de directorio";
        }
        return msj;
    }
public static int counts;
    public static void generarnuevaEstructura(TreeNode nodo) {
        
        if (nodo.getChildCount() >= 0) {
            if(nodo.toString().contains(".csv")){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodo;
            TreePath path = null;
            String abs = "";
            if (node.isLeaf()) {
                node = (DefaultMutableTreeNode) node.getParent();
                path = new TreePath(node.getPath());
                String[] array = path.toString().split("");
                for (int i = 0; i < array.length; i++) {
                    if (array[i].equals("[") || array[i].equals("]")) {
                    } else {
                        if (array[i].equals(",")) {
                            abs += "/";
                        } else {
                            if (array[i].equals(" ")) {
                            } else {
                                abs += array[i];
                            }
                        }
                    }
                }
            }
    String tmp="";
                String array[]=nodo.toString().split("");
                for (int i = 0; i < array.length; i++) {
                    if(array[i].equals(".")){
                    break;
                    }else{tmp+=array[i];}
                    
                }
                project.estructura += "<ARCHIVO nombre=\"" + tmp + "\" ubicacion=\"" +folder.getnewpath(project.file.getAbsolutePath(), 2)+"/"+ abs + "/" + nodo.toString() + "\"/>\n";
             
        
            }else{
            if ((nodo.toString()+".ide").equals(file.getName())) {
                project.estructura += "<PROYECTO nombre=\"" + nodo.toString() + "\">\n";
            } else {
                project.estructura += "<CARPETA nombre=\"" + nodo.toString() + "\">\n";
            }
            for (Enumeration e = nodo.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                generarnuevaEstructura(n);
            }
            if ((nodo.toString()+".ide").equals(file.getName())) {
                project.estructura += "</PROYECTO>";
            } else {
                project.estructura += "</CARPETA>\n";
            }}
        } else {
           
        }
    }
    
    public String getProjectName() {
        return projectName;
    }

    public String getPath() {
        return path;
    }

    public String getNameFile() {
        return nameFile;
    }

    public String getNameFolder() {
        return nameFolder;
    }

    public static boolean escribirFichero(String path, String name, String ext, String estructura ,int opc) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        boolean validar=false;
        try {
            if(opc==1){
                fichero = new FileWriter(path + "/" + name + "." + ext + "");
            
            }else{
                fichero = new FileWriter(path);
            }
            pw = new PrintWriter(fichero);
            pw.println(estructura);
            validar=true;    
        } catch (Exception e) {
            e.printStackTrace();
            validar=false;
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

   return validar; }

    public static String leer(File pj) {
        FileReader fr = null;
        BufferedReader br = null;
        String txt = "";

        try {
            fr = new FileReader(pj);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                txt += (linea)+"\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return txt;
    }

}
