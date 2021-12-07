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
	private ArrayList<String> simboloTerminal;
	private ArrayList<String> simboloNoTerminal;

	// Constructor vacio
	public TablaLR() {
		acciones = new ArrayList<Map<String, String>>();
		irA = new ArrayList<Map<String, String>>();
		simboloTerminal = new ArrayList<String>();
		simboloNoTerminal = new ArrayList<String>();
	}

	// Constructor con parametros
	public TablaLR(ArrayList<Map<String, String>> acciones, ArrayList<Map<String, String>> irA) {
		this.acciones = acciones;
		this.irA = irA;
		simboloTerminal = new ArrayList<String>();
		simboloNoTerminal = new ArrayList<String>();
	}

	// Getters y Setters
	public ArrayList<Map<String, String>> getAcciones() {
		return acciones;
	}

	public ArrayList<Map<String, String>> getIrA() {
		return irA;
	}
	public ArrayList<String> getSimboloTerminal(){
		return simboloTerminal;
	}
	public ArrayList<String> getSimboloNoTerminal(){
		return simboloNoTerminal;
	}

	// Metodos
	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
		TablaLR tabla = new TablaLR();
		ReglaProduccion temporalProduccion = new ReglaProduccion();
		ArrayList<Map<String, String>> acciones = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> irA = new ArrayList<Map<String, String>>();
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




		for (ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()) {
			for (Elemento recorreElemento : recorreConjunto.getElementos()) {

				ArrayList<String> temp =recorreElemento.getProduccion();
				
				
				temporalProduccion.setSimboloGramatical(recorreElemento.getSimboloGramatical());

				if (gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto())) {
					ConjuntoElementos aux = IrA.hacer(recorreConjunto, recorreElemento.getSimboloDespuesDePunto(), gramatica);
					tabla.remove("Desplazar", coleccionCanonica.indiceDe(aux) + "",recorreElemento.getSimboloDespuesDePunto() + "");
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
							tabla.agregarAcciones("Reducir",gramatica.getReglasProduccion().indexOf(temporalProduccion) + "",recorreSiguientes);
						}
					}
					continue;

				} 
				if (recorreElemento.getSimboloDespuesDePunto().equals("$")) {
					tabla.agregarAcciones("Aceptar", "", "$");
					continue;
				}

				else{
					tabla.agregarAcciones(" ", " ", " ");
				}
			}
			for (String recorreNoTerminales : gramatica.getNoTerminales()) {
				ConjuntoElementos temporal = IrA.hacer(recorreConjunto, recorreNoTerminales, gramatica);
				if (recorreConjunto.equals(temporal)) {
					tabla.agregarIra(coleccionCanonica.getConjuntosElementos().indexOf(temporal) + "", "", recorreNoTerminales);
				} else {
					tabla.agregarIra("","",recorreNoTerminales);
				}
			}
		}

		return tabla;
	}
}