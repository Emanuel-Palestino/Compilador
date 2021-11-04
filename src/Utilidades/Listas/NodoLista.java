package Utilidades.Listas;

public class NodoLista {
	
	private NodoLista anterior, siguiente;
	private int estadoDestino;	
	private String simboloTransicion;

	public NodoLista() {
		anterior = null;
		siguiente  = null;
		estadoDestino = -1;
		simboloTransicion = null;
	}

	public NodoLista(NodoLista ant, NodoLista sig, int eD, String sT) {
		anterior = ant;
		siguiente = sig;
		estadoDestino = eD;
		simboloTransicion = sT;
	}

	public NodoLista(int eD, String sT) {
		estadoDestino = eD;
		simboloTransicion = sT;
		anterior = null;
		siguiente = null;
	}

	// Getter and Setters
	public NodoLista getAnterior() {
		return anterior;
	}

	public NodoLista getSiguiente() {
		return siguiente;
	}

	public int getEstadoDestino() {
		return estadoDestino;
	}

	public String getSimbolo() {
		return simboloTransicion;
	}

	public void setAnterior(NodoLista ant) {
		anterior = ant;
	}

	public void setSiguiente(NodoLista sig) {
		siguiente = sig;
	}

	public void setEstadoDestino(int d) {
		estadoDestino = d;
	}
}
