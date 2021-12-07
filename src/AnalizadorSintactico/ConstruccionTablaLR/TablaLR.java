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

		ReglaProduccion temporalProduccion = new ReglaProduccion();
		ArrayList<Map<String, String>> acciones = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> irA = new ArrayList<Map<String, String>>();
		int i = 0;

		//Construimos acciones e ira
		for (ConjuntoElementos construirestados: coleccionCanonica.getConjuntosElementos()) {
			HashMap<String, String> aux = new HashMap<String, String>();
			HashMap<String, String> aux2 = new HashMap<String, String>();
			for (String simbolo_a :gramatica.getTerminales()){
				aux.put(simbolo_a, "");
			}
			for (String simbolo_A :gramatica.getNoTerminales()){
				aux2.put (simbolo_A,"");
			}
			acciones.add(aux);
			irA.add(aux2);
		}


		//Algoritmo de construcción 

		for (ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()) {
			for (Elemento recorreElemento : recorreConjunto.getElementos()) {
				
				temporalProduccion.setSimboloGramatical(recorreElemento.getSimboloGramatical());

				if (gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto())) {
					ConjuntoElementos temporal = IrA.hacer(recorreConjunto, recorreElemento.getSimboloDespuesDePunto(), gramatica);
					acciones.get(i).replace(recorreElemento.getSimboloDespuesDePunto(), "Desplazar" + coleccionCanonica.indiceDe(temporal) + "");
					continue;

				}
				if (recorreElemento.getIndexPunto() == (recorreElemento.getTamañoProduccion() - 1)) {
					PrimerosSiguientes auxSiguientes = new PrimerosSiguientes();
					ConjuntoSimbolos resultadoSiguiente = new ConjuntoSimbolos();
					resultadoSiguiente = auxSiguientes.siguiente(recorreElemento.getSimboloGramatical(), gramatica);
					temp.remove(recorreElemento.getTamañoProduccion() - 1);
					temporalProduccion.setProduccion(temp); 
					if (gramatica.getReglasProduccion().contains(temporalProduccion)) {
						for (String recorreSiguientes : resultadoSiguiente.getSimbolos()) {
							acciones.get(i).replace(recorreSiguientes,"Reducir" + gramatica.getReglasProduccion().indexOf(temporalProduccion) + "");
						}
					}
					continue;

				} 
				if (recorreElemento.getSimboloDespuesDePunto().equals("$")) {
					acciones.get(i).replace("$","Aceptar");
					continue;
				}
			}
			for (String recorreNoTerminales : gramatica.getNoTerminales()) {
				ConjuntoElementos temporal = IrA.hacer(recorreConjunto, recorreNoTerminales, gramatica);
				if (recorreConjunto.equals(temporal)) {
					irA.get(i).replace(recorreNoTerminales, "Regla" + coleccionCanonica.getConjuntosElementos().indexOf(temporal) + "");
				} 
			}
			i++;
		}

		return new TablaLR(acciones, irA);
	}
}