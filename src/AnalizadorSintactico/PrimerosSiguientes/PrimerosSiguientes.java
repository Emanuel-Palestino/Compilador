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

		// Se hace siguientes
		resultado.setSiguientes(siguientes(gramatica.getNoTerminales(), gramatica));

		return resultado;
	}

	public ConjuntoSimbolos siguiente(String simbolo, Gramatica gramatica) {
		ConjuntoSimbolos resultado = new ConjuntoSimbolos();

		// Asignar id al conjunto
		resultado.setId(simbolo);

		// Primer regla
		if (gramatica.getSimboloInicial().equals(simbolo)) {
			resultado.agregarSimbolo("$");
		}

		// Obtener las reglas de produccion donde el simbolo esté en la produccion
		ArrayList<ReglaProduccion> reglasGramatica = gramatica.getReglasProduccion();
		ArrayList<ReglaProduccion> reglasSimboloProduccion = new ArrayList<ReglaProduccion>();
		for (ReglaProduccion regla : reglasGramatica) {
			if (regla.getProduccion().contains(simbolo)) {
				reglasSimboloProduccion.add(regla);
			}
		}

		// Hacer todas las reglas encontradas
		for (ReglaProduccion regla : reglasSimboloProduccion) {
			ArrayList<String> produccionRegla = regla.getProduccion();
			int indexSimbolo = produccionRegla.indexOf(simbolo);

			// Segunda regla
			if (indexSimbolo != produccionRegla.size() - 1) {
				// Obtener simbolos de Beta
				ArrayList<String> simbolosBeta = new ArrayList<String>();
				for (int i = indexSimbolo + 1; i < produccionRegla.size(); i++) {
					simbolosBeta.add(produccionRegla.get(i));
				}
				ArrayList<ConjuntoSimbolos> primeroBeta = primeros(simbolosBeta, gramatica);
				// Agregar simbolos de Primeros excepto epsilon
				primeroBeta.get(0).getSimbolos().remove("Ɛ");
				for (String simboloPrimero : primeroBeta.get(0).getSimbolos()) {
					if (!resultado.contiene(simboloPrimero))
						resultado.agregarSimbolo(simboloPrimero);
				}
			}

			// Tercera regla primera parte
			if (indexSimbolo == produccionRegla.size() - 1) {
				ArrayList<String> simboloB = new ArrayList<String>();
				simboloB.add(regla.getSimboloGramatical());

				// comprobar que el simbolo no sea el mismo que el simbolo gramatical
				if (!simboloB.get(0).equals(simbolo)) {
					ArrayList<ConjuntoSimbolos> siguienteB = siguientes(simboloB, gramatica);
					// Agregar simbolos de Siguientes
					for (String simboloPrimero : siguienteB.get(0).getSimbolos()) {
						if (!resultado.contiene(simboloPrimero))
							resultado.agregarSimbolo(simboloPrimero);
					}
				}
			}

			// Tercera regla segunda parte
			if (indexSimbolo != produccionRegla.size() - 1) {
				ArrayList<String> simbolosBeta = new ArrayList<String>();
				for (int i = indexSimbolo + 1; i < produccionRegla.size(); i++) {
					simbolosBeta.add(produccionRegla.get(i));
				}
				ArrayList<ConjuntoSimbolos> primeroBeta = primeros(simbolosBeta, gramatica);
				// Agregar los siguientes de B si existe epsilon en Primeros de Beta
				if (primeroBeta.get(0).getSimbolos().contains("Ɛ")) {
					ArrayList<String> simboloB = new ArrayList<String>();
					simboloB.add(regla.getSimboloGramatical());
					// comprobar que el simbolo no sea el mismo que el simbolo gramatical
					if (!simboloB.get(0).equals(simbolo)) {
						ArrayList<ConjuntoSimbolos> siguienteB = siguientes(simboloB, gramatica);
						// Agregar simbolos de Siguientes
						for (String simboloPrimero : siguienteB.get(0).getSimbolos()) {
							if (resultado.contiene(simboloPrimero))
								resultado.agregarSimbolo(simboloPrimero);
						}
					}
				}
			}

		}

		return resultado;
	}

	public ArrayList<ConjuntoSimbolos> siguientes(ArrayList<String> simbolos, Gramatica gramatica) {
		ArrayList<ConjuntoSimbolos> resultados = new ArrayList<ConjuntoSimbolos>();

		for (String simbolo : simbolos) {
			ConjuntoSimbolos resultadoSiguiente = new ConjuntoSimbolos();
			resultadoSiguiente = siguiente(simbolo, gramatica);
			resultadoSiguiente.setId(simbolo);
			resultados.add(resultadoSiguiente);
		}

		return resultados;
	}

	public ArrayList<ConjuntoSimbolos> primeros(ArrayList<String> simbolos, Gramatica gramatica) {

		ArrayList<ConjuntoSimbolos> salida = new ArrayList<ConjuntoSimbolos>();
		// Para cada no terminal
		for (String valor : simbolos) {
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
				if (reglaProduccion.size() == 1 && reglaProduccion.get(0).equals("Ɛ")) {
					primeros.getSimbolos().add(reglaProduccion.get(0));
					return primeros;
				} else if (reglaProduccion.size() == 1) {
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