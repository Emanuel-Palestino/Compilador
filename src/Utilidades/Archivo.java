package Utilidades;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.*;

public class Archivo {

	public static ArrayList<String> capturaDatosArchivo(String direccionArchivo) throws FileNotFoundException, IOException {
		ArrayList<String> contenido = new ArrayList<String>();
		FileReader archivo = new FileReader(direccionArchivo, StandardCharsets.UTF_8);
		BufferedReader lectorBuffer = new BufferedReader(archivo);
		String entradaDeTexto = new String();

		while ((entradaDeTexto = lectorBuffer.readLine()) != null)
			contenido.add(entradaDeTexto);

		lectorBuffer.close();

		return contenido;
	}

	public static BufferedReader abrirArchivo(String direccionArchivo) throws FileNotFoundException {
		FileReader archivo = null;
		archivo = new FileReader(direccionArchivo);

		return new BufferedReader(archivo);
	}

	public static String obtenerRutaArchivo(JDialog parent) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir") + "\\src\\ArchivosExtra");
		int seleccion = chooser.showOpenDialog(parent);
		String rutaAux = "";

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = chooser.getSelectedFile();
			rutaAux = archivoSeleccionado.getAbsolutePath();
		}
		return rutaAux;
	}
}
