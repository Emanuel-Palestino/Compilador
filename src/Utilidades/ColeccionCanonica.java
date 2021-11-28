package Utilidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;

public class ColeccionCanonica {
	private Set<ConjuntoElementos> conjuntosElementos;
	private static String proceso;

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



	//hacer clase gramatica ampliada
	
	public static ColeccionCanonica crear(Gramatica gramatica){


		ColeccionCanonica coleccionCanonica = new ColeccionCanonica();
		StringBuilder procesoResultado = new StringBuilder();

		//crear regla 0
		//---------------------------------------------------------------------------
		Elemento elementoInicial = new Elemento();
		ArrayList<String> produccion = new ArrayList<String>();
		produccion.add(gramatica.getSimboloInicial());
		produccion.add("$");
		elementoInicial.setSimboloGramatical(gramatica.getSimboloInicial() +"'");
		elementoInicial.setProduccion(produccion);
		elementoInicial.insertarPuntoInicial();

		//_--------------------------------------------------------------------------
		coleccionCanonica.agregar(Cerradura.hacer(elementoInicial));
		
		

		for(ConjuntoElementos conjuntoElementosActual:  coleccionCanonica.getConjuntosElementos()){

			//Cada simbolo gramatical
			Set<String> simbolosGramaticales = new HashSet<String>();

			for(Elemento elemento : conjuntoElementosActual.getElementos() ){

				simbolosGramaticales.add(elemento.getSimboloDespuesDePunto());
			}

			ConjuntoElementos aux = new ConjuntoElementos(); 

			for(String X : simbolosGramaticales){

				aux.agregar(IrA.hacer(conjuntoElementosActual,X));

				if(aux.getElementos().size() > 0){
					
					coleccionCanonica.agregar(aux);
					procesoResultado.append(aux);
				}
			}		
		}
		
		return coleccionCanonica;
	}
}
