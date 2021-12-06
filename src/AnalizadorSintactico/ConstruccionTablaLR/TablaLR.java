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


	public void agregarAcciones(String op1, String op2, String op3) {
		HashMap<String, String> auxAcciones = new HashMap<String, String>();
		auxAcciones.put(op1, op2);
		this.simboloTerminal.add(op3);
		this.acciones.add(auxAcciones);
	}

	public void agregarIra(String op1, String op2, String op3) {
		HashMap<String, String> auxIrA = new HashMap<String, String>();
		auxIrA.put(op1, op2);
		this.simboloNoTerminal.add(op3);
		this.irA.add(auxIrA);
	}

	// Metodos
	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
		TablaLR tabla = new TablaLR();
		
		for (ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()) {
			for (Elemento recorreElemento : recorreConjunto.getElementos()) {
				
				
				ArrayList<String> temp = new ArrayList<String>(recorreElemento.getProduccion());
			
				ReglaProduccion temporalProduccion = new ReglaProduccion();
				temporalProduccion.setSimboloGramatical(recorreElemento.getSimboloGramatical());

				if (gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto())) {
					ConjuntoElementos aux = IrA.hacer(recorreConjunto, recorreElemento.getSimboloDespuesDePunto(), gramatica);
					tabla.agregarAcciones("Desplazar", coleccionCanonica.indiceDe(aux) + "",recorreElemento.getSimboloDespuesDePunto() + "");
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
			for (String simbolos_A : gramatica.getNoTerminales()) {
				ConjuntoElementos temporal = IrA.hacer(recorreConjunto, simbolos_A, gramatica);
				if (recorreConjunto.equals(temporal)) {
					tabla.agregarIra(coleccionCanonica.getConjuntosElementos().indexOf(temporal) + "", "", simbolos_A);
				} else {
					tabla.agregarIra("","",simbolos_A);
				}
			}
		}

		return tabla;
	}
}