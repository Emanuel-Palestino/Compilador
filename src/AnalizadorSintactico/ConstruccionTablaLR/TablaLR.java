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
	/*
	public void agregarAcciones(String op1, String op2) {
		Map<String,String> aux = new HashMap<String,String>();
		aux.put(op1, op2);
		this.acciones.add(aux);
	}
	*/ 
	public ArrayList<Map<String, String>> getIrA() {
		return irA;
	}
	/*
	public void agregarIra(String op1, String op2) {
		Map<String,String> aux = new HashMap<String,String>();
		aux.put(op1, op2);
		this.irA.add(aux);
	}
	*/
	// Metodos
	public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
		TablaLR tabla = new TablaLR();
		ArrayList <Map<String, String>> temporalAcciones = new ArrayList<Map<String, String>>();
		ArrayList <Map<String, String>> temporalIrA = new ArrayList<Map<String, String>>();
		ArrayList <String> temporalProduccion = new ArrayList<String>();
		HashMap<String, String> auxAcciones = new  HashMap<String, String> ();
		HashMap<String, String> auxIrA = new  HashMap<String, String> ();


		for(ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()){
			for (Elemento recorreElemento : recorreConjunto.getElementos()){
				//Elemento auxiliar = new Elemento(recorreElemento);
				ConjuntoElementos aux = IrA.hacer(recorreConjunto,recorreElemento.getSimboloDespuesDePunto(),gramatica);
				int tamañoRegla = recorreElemento.getTamañoProduccion();recorreElemento.getTamañoProduccion();
				temporalProduccion = recorreElemento.getProduccion();
				temporalProduccion.remove(recorreElemento.getTamañoProduccion()-1);
				if(gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto()) && coleccionCanonica.getConjuntosElementos().contains(aux)){
					//tabla.agregarAcciones("Desplazar", coleccionCanonica.getConjuntosElementos().indexOf(aux) + "");
					auxAcciones.put("Desplazar",coleccionCanonica.getConjuntosElementos().indexOf(aux) + "");
					temporalAcciones.add (auxAcciones); 
				}else if(recorreElemento.getIndexPunto() == (recorreElemento.getTamañoProduccion()-1)){
					
					for(ReglaProduccion recorreProduccion : gramatica.getReglasProduccion()){

						if(recorreProduccion.getProduccion().equals(temporalProduccion)){
							
						}
					}


					//tabla.agregarAcciones ("Reducir", gramatica.getReglasProduccion().getProduccion().indexOf(temporalProduccion)+ "");
					//para todos los simbolos terminales que sean siguientes de A, sin contar S'.	
				}else if(recorreElemento.getSimboloDespuesDePunto().equals("$")){	
					//tabla.agregarAcciones("Aceptar", "");
					auxAcciones.put ("Aceptar","");
					temporalAcciones.add (auxAcciones); 
				}
			}
		}

		//elemento_i=0
		for(ConjuntoElementos recorreConjunto : coleccionCanonica.getConjuntosElementos()){
			for ( String simbolos_A : gramatica.getNoTerminales()){

				if(recorreConjunto.equals (IrA.hacer(recorreConjunto, simbolos_A, gramatica))){
					// VERIFICAR CONDICION   
					// auxIrA.put (coleccionCanonica.getConjuntosElementos().indexOf()+"",simbolos_A)
					// temporalIrA.add(auxIrA)
				}
			}
		}

		return new TablaLR (temporalAcciones, temporalIrA); 
	}

}
