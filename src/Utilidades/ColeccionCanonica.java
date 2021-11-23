package Utilidades;

import java.util.HashSet;
import java.util.Set;
import Utilidades.ConjuntoElementos.ConjuntoElementos;

public class ColeccionCanonica {
	private Set<ConjuntoElementos> conjuntosElementos;

	// Constructor vacio
	public ColeccionCanonica() {
		conjuntosElementos = new HashSet<ConjuntoElementos>();
	}

	//Getters
	public Set<ConjuntoElementos> getConjuntosElementos() {
		return conjuntosElementos;
	}

	// Metodos
	public void agregar(ConjuntoElementos conjunto) {
		conjuntosElementos.add(conjunto);
	}
	
}
