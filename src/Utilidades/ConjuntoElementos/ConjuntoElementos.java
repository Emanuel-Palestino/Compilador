package Utilidades.ConjuntoElementos;

import java.util.ArrayList;

public class ConjuntoElementos {
	private ArrayList<Elemento> elementos;

	// Constructor vacio
	public ConjuntoElementos() {
		elementos = new ArrayList<Elemento>();
	}

	// Constructor con un elemento
	public ConjuntoElementos(Elemento elemento) {
		elementos = new ArrayList<Elemento>();
		elementos.add(elemento);
	}

	// Getters and Setters
	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void agregar(Elemento elemento) {
		Boolean bandera = true;
		for (Elemento ele : elementos) {
			String simbolo = ele.getSimboloGramatical();
			ArrayList<String> produc = ele.getProduccion();

			// poner bandera en falso si existe algún igual
			if (simbolo.equals(elemento.getSimboloGramatical()) && produc.equals(elemento.getProduccion())) {
				bandera = false;
			}
		}

		// Agregar elemento si no es igual
		if (bandera)
			elementos.add(elemento);
	}
	
}
