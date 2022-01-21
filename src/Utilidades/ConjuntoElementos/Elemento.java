package Utilidades.ConjuntoElementos;

import java.util.ArrayList;

import Utilidades.Gramatica.ReglaProduccion;

public class Elemento {
	private String simboloGramatical;
	private ArrayList<String> produccion;

	// Constructor Vacio
	public Elemento() {
		simboloGramatical = null;
		produccion = new ArrayList<String>();
	}

	// Constructor de copia para una ReglaProduccion
	public Elemento(ReglaProduccion regla) {
		simboloGramatical = regla.getStringSimboloGramatical();
		produccion = new ArrayList<String>();
		ArrayList<String> aux = regla.getProduccion();
		for (String simbolo : aux)
			produccion.add(simbolo);
	}

	// Constructor de copia para un Elemento
	public Elemento(Elemento elemento) {
		simboloGramatical = elemento.simboloGramatical;
		produccion = new ArrayList<String>();
		for (String simbolo : elemento.produccion)
			produccion.add(simbolo);
	}

	// Getters and Setters
	public String getSimboloGramatical() {
		return simboloGramatical;
	}

	public ArrayList<String> getProduccion() {
		return produccion;
	}

	public void setSimboloGramatical(String simbolo) {
		simboloGramatical = simbolo;
	}

	public void setProduccion(ArrayList<String> produccion) {
		this.produccion = produccion;
	}

	// Metodos
	public void insertarPuntoInicial() {
		produccion.add(0, "■");
	}

	public void moverPunto() {
		int indexPunto = produccion.indexOf("■");
		produccion.remove(indexPunto);
		produccion.add(indexPunto + 1, "■");
	}

	public int getTamañoProduccion() {
		return produccion.size();
	}

	public int getIndexPunto() {
		return produccion.indexOf("■");
	}

	public String getSimboloDespuesDePunto() {
		if (getIndexPunto() <= produccion.size() - 2)
			return produccion.get(getIndexPunto() + 1);
		return "";
	}

	public Boolean esIgual(Elemento elemento) {
		String simbolo = elemento.simboloGramatical;
		ArrayList<String> produccion = elemento.produccion;

		// Comprobar
		if (simbolo.equals(this.simboloGramatical) && produccion.equals(this.produccion))
			return true;
		else
			return false;

	}

}
