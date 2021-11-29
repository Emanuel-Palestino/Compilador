package Pruebas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import AnalizadorSintactico.ColeccionCanonica.VentanaColeccionCanonica;

public class PruebaVentanaCC {

	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error al aplicar estilo Windows");
		}
		new VentanaColeccionCanonica();
	}

}
