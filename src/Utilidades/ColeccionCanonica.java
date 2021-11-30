package Utilidades;

import java.util.ArrayList;

import AnalizadorSintactico.ColeccionCanonica.Cerradura;
import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;

public class ColeccionCanonica {
	private ArrayList<ConjuntoElementos> conjuntosElementos;

	// Constructor vacio
	public ColeccionCanonica() {
		conjuntosElementos = new ArrayList<ConjuntoElementos>();
	}

	public ColeccionCanonica(ArrayList<ConjuntoElementos> conjunto) {
		conjuntosElementos = conjunto;
	}

	//Getters
	public ArrayList<ConjuntoElementos> getConjuntosElementos() {
		return conjuntosElementos;
	}

	// Metodos
	public void agregar(ConjuntoElementos conjunto) {
		Boolean bandera = true;
		for (ConjuntoElementos conjuntoEle : conjuntosElementos) {
			ArrayList<Elemento> elementosActual = conjuntoEle.getElementos();
			ArrayList<Elemento> elementosNuevo = conjunto.getElementos();

			// Comparar si tienen el mismo largo
			if (elementosActual.size() == elementosNuevo.size()) {
				for (int i = 0; i < elementosActual.size(); i++) {
					// Comparacion elemento a elemento
					
				}
			}
		}

		if (bandera)
			conjuntosElementos.add(conjunto);


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

		for (int i = 0; i < resultado.size(); i ++) {
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

		return resFinal;
		
	}
	
}
