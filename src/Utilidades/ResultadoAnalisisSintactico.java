package Utilidades;

import java.util.ArrayList;
import java.util.Stack;

public class ResultadoAnalisisSintactico {
	private Stack<String> pila;
	private ArrayList<String> entrada;
	private ArrayList<String> accion;

	// Constructor
	public ResultadoAnalisisSintactico(Stack<String> pila, ArrayList<String> entrada, ArrayList<String> accion) {
		this.pila = pila;
		this.entrada = entrada;
		this.accion = accion;
	}

	// Getters and Setters
	public Stack<String> getPila() {
		return pila;
	}

	public ArrayList<String> getEntrada() {
		return entrada;
	}

	public ArrayList<String> getAccion() {
		return accion;
	}

	public void setPila(Stack<String> pila) {
		this.pila = pila;
	}

	public void setEntrada(ArrayList<String> entrada) {
		this.entrada = entrada;
	}

	public void setAccion(ArrayList<String> accion) {
		this.accion = accion;
	}

	public String[][] getDatosTabla() {
		String[][] res = new String[pila.size()][3];
		for (int j = 0; j < pila.size(); j++) {
			res[j][0] = (pila.get(j) == null) ? "" : pila.get(j).toString();
			res[j][1] = entrada.get(j).toString();
			res[j][2] = accion.get(j).toString();
		}

		return res;
	}

}
