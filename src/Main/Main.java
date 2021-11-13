package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import AnalizadorLexico.AlgoritmoThompson.IniciarThompson;
import AnalizadorLexico.ConversionAFD.IniciarConjuntos;
import AnalizadorLexico.Final.IniciarFinal;

public class Main {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error al aplicar estilo Windows");
        }
        //new IniciarThompson();
        //new IniciarConjuntos();
        //new IniciarFinal();  
    }
    
    
}