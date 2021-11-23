package Utilidades.ConjuntoElementos;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoElementos {
	private Set<Elemento> elementos;

	// Constructor vacio
	public ConjuntoElementos() {
		elementos = new HashSet<Elemento>();
	}

	// Constructor con un elemento
	public ConjuntoElementos(Elemento elemento) {
		elementos = new HashSet<Elemento>();
		elementos.add(elemento);
	}

	// Getters and Setters
	public Set<Elemento> getElementos() {
		return elementos;
	}

	public void agregar(Elemento elemento) {
		elementos.add(elemento);
	}
	
}
