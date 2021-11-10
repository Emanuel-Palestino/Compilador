package Utilidades;

import java.io.*;
import java.util.ArrayList;

public class Archivo {

	public ArrayList<String> capturaDatosArchivo(String direccionArchivo) throws FileNotFoundException, IOException {
		ArrayList<String> contenido = new ArrayList<String>();
		FileReader archivo = new FileReader(direccionArchivo);
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

}
