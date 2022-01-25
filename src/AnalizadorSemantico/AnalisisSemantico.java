package AnalizadorSemantico;

import java.util.ArrayList;
import java.util.Stack;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ResultadoAnalisisSemantico;
import Utilidades.AnalizadorLexico.Token;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;
import Utilidades.Gramatica.SimboloGramatical;

public class AnalisisSemantico {
	public static ResultadoAnalisisSemantico analizar(ArrayList<Token> tiraTokens, ArrayList<String> tiraTokensString,
			ArrayList<String> tiraTokensSemantico, Gramatica gramatica, TablaLR tablaLR) {
		// declaramos variables locales
		ArrayList<String> accionResultado = new ArrayList<String>();
		Stack<String> pilaResultadoString = new Stack<String>();
		ArrayList<String> entradaResultadoString = new ArrayList<String>();
		ArrayList<String> entradaResultadoSemantico = new ArrayList<String>();

		// Agregar "$" en tira de token
		tiraTokensString.add("$");
		tiraTokensSemantico.add("$");

		ArrayList<String> copiaTokensString = new ArrayList<String>(tiraTokensString);
		ArrayList<String> copiaTokensSemantico = new ArrayList<String>(tiraTokensSemantico);
		Stack<String> copiaPilaString = new Stack<String>();
		Stack<String> pilaString = new Stack<String>();
		Stack<SimboloGramatical> pilaGramatical = new Stack<SimboloGramatical>();
		SimboloGramatical temporalAñadiduras = new SimboloGramatical();
		SimboloGramatical auxiliarIndex = new SimboloGramatical();
		int elementoTopePila;
		int a = 0; // Posicion actual de la tira de tokens
		int index = 0;
		String erroresSintacticos = new String();
		String auxiliarCopia = new String();
		boolean bandera = true;
		boolean banderaAccion = false;

		// inicializamos las pilas en 0
		temporalAñadiduras.setSimboloGramatical("0");
		pilaString.add("0");
		pilaGramatical.add(temporalAñadiduras);

		// Agregamos los tokens como los recibimos en las entradasResultados
		entradaResultadoString.add(copiaTokensString.toString());
		entradaResultadoSemantico.add(copiaTokensSemantico.toString());

		while (bandera) {
			int indiceTemp = Integer.parseInt(pilaString.peek());
			String temp = tablaLR.getAcciones().get(indiceTemp).get(tiraTokensString.get(a));
			if (temp == null || temp == "") {
				temp = "e";
			}
			String num = temp.substring(1);

			char operacionSwitch = temp.charAt(0);
			switch (operacionSwitch) {
				case 'd':
					// Parte String
					index = Integer.parseInt(num);
					accionResultado.add("d" + index);
					copiaPilaString = (Stack<String>) pilaString.clone();// Copia pila string
					pilaResultadoString.add(copiaPilaString.toString());
					if (!tiraTokens.get(a).esPalabraReservada()) {
						// Si no es una palabra reservada le copiamos con punto y su valor lexico a la
						// pila
						auxiliarCopia = tiraTokensString.get(a) + ".'" + tiraTokens.get(a).getValorLexico() + "'";
						pilaString.add(auxiliarCopia);
					} else {
						// Si es una palabra reservada solo le copiamos lo que este en la pilaString
						pilaString.add(tiraTokensString.get(a));
					}
					pilaString.push(String.valueOf(index));
					copiaTokensString.set(a, "");
					entradaResultadoString.add(copiaTokensString.toString());

					// Parte Objeto
					temporalAñadiduras = new SimboloGramatical(tiraTokens.get(a));
					pilaGramatical.push(temporalAñadiduras);
					auxiliarIndex = new SimboloGramatical(String.valueOf(index));
					pilaGramatical.push(auxiliarIndex);

					// Manipulamos la arrayList de resultado
					copiaTokensSemantico.set(a, "");
					entradaResultadoSemantico.add(copiaTokensSemantico.toString());

					a++;
					break;

				case 'r': /* reducir A -> β */
					index = Integer.parseInt(num);
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index - 1); // f -> id
					// colocamos la regla de produccion y el r + indice
					accionResultado.add(imprimirAccion(rProduccion, index)); // Aqui llamamos al metodo donde
																				// concatenara la acción y la regla de
																				// produccion
																				// La parte comentada del metodo es
																				// donde esta la accion Semantica
					// parte String
					copiaPilaString = (Stack<String>) pilaString.clone();
					pilaResultadoString.add(copiaPilaString.toString());
					entradaResultadoString.add(copiaTokensString.toString());

					// Manipulamos la arrayList de resultado
					entradaResultadoSemantico.add(copiaTokensSemantico.toString());

					if (rProduccion.getProduccion().get(0).equals("Ɛ")) {
						elementoTopePila = Integer.parseInt(pilaString.lastElement());
						banderaAccion = true;
					} else {
						int tamañoPop = rProduccion.getProduccion().size() * 2;
						for (int i = 0; i < tamañoPop; i++) {
							pilaString.pop();
						}
						elementoTopePila = Integer.parseInt(pilaString.lastElement()); // elemento del tope de la pila
																						// antes del pop
					}


					// parte Objeto
					SimboloGramatical[] simbolosProduccion = new SimboloGramatical[0];
					if (banderaAccion == false) { // Si la condición del if de arriba no se cumple
						int tamañoPop = rProduccion.getProduccion().size() * 2;
						// Obtener todos los simbolosGramaticales
						simbolosProduccion = new SimboloGramatical[tamañoPop];

						for (int i = 0; i < tamañoPop; i++) {
							simbolosProduccion[i] = pilaGramatical.pop();
						}
						elementoTopePila = Integer.parseInt(pilaGramatical.lastElement().getSimboloGramatical());
					} else {
						elementoTopePila = Integer.parseInt(pilaGramatical.lastElement().getSimboloGramatical());
					}

					String s = tablaLR.getIrA().get(elementoTopePila).get(rProduccion.getStringSimboloGramatical());

					pilaGramatical.push(rProduccion.evaluarAccionSemantica(simbolosProduccion));
					pilaGramatical.push(new SimboloGramatical(s));

					pilaString.push(rProduccion.getStringSimboloGramatical() + ".\"" + rProduccion.getObjetoSimboloGramatical().getTraduccion() + "\"");
					pilaString.push(s);

					banderaAccion = false;
					break;

				case 'a':
					accionResultado.add("Aceptar");
					// parte String
					copiaPilaString = (Stack<String>) pilaString.clone();
					pilaResultadoString.add(copiaPilaString.toString());
					// parte objeto

					bandera = false;
					break;

				default:
					// parte String
					entradaResultadoString.add(copiaTokensString.toString());

					// Manipulamos la arrayList de resultado
					entradaResultadoSemantico.add(copiaTokensSemantico.toString());

					pilaResultadoString.add(copiaPilaString.toString());

					for (String simboloTerminal : gramatica.getTerminales()) {
						if (!tablaLR.getAcciones().get(index).get(simboloTerminal).equals("$")) {
							erroresSintacticos += simboloTerminal + " ó ";
						}
					}
					accionResultado.add("Error sintáctico, se esperaba: " + erroresSintacticos);
					bandera = false;
					break;
			}
		}
		// Sacamos el ultimo estado de la pila<SimboloGramatical>

		return new ResultadoAnalisisSemantico(pilaResultadoString, entradaResultadoSemantico, accionResultado);
	}

	static private String imprimirAccion(ReglaProduccion reglaProduccionImprimir, int index) {
		String temp = new String();
		temp = "r" + index + " " + reglaProduccionImprimir.getStringSimboloGramatical() + "»" + reglaProduccionImprimir
				.getProduccion();
		// Accion semantica
		temp += "  { " + reglaProduccionImprimir.getAccionSemantica().getAccion() + " }";
		return temp;
	}

}
