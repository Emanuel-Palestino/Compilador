package AnalizadorSintactico.ConstruccionTablaLR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ColeccionCanonica;
import Utilidades.ConjuntoSimbolos;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;
import AnalizadorSintactico.PrimerosSiguientes.PrimerosSiguientes;

public class TablaLR {

	private ArrayList<Map<String, String>> acciones;
	private ArrayList<Map<String, String>> irA;

	// Constructor vacio
	public TablaLR() {
		acciones = new ArrayList<Map<String, String>>();
		irA = new ArrayList<Map<String, String>>();
	}

	// Constructor con parametros
	public TablaLR(ArrayList<Map<String, String>> acciones, ArrayList<Map<String, String>> irA) {
		this.acciones = acciones;
		this.irA = irA;
	}

	// Getters y Setters
	public ArrayList<Map<String, String>> getAcciones() {
		return acciones;
	}

	public ArrayList<Map<String, String>> getIrA() {
		return irA;
	}



	// Metodos
	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
		
		ArrayList<Map<String, String>> acciones = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> irA = new ArrayList<Map<String, String>>();
		
		for(int i = 0; i < coleccionCanonica.getConjuntosElementos().size() ; i++){

			HashMap<String, String> aux = new HashMap<String, String>();

			for (String simbolo : gramatica.getTerminales()){
				aux.put(simbolo, "");
			}
			aux.put ("$","");
			acciones.add(aux);
		}

		for(int i = 0; i < coleccionCanonica.getConjuntosElementos().size() ; i++) {

			ConjuntoElementos recorreConjunto = new ConjuntoElementos();
			recorreConjunto = coleccionCanonica.getConjuntosElementos().get(i);
			for (Elemento recorreElemento : recorreConjunto.getElementos()) {
				
				
				
				ReglaProduccion temporalProduccion = new ReglaProduccion();
				temporalProduccion.setSimboloGramatical(recorreElemento.getSimboloGramatical());
				
				if (gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto())) {
					ConjuntoElementos aux = IrA.hacer(recorreConjunto, recorreElemento.getSimboloDespuesDePunto(), gramatica);
					acciones.get(i).replace(recorreElemento.getSimboloDespuesDePunto(), "d" + coleccionCanonica.indiceDe(aux));
					
					continue;
					
				}
				if (recorreElemento.getIndexPunto() == (recorreElemento.getTamañoProduccion() - 1)) {
					PrimerosSiguientes auxSiguientes = new PrimerosSiguientes();
					ConjuntoSimbolos resultadoSiguiente = new ConjuntoSimbolos();
					resultadoSiguiente = auxSiguientes.siguiente(recorreElemento.getSimboloGramatical(), gramatica);
					ArrayList<String> temp = new ArrayList<String>(recorreElemento.getProduccion());
					temp.remove(recorreElemento.getTamañoProduccion() - 1);
					temporalProduccion.setProduccion(temp); 
 					if (gramatica.getReglasProduccion().contains(temporalProduccion)) {
						for (String recorreSiguientes : resultadoSiguiente.getSimbolos()) {
							acciones.get(i).replace(recorreSiguientes, "r" + gramatica.getReglasProduccion().indexOf(temporalProduccion) );
						}
					}
					continue;

				} 
				if (recorreElemento.getSimboloDespuesDePunto().equals("$")) {

					acciones.get(i).replace("$", "acep");
					continue;
				}

			}

			for( int k = 0; k < coleccionCanonica.getConjuntosElementos().size() ; k++){
	
				HashMap<String, String> aux = new HashMap<String, String>();
				for (String simbolo : gramatica.getNoTerminales()){
					aux.put(simbolo, "");
				}
				irA.add(aux);
			}
			for(int j = 0; j < gramatica.getNoTerminales().size(); j++) {
				ConjuntoElementos temporal = IrA.hacer(recorreConjunto, gramatica.getNoTerminales().get(j),gramatica);
				if (recorreConjunto.equals(temporal)) {

					irA.get(i).replace(gramatica.getNoTerminales().get(j),coleccionCanonica.indiceDe(temporal) + "");
				}
	
			}
		}


		return new TablaLR(acciones, irA);
	}
}