package AnalizadorSintactico.ConstruccionTablaLR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ColeccionCanonica;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class TablaLR {

	private ArrayList <Map<String, String>> acciones;
	private ArrayList <Map<String, String>> irA;
	private ArrayList <String> simboloTerminal;
	private ArrayList <String> simboloNoTerminal;

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

	public void agregarAcciones(String op1, String op2, String op3) {
		HashMap<String, String> auxAcciones = new  HashMap<String, String> ();
		auxAcciones.put(op1, op2);
		simboloTerminal.add (op3);
		this.acciones.add(auxAcciones);
	}
	public void agregarIra(String op1, String op2, String op3) {
		HashMap<String, String> auxIrA = new  HashMap<String, String> ();
		auxIrA.put(op1, op2);
		simboloNoTerminal.add (op3);
		this.irA.add(auxIrA);
	}

	// Metodos
	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
		TablaLR tabla = new TablaLR();
		ArrayList <String> temporalProduccion = new ArrayList<String>();


		for(ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()){
			for (Elemento recorreElemento : recorreConjunto.getElementos()){

				ConjuntoElementos aux = IrA.hacer(recorreConjunto,recorreElemento.getSimboloDespuesDePunto(),gramatica);
				temporalProduccion = recorreElemento.getProduccion();
				temporalProduccion.remove(recorreElemento.getTamañoProduccion()-1);

				if(gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto()) && coleccionCanonica.getConjuntosElementos().contains(aux)){
					tabla.agregarAcciones("Desplazar", coleccionCanonica.getConjuntosElementos().indexOf(aux) + "",recorreElemento.getSimboloDespuesDePunto()+"");
				}else if(recorreElemento.getIndexPunto() == (recorreElemento.getTamañoProduccion()-1)){
					for(ReglaProduccion recorreProduccion : gramatica.getReglasProduccion()){
						if(recorreProduccion.getProduccion().equals(temporalProduccion)){
							
						}
					}


					//tabla.agregarAcciones ("Reducir", gramatica.getReglasProduccion().getProduccion().indexOf(temporalProduccion)+ "");
					//para todos los simbolos terminales que sean siguientes de A, sin contar S'.	
				}else if(recorreElemento.getSimboloDespuesDePunto().equals("$")){	
					tabla.agregarAcciones("Aceptar", "","$");
				}
			}
		}

		for(ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()){
			for ( String simbolos_A : gramatica.getNoTerminales()){
				ConjuntoElementos temporal = IrA.hacer(recorreConjunto, simbolos_A, gramatica);
				if(recorreConjunto.equals(temporal)){
					tabla.agregarIra(coleccionCanonica.getConjuntosElementos().indexOf(temporal)+"", "", simbolos_A);
				}
			}
		}

		return tabla; 
	}

}
