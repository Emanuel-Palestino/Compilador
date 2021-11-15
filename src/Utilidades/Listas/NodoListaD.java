package Utilidades.Listas;

import Utilidades.ConjuntoEstados;

public class NodoListaD {

	private NodoListaD anterior, siguiente;
	private ConjuntoEstados estados;
	private String transicion;

	public NodoListaD() {
		anterior = null;
		siguiente = null;
		estados = null;
		transicion = null;
	}

	public NodoListaD(NodoListaD ant, NodoListaD sig, ConjuntoEstados estados, String transicion) {
		anterior = ant;
		siguiente = sig;
		this.estados = estados;
		this.transicion = transicion;
	}

	public NodoListaD(ConjuntoEstados estados, String sT) {
		this.estados = estados;
		transicion = sT;
		anterior = null;
		siguiente = null;
	}

	public NodoListaD getAnterior() {
		return anterior;
	}

	public NodoListaD getSiguiente() {
		return siguiente;
	}

	public ConjuntoEstados getEstados() {
		return estados;
	}

	public String getTransicion() {
		return transicion;
	}

	public void setAnterior(NodoListaD ant) {
		anterior = ant;
	}

	public void setSiguiente(NodoListaD sig) {
		siguiente = sig;
	}

	public void setEstados(ConjuntoEstados estados) {
		this.estados = estados;
	}

}
