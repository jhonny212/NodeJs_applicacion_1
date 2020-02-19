/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template Archivo, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import interfaz.project;
import static interfaz.project.jTree1;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author jhonny
 */
public class Archivo {

    public static void create(String nombre) {
        
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nombre);
        selectedNode.add(newNode);
        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        model.reload();
    }

    public static void update(String nombre) {
        
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();
        selectedNode.setUserObject(nombre);
        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        model.reload();
    }

    public static void delete() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

        if (selectedNode != jTree1.getModel().getRoot()) {
            DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();

            model.removeNodeFromParent(selectedNode);

            model.reload();
        }
    }
    public static boolean  delete(String path) {
       boolean validar=false;
       File file=new File(path);
       if(file.exists()){
           if(file.delete()){
               validar=true;
               System.out.println("se elimino--------------->");
           }else{validar=false;
                 System.out.println("No se elimino--------------->");
         
           }
       }else{validar=false;}
       
    return validar;}
    
    public static boolean create(String path, int tipo, String name) {
        boolean validar=true;
        switch(tipo){
            case 1:
                String array[]=path.split("");
                String tmp="";
                File Folder = new File(folder.getnewpath(project.file.getAbsolutePath(), 2)+"/"+path(array,tmp)+"/"+name);
                  if (!Folder.exists()) {
                       
                        if (Folder.mkdirs()) {
                          
                        }else{validar=false;}
                    } else {
                      validar=false;
                    }
                break;
            case 2:
                break;
        }
        
        /*DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        model.reload();*/
    return validar;}
    
    public static boolean update(String oldpath, String newname) {
        boolean validar=true;
        
        File oldfile = new File(oldpath);
        String tmp=folder.getnewpath(oldfile.getAbsolutePath(), 1)+"/"+newname;
        System.out.println(oldpath);
        File newfile = new File(tmp);
        if(newfile.exists()){
            validar=false;
        }else{ if (oldfile.renameTo(newfile)) {
        } else {
            validar=false;
        }}
       
         
    return validar;}
    
    public static String path(String array1[],String path){
    for (int i = 0; i < array1.length; i++) {
                    if (array1[i].equals("[") || array1[i].equals("]") || array1[i].equals(" ")) {
                    } else {
                        if (array1[i].equals(",")) {
                            path += "/";
                        } else {
                            path += array1[i];
                        }
                    }
                }
    return path;}
    
}
