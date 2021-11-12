package Main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import AnalizadorLexico.ConversionAFD.IniciarConjuntos;
import AnalizadorLexico.Final.IniciarFinal;
import Utilidades.Excepciones.ExcepcionER;
public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ExcepcionER {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error al aplicar estilo Windows");
		}
		//new IniciarThompson();
		//new IniciarConjuntos();
		new IniciarFinal();
	}
}
