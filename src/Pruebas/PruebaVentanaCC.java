package Pruebas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import AnalizadorSintactico.ColeccionCanonica.VentanaColeccionCanonica;
import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class PruebaVentanaCC {

	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error al aplicar estilo Windows");
		}
		Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica.txt");
		String resultado = ColeccionCanonica.hacer(gramatica).getProceso();
		new VentanaColeccionCanonica(null,"","","","",resultado);
	}

}
