package Utilidades;

import java.util.HashSet;
import java.util.Set;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.Gramatica.*;

public class ColeccionCanonica {
	private Set<ConjuntoElementos> conjuntosElementos;

	// Constructor vacio
	public ColeccionCanonica() {
		conjuntosElementos = new HashSet<ConjuntoElementos>();
	}

	// Getters
	public Set<ConjuntoElementos> getConjuntosElementos() {
		return conjuntosElementos;
	}

	// Metodos
	public void agregar(ConjuntoElementos conjunto) {
		conjuntosElementos.add(conjunto);
	}

	public static ColeccionCanonica crear(Gramatica gramatica){

		Cerradura cerradura = new cerradura();
		IrA irA = new IrA();

		ColeccionCanonica C = new ColeccionCanonica(); 
		C.agregar(cerradura.hacer(/*simbolo añadido de la gramatica*/));
		do{
			for (ConjuntoElementos i : conjuntosElementos) {//para cada conjunto de elementos i en C							conjuntosElementos es una referencia estatica
				for (/*cada simbolo gramatical : simbolosgramaticales*/) {//para cada simbolo gramatical x
					if(irA.hacer(i,i.getElementos())!=0){};//checar como se maneja irA cuando devuelve vacío
					C.agregar(i);	
				}
			}
		}while();
		
		return C;
	}

}
