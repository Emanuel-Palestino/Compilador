package AnalizadorSintactico.ConstruccionTablaLR;

import java.util.ArrayList;
import java.util.Map;

import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class TablaLR {

	private ArrayList<Map<String, String>> acciones;
	private ArrayList<Map<String, String>> irA;

	// Constructor vacio
	public TablaLR() {
		acciones = new ArrayList<Map<String, String>>();
		irA = new ArrayList<Map<String, String>>();
	}

	// Constructor con parametros
	public TablaLR(ArrayList<Map<String, String>> acciones, ArrayList<Map<String, String>> irA) {
		this.acciones = acciones;
		this.irA = irA;
	}

	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {

		return new TablaLR();
	}

}
