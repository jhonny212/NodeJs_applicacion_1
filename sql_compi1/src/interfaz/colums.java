/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;
import javax.swing.*;

/**
 *
 * @author jhonny
 */

public class colums
{
  public static void main(String[] args)
  {
    JFrame frame = new JFrame("InputDialog Example #2");
    String code = JOptionPane.showInputDialog(
        frame, 
        "Ingrese el nombre de la columna", 
        "-------->", 
        JOptionPane.WARNING_MESSAGE
    );
    // if the user presses Cancel, this will be null
    System.out.printf("The secret code is '%s'.\n", code);
    System.exit(0);
  }
}