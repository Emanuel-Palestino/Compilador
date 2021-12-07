package Utilidades;

import java.util.ArrayList;


import AnalizadorSintactico.ColeccionCanonica.Cerradura;
import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;

public class ColeccionCanonica {
	private ArrayList<ConjuntoElementos> conjuntosElementos;
	private String proceso;
	private int contador;

	// Constructor vacio
	public ColeccionCanonica() {
		conjuntosElementos = new ArrayList<ConjuntoElementos>();
		proceso = "";
		contador = 0;
	}

	public ColeccionCanonica(ArrayList<ConjuntoElementos> conjunto) {
		conjuntosElementos = conjunto;
		proceso = "";
		contador = 0;
	}

	// Getters
	public ArrayList<ConjuntoElementos> getConjuntosElementos() {
		return conjuntosElementos;
	}

	public String getProceso() {
		return this.proceso;
	}

	// Metodos

	public int indiceDe(ConjuntoElementos I) {
		int i; 
		int aux = 0;
		Boolean bandera = true;

		for (i = 0; i < conjuntosElementos.size(); i++) {
			ArrayList<Elemento> elementosActual = conjuntosElementos.get(i).getElementos();
			ArrayList<Elemento> elementosNuevo = I.getElementos();

			// Comparar si tienen el mismo largo
			if (elementosActual.size() == elementosNuevo.size()) {
				for (int k = 0; k < elementosActual.size(); k++) {
					// Comprobar elemento a elemento
					if (!elementosActual.get(k).esIgual(elementosNuevo.get(k))) {
						bandera = false; 
					} 
				}
				aux = i;
			}
			if(aux != 0){
				return aux;
			}
		}
		if(bandera == false){
			return -1;
		}
		return 1;
	}

	public void agregar(ConjuntoElementos conjunto) {
		Boolean bandera = true;
		for (ConjuntoElementos conjuntoEle : conjuntosElementos) {
			bandera = true;
			ArrayList<Elemento> elementosActual = conjuntoEle.getElementos();
			ArrayList<Elemento> elementosNuevo = conjunto.getElementos();
			Boolean bandera2 = true;

			// Comparar si tienen el mismo largo
			if (elementosActual.size() == elementosNuevo.size()) {

				for (int i = 0; i < elementosActual.size(); i++) {

					// Comprobar elemento a elemento
					if (!elementosActual.get(i).esIgual(elementosNuevo.get(i))) {
						bandera2 = false;
						break;
					}
				}

				if (bandera2) { // El conjunto actual es el mismo que el parametro y se termina el ciclo
								// principal
					bandera = false;
					break;
				}
			}
		}

		if (bandera)
			conjuntosElementos.add(conjunto);
	}

	public void agregarProceso(ColeccionCanonica coleccionCanonica) {

		for (ConjuntoElementos recorridoElementos : coleccionCanonica.getConjuntosElementos()) {
			proceso += contador;
			proceso += ": ";

			for (Elemento recorrido : recorridoElementos.getElementos()) {
				proceso += '[';
				proceso += recorrido.getSimboloGramatical();
				proceso += "->";
				proceso += recorrido.getProduccion();
				proceso += "], ";
			}
			proceso += '\n';
			contador++;
		}
	}

	public static ColeccionCanonica hacer(Gramatica gramatica) {
		ArrayList<ConjuntoElementos> resultado = new ArrayList<ConjuntoElementos>();

		// Regla produccion inicial
		Elemento regla1 = new Elemento();
		regla1.setSimboloGramatical(gramatica.getSimboloInicial() + "'");
		ArrayList<String> produccion = new ArrayList<String>();
		produccion.add("■");
		produccion.add(gramatica.getSimboloInicial());
		produccion.add("$");
		regla1.setProduccion(produccion);
		ConjuntoElementos ini = new ConjuntoElementos(regla1);

		resultado.add(Cerradura.hacer(ini, gramatica));

		ColeccionCanonica resFinal = new ColeccionCanonica(resultado);

		for (int i = 0; i < resultado.size(); i++) {
			ArrayList<Elemento> conjuntoEle = resultado.get(i).getElementos();

			// Obtener todos los simbolos X
			ArrayList<String> simbolosX = new ArrayList<String>();
			for (Elemento ele : conjuntoEle) {
				String X = ele.getSimboloDespuesDePunto();
				if (!simbolosX.contains(X) && !X.equals("$"))
					simbolosX.add(X);
			}

			// Hacer irA para cada X
			for (String X : simbolosX) {
				ConjuntoElementos resIrA = IrA.hacer(resultado.get(i), X, gramatica);
				if (resIrA.getElementos().size() > 0)
					resFinal.agregar(resIrA);
			}

		}

		resFinal.agregarProceso(resFinal);
		return resFinal;

	}

}
