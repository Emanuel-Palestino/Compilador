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
	public ArrayList<String> getSimboloTerminal(){
		return simboloTerminal;
	}
	public ArrayList<String> getSimboloNoTerminal(){
		return simboloNoTerminal;
	}


	public void agregarAcciones(String op1, String op2, String op3) {
		HashMap<String, String> auxAcciones = new HashMap<String, String>();
		auxAcciones.put(op1, op2);
		simboloTerminal.add(op3);
		this.acciones.add(auxAcciones);
	}

	public void agregarIra(String op1, String op2, String op3) {
		HashMap<String, String> auxIrA = new HashMap<String, String>();
		auxIrA.put(op1, op2);
		simboloNoTerminal.add(op3);
		this.irA.add(auxIrA);
	}

	// Metodos
	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
		TablaLR tabla = new TablaLR();
		ArrayList<String> temporalProduccion = new ArrayList<String>();

		for (ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()) {
			for (Elemento recorreElemento : recorreConjunto.getElementos()) {

				ConjuntoElementos aux = IrA.hacer(recorreConjunto, recorreElemento.getSimboloDespuesDePunto(), gramatica);
				temporalProduccion = recorreElemento.getProduccion();
				temporalProduccion.remove(recorreElemento.getTamañoProduccion() - 1);

				if (gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto())
						&& coleccionCanonica.getConjuntosElementos().contains(aux)) {
					tabla.agregarAcciones("Desplazar", coleccionCanonica.getConjuntosElementos().indexOf(aux) + "",
							recorreElemento.getSimboloDespuesDePunto() + "");

				}
				if (recorreElemento.getIndexPunto() == (recorreElemento.getTamañoProduccion() - 1)) {
					PrimerosSiguientes auxSiguientes = new PrimerosSiguientes();
					ConjuntoSimbolos resultadoSiguiente = new ConjuntoSimbolos();
					resultadoSiguiente = auxSiguientes.siguiente(recorreElemento.getSimboloGramatical(), gramatica);
					if (gramatica.getReglasProduccion().contains(temporalProduccion)) {
						for (String recorreSiguientes : resultadoSiguiente.getSimbolos()) {
							tabla.agregarAcciones("Reducir",gramatica.getReglasProduccion().indexOf(temporalProduccion) + "",recorreSiguientes);
						}

					}

				}
				if (recorreElemento.getSimboloDespuesDePunto().equals("$")) {
					tabla.agregarAcciones("Aceptar", "", "$");
				}
			}
		}

		for (ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()) {
			for (String simbolos_A : gramatica.getNoTerminales()) {
				ConjuntoElementos temporal = IrA.hacer(recorreConjunto, simbolos_A, gramatica);
				if (recorreConjunto.equals(temporal)) {
					tabla.agregarIra(coleccionCanonica.getConjuntosElementos().indexOf(temporal) + "", "", simbolos_A);
				}
			}
		}

		return tabla;
	}
}