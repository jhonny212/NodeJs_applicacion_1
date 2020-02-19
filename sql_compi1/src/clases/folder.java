/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static interfaz.project.jTree1;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author jhonny
 */
public class folder {

    private String name;
    private String path;

    public DefaultMutableTreeNode p;

    public folder() {
        this.p = new DefaultMutableTreeNode();
    }

    public DefaultMutableTreeNode getArbol(String path) {
        DefaultMutableTreeNode nodo = null;
        File f = new File(path);
        if (f.exists()) {

            nodo = new DefaultMutableTreeNode(f.getName());
            File[] ficheros = f.listFiles();
            for (int i = 0; i < ficheros.length; i++) {

                String tmp = ficheros[i].getName();
                if (tmp.contains(".csv")) {
                    DefaultMutableTreeNode nodocsv = new DefaultMutableTreeNode(tmp);
                    nodo.add(nodocsv);

                } else {
                    try {
                        nodo.add((getArbol(ficheros[i].getAbsolutePath())));
                    } catch (java.lang.NullPointerException e) {
                    }
                }
            }
        }

        return nodo;
    }

    public static String getnewpath(String path, int x) {
        String[] array = path.split("/");
        String abs = "";
        for (int i = 0; i < array.length - x; i++) {
            if (i == array.length - x - 1) {
                abs += array[i];
            } else {
                abs += array[i] + "/";
            }
        }
       
        return abs;
    }

    public static String getnewpath(Object path) {
        String[] array = path.toString().split("");
        String abs = "";
        for (int i = 0; i < array.length; i++) {

            if (array[i].equals("[") || array[i].equals("]") || array[i].equals(" ")) {
            } else {
                if (array[i].equals(",")) {
                    abs += "/";
                } else {
                    abs += array[i];
                }

            }

        }
        return abs;
    }


    
    
}
