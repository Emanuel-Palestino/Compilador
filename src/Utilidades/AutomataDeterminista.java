package Utilidades;

import Utilidades.Alfabeto.Alfabeto;
import Utilidades.Listas.ListaDoblementeEnlazadaD;

public class AutomataDeterminista {
	private int totalEstados;
	private ListaDoblementeEnlazadaD[] adyacencia;
	private Alfabeto alfabeto;

	public AutomataDeterminista() {
		totalEstados = 0;
		adyacencia = null;
		alfabeto = null;
	}

	// Getters and Setters
	public int getTotalEstados() {
		return totalEstados;
	}

	public ListaDoblementeEnlazadaD getTransiciones(int estado) {
		return adyacencia[estado];
	}

	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alf) {
		alfabeto = alf;
	}

	public void setTotalEstados(int totalE) {
		totalEstados = totalE;
	}

	// Metodos
	public void insertarTransicion(int estadoOrigen, ConjuntoEstados estadoDestino, String simbolo) {
		if (adyacencia[estadoOrigen] == null)
			adyacencia[estadoOrigen] = new ListaDoblementeEnlazadaD();
		adyacencia[estadoOrigen].insertar(estadoDestino, simbolo);
	}

	public void insertarAdyacencia(ListaDoblementeEnlazadaD[] transiciones) {
		adyacencia = transiciones;
	}

}
