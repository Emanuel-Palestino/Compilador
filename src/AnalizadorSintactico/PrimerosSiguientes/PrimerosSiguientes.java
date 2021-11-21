package AnalizadorSintactico.PrimerosSiguientes;

import java.util.ArrayList;

import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class PrimerosSiguientes {

	public ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {
		ResultadoPrimerosSiguientes resultado = new ResultadoPrimerosSiguientes();
		// Se hace primeros
		resultado.setPrimeros(primeros(gramatica.getNoTerminales(), gramatica));

		return resultado;
	}

	public ArrayList<ConjuntoSimbolos> primeros(ArrayList<String> simbolos, Gramatica gramatica) {

		ArrayList<ConjuntoSimbolos> salida = new ArrayList<ConjuntoSimbolos>();

		// Para cada no terminal
		for (String valor : gramatica.getNoTerminales()) {
			ConjuntoSimbolos aux = new ConjuntoSimbolos();
			aux = primero(valor, gramatica);
			aux.setId(valor);
			salida.add(aux);
			for (ReglaProduccion recorre : gramatica.getReglasProduccion()) {

				recorre.setMarcado(false);
			}

		}
		return salida;

	}

	public ConjuntoSimbolos primero(String simbolo, Gramatica gramatica) {

		ConjuntoSimbolos primeros = new ConjuntoSimbolos();

		// Si simbolos es un terminal, el PRIMER conjunto es él mismo
		// Aquí, la cadena vacía también se incluye en el símbolo del terminal
		if (gramatica.esTerminal(simbolo)) {
			primeros.getSimbolos().add(simbolo);
			return primeros;
		} else {

			// De lo contrario, es igual a la suma de los PRIMEROS conjuntos de símbolos de
			// la derecha
			// Extraer las reglas de produccion desde gramatica
			ArrayList<ReglaProduccion> produccion = new ArrayList<ReglaProduccion>();
			ArrayList<ArrayList<String>> elementosDerecha = new ArrayList<ArrayList<String>>();

			produccion = gramatica.getReglasProduccion();
			for (ReglaProduccion regla : produccion) { // For each para recorrer las reglas de produccion

				// si la regla de produccion tiene simbolo igual que el simbolo que se analiza y
				// no esta marcada se asigna a elementos derecha
				if (regla.getSimboloGramatical().equals(simbolo) && regla.getMarcado() == false) {
					elementosDerecha.add(regla.getProduccion());
					regla.setMarcado(true);
				}
			}

			// Pasa por cada elemento de la derecha
			for (ArrayList<String> regla : elementosDerecha) {

				ArrayList<String> reglaProduccion = regla;

				// Si la produccion de la derecha es un solo simbolo, se agrega directamente al
				// primer conjunto
				if (reglaProduccion.size() == 1) {
					primeros.getSimbolos().addAll(primero(reglaProduccion.get(0), gramatica).getSimbolos());
				} else {

					// Si hay varios simbolos a la derecha, mirar al primer simbolo de la cadena
					for (int i = 0; i < reglaProduccion.size(); i++) {

						String caracter = reglaProduccion.get(i);

						// Si es un terminal, agregar directamente a la primera colección
						if (gramatica.esTerminal(caracter)) {
							primeros.getSimbolos().add(caracter);
							break;
						} else {
							// Si es un símbolo no terminal, calcule su PRIMER conjunto

							ArrayList<String> cPrimeros = primero(caracter, gramatica).getSimbolos();
							// Si hay una cadena vacía, es necesario continuar calculando el PRIMER conjunto
							// del siguiente símbolo
							if (cPrimeros.contains("Ɛ")) {
								// Si este es el último símbolo
								// Es necesario agregar la página de cadena vacía a la primera colección
								if (i == cPrimeros.size() - 1) {
									primeros.getSimbolos().addAll(cPrimeros);

								} else {
									cPrimeros.remove("Ɛ");
									primeros.getSimbolos().addAll(cPrimeros);

								}
							} else {
								// Si no contiene una cadena vacía, no es necesario continuar con el cálculo
								primeros.getSimbolos().addAll(cPrimeros);
								break;
							}
						}
					}
				}
			}
		}
		return primeros;
	}
}
