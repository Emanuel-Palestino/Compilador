package AnalizadorSintactico.PrimerosSiguientes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class PrimerosSiguientes {

	/*
	 * public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {
	 * 
	 * return new ResultadoPrimerosSiguientes(); }
	 */

	public static ConjuntoSimbolos siguiente(String simbolo, Gramatica gramatica) {
		ArrayList<ReglaProduccion> reglasSimboloActual = new ArrayList<ReglaProduccion>();
		ConjuntoSimbolos resultadoPrimero = new ConjuntoSimbolos();
		ConjuntoSimbolos resultado = new ConjuntoSimbolos();
		ConjuntoSimbolos temporal = new ConjuntoSimbolos();
		ConjuntoSimbolos temporalSig = new ConjuntoSimbolos();

		int tamañoVariable, posicionA, contadorBeta; // contador
		int posicionBeta;

		// Le asigna simbolo al atributo id
		resultado.setId(simbolo);
		// Recorremos gramatica.getReglasProduccion() y movemos esas reglas para
		// siguiente
		// Primer Caso (Regla 1)
		if (gramatica.getSimboloInicial().equals(simbolo)) {
			resultado.agregarSimbolo("$");
		}

		// Obtener las reglas de produccion del simbolo a calcular que este a la derecha

		for (ReglaProduccion buscando : gramatica.getReglasProduccion()) {
			if (buscando.getProduccion().contains(simbolo) && buscando.getMarcadoSiguiente() == false) {
				reglasSimboloActual.add(buscando);
				buscando.setMarcadoSiguiente(true);
			}
		}

		// Empezamos a calcular las reglas 2 y 3

		for (ReglaProduccion buscar : reglasSimboloActual) {
			// regla 2

			posicionA = buscar.getProduccion().indexOf(simbolo);
			tamañoVariable = buscar.getProduccion().size();
			if (posicionA == tamañoVariable - 1) {
				//Regla 3 (Opción 1)
				// Con esto comprobamos que no hay un beta y solamente queda del tipo B -> αA
				temporal = siguiente(buscar.getSimboloGramatical(), gramatica); // siguiente(B)
				for (String recorreTemporal : temporal.getSimbolos()) {
					if (!resultado.getSimbolos().contains(recorreTemporal)) {
						resultado.getSimbolos().add(recorreTemporal);
					}
				}
				buscar.setMarcadoSiguiente(true); // B -> αA (marcado true)

			} else {
				posicionBeta = buscar.getProduccion().indexOf(buscar.getProduccion().get(posicionA + 1));
				contadorBeta = posicionBeta;
				List<String> betaSubarreglo = buscar.getProduccion().subList(contadorBeta, tamañoVariable);
				// recorremos el subarreglo de beta
				// Con ese while comprobamos si lo que tiene mueveBeta en la posicion de la
				// lista es un no terminal
				resultadoPrimero = primero(betaSubarreglo.get(0), gramatica);
				temporal.getSimbolos().addAll(resultadoPrimero.getSimbolos());
				if (temporal.getSimbolos().indexOf("Ɛ") >= 0) {
					temporalSig = siguiente(buscar.getSimboloGramatical(), gramatica); // siguiente(B)
					temporal.getSimbolos().addAll(temporalSig.getSimbolos());
				}
				for (String recorreTemporal : temporal.getSimbolos()) {
					if (!resultado.getSimbolos().contains(recorreTemporal)) {
						resultado.getSimbolos().add(recorreTemporal);
					}
				}
				buscar.setMarcadoSiguiente(true);
			}
			if(!gramatica.getTerminales().contains("Ɛ")){
				for (ReglaProduccion buscando : gramatica.getReglasProduccion()) {
					buscando.setMarcadoSiguiente(false);
				}
			}	
		}

		return resultado;
	}
	public ArrayList<ConjuntoSimbolos> siguientes(ArrayList<String> simbolos, Gramatica gramatica) {
		ArrayList<ConjuntoSimbolos> resultados = new ArrayList<ConjuntoSimbolos>();
		int i = 0;

		for (String mueveSimbolos : simbolos) {
			
			
			ConjuntoSimbolos temporales = new ConjuntoSimbolos();
			temporales = siguiente(mueveSimbolos, gramatica);
			temporales.setId(mueveSimbolos);
			resultados.add(temporales);
			// Primer caso
			/*
			 * if (i == 0) { // resultados.get(i).setId(gramatica.getSimboloInicial()); Por
			 * si las moscas resultados.get(i).getSimbolos().add("$"); } i++;
			 */
			
			for (ReglaProduccion buscando : gramatica.getReglasProduccion()) {
				buscando.setMarcadoSiguiente(false);
			}

		}
		return resultados;
	}
}
