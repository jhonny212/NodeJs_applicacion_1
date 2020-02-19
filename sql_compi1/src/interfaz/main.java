/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import analizadorLexico.AnalizadorLexicoIDE;
import analizadorLexico.parserIde;
import clases.ERROR;

import clases.proyecto;

import static interfaz.project.tabbepane;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jhonny
 */
public class main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public main() {
        initComponents();
        this.setLocationRelativeTo(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_info = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(149, 140, 132));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 410));

        jPanel2.setBackground(new java.awt.Color(237, 223, 210));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_info.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        btn_info.setText("Informacion");
        btn_info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });
        jPanel2.add(btn_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        jButton1.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jButton1.setText("Abrir proyecto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 180, -1));

        jButton2.setFont(new java.awt.Font("Ubuntu Mono", 1, 18)); // NOI18N
        jButton2.setText("Crear nuevo proyecto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 220, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/portadaSql.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-290, 0, 940, 410));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 650, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_infoActionPerformed

    }//GEN-LAST:event_btn_infoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        nuevo_proyecto proyecto = new nuevo_proyecto();
        proyecto.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Creamos el objeto JFileChooser
        JFileChooser fc = new JFileChooser();
//Indicamos que podemos seleccionar varios ficheros
        fc.setMultiSelectionEnabled(true);
//Indicamos lo que podemos seleccionar
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//Creamos el filtro
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.ide", "ide");
//Le indicamos el filtro
        fc.setFileFilter(filtro);
//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion = fc.showOpenDialog(this);
//Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            project.file = null;
            project.file = fc.getSelectedFile();
        }
// verificar que el archivo no sea nulo y tipo:ide
        if (project.file != null && project.file.getName().contains(".ide")) {
            project tmp = new project();
            // inicializar errores sintaticos y lexicos
            parserIde.errorSintatico = new ArrayList();
            AnalizadorLexicoIDE.errorLexico = new ArrayList();
            // obtener estructura XML
            String txt = proyecto.leer(project.file);
            // pasar txt

            AnalizadorLexicoIDE lexico = new AnalizadorLexicoIDE(new StringReader(txt));
            try {
                new parserIde(lexico).parse();
            } catch (Exception ex) {
            }
            // si no hay errores    
            if (parserIde.errorSintatico.isEmpty() && AnalizadorLexicoIDE.errorLexico.isEmpty()) {
                tmp.show();
                dispose();

            } else {
                // Errores---------->    
                JOptionPane.showMessageDialog(this, "Hay errores--->");
                getError(txt);
              
            }

        } else {
            //Archivo seleccionado, no cumple ------------->
            JOptionPane.showMessageDialog(this, "Seleccione un archivo valido \n con extension: ide ");

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    private void getError(String txt) {
        String erroresLexico = "";
        ArrayList<String> colums = new ArrayList();
        colums.add("Linea");
        colums.add("Columna");
        colums.add("Valor");
        ArrayList<String[]> rows = new ArrayList();

        for (int i = 0; i < AnalizadorLexicoIDE.errorLexico.size(); i++) {
            ERROR e = AnalizadorLexicoIDE.errorLexico.get(i);
            rows.add(new String[]{String.valueOf(e.getLinea() + 1), String.valueOf(e.getColumna()), String.valueOf(e.getValor())});
        }
        
        ArrayList<String[]> rows2 = new ArrayList();
        for (int i = 0; i < parserIde.errorSintatico.size(); i++) {
            Symbol e = parserIde.errorSintatico.get(i);
            rows2.add(new String[]{(String.valueOf(e.left)), (String.valueOf(e.right)), (String.valueOf(e.value))});

        }

        

        tabla2 t2=new tabla2();
        t2.llenar2(colums, rows,txt);
        t2.llenar1(colums, rows2);
        t2.show();

       // getError2();
       
       
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}