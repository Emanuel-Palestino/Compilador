package Utilidades;

import java.awt.Component;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.*;



public class Archivo {

	private  Component parent;
	private  JTextField campoTexto;
	String rutaText;

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

	public  String obtenerRutaArchivo(){
		campoTexto=new JTextField();
		//JFileChooser chooser = new JFileChooser();

		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir")+"\\src\\ArchivosExtra");
		int seleccion = chooser.showOpenDialog(parent);
		String rutaAux = "";

		if(seleccion==JFileChooser.APPROVE_OPTION){
			File archivoSeleccionado = chooser.getSelectedFile();
			campoTexto.setText(archivoSeleccionado.getAbsolutePath());
			rutaAux=archivoSeleccionado.getAbsolutePath();
		}
		return rutaAux;
	}
}
