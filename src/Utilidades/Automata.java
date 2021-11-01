package Utilidades;

import Utilidades.Listas.*;

public class Automata {
	private String id;
	private int totalEstados;
	private ListaDoblementeEnlazada[] adyacencia;

	public Automata() {
		id = null;
		totalEstados = 0;
		adyacencia = null;
	}	

	public Automata(Automata a) {
		totalEstados = a.getNumEstados();
		adyacencia = new ListaDoblementeEnlazada[totalEstados];
		for (int i = 0; i < totalEstados; i++) {
			try {
				adyacencia[i] = new ListaDoblementeEnlazada(a.getTransiciones(i));
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
		}
	}

	public Automata(int totalE) {
		id = null;
		totalEstados = totalE;
		adyacencia = new ListaDoblementeEnlazada[totalEstados];
	}

	public Automata(String simbolo) {
		id = simbolo;
		totalEstados = 2;
		adyacencia = new ListaDoblementeEnlazada[totalEstados];
		this.insertarTransicion(0, 1, simbolo);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public int getNumEstados() {
		return totalEstados;
	}

	public ListaDoblementeEnlazada getTransiciones(int estado) {
		return adyacencia[estado];
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNumEstados(int t) {
		totalEstados = t;
	}

	// Metodos
	public void insertarTransicion(int estadoOrigen, int estadoDestino, String simbolo) {

/* 		List<ArrayList<NodoLista>> listOfLists = new ArrayList<ArrayList<NodoLista>>();
		listOfLists.get(0); */

		if (adyacencia[estadoOrigen] == null)
			adyacencia[estadoOrigen] = new ListaDoblementeEnlazada();
		adyacencia[estadoOrigen].insertar(estadoDestino, simbolo);
	}

	public void insertarTransiciones(int estado, ListaDoblementeEnlazada tran) {
		adyacencia[estado] = tran;
	}


	public String[] getSimbolosTransiciones(int estado) {
		return new String[0];
	}

	public int[] getEstadosDestinoSimbolo(int estado, String simbolo) {
		return new int[0];
	}

	public String[][] getMatrizAdyacencia() {
		return new String[0][0];
	}
	// Eliminar Nodo
	// Buscar Nodo

}