package Utilidades;

import java.util.ArrayList;
import java.util.Stack;

public class ResultadoAnalisisSintactico {
	private Stack<String>[] pila;
	private ArrayList<String>[] entrada;
	private String[] accion;

	// Constructor
	public ResultadoAnalisisSintactico(Stack<String>[] pila, ArrayList<String>[] entrada, String[] accion) {
		this.pila = pila;
		this.entrada = entrada;
		this.accion = accion;
	}

	// Getters and Setters
	public Stack<String>[] getPila() {
		return pila;
	}

	public ArrayList<String>[] getEntrada() {
		return entrada;
	}

	public String[] getAccion() {
		return accion;
	}

	public void setPila(Stack<String>[] pila) {
		this.pila = pila;
	}

	public void setEntrada(ArrayList<String>[] entrada) {
		this.entrada = entrada;
	}

	public void setAccion(String[] accion) {
		this.accion = accion;
	}

}
