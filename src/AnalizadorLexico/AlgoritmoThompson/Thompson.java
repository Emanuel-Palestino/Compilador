package AnalizadorLexico.AlgoritmoThompson;

import java.util.Stack;

import Utilidades.Automata;
import Utilidades.Postfija;
import Utilidades.Alfabeto.Alfabeto;

public class Thompson {

	public Thompson() {
	};

	// Metodos
	public Automata evaluarER(String expR, String cadenaAlfabeto) {
		Postfija post = new Postfija();
		String postfija;

		// Crear Alfabeto
		int cantidadSimbolos = cadenaAlfabeto.length();
		String[] simbolosAlfabeto = new String[cantidadSimbolos];
		for (int i = 0; i < cantidadSimbolos; i++) {
			simbolosAlfabeto[i] = "" + cadenaAlfabeto.charAt(i);
		}
		Alfabeto alfabeto = new Alfabeto(simbolosAlfabeto);

		// Transformar Expresion regular a su forma postfija
		postfija = post.crear(expR, alfabeto);

		// Pila de Operandos y Operandos Automatas
		Stack<String> operandos = new Stack<String>();
		Stack<Automata> operandosAutomatas = new Stack<Automata>();

		// Algoritmo para evaluar la expresion postfija
		int tamaño = postfija.length();
		String simbolo = "";
		for (int i = 0; i < tamaño; i++) {
			simbolo = "" + postfija.charAt(i);
			if (alfabeto.simboloValido(simbolo)) {
				operandos.push(simbolo);
				operandosAutomatas.push(new Automata(simbolo));
			} else {
				Automata resultadoOperacion = new Automata();

				// EVALUACION

				if (operadorBinario(simbolo)) { // Evaluacion para operadores binarios
					Automata operando1;
					Automata operando2;
					operando2 = operandosAutomatas.pop();
					operando1 = operandosAutomatas.pop();
					operandos.pop();
					operandos.pop();

					// Realizar operaciones
					switch (simbolo) {
					case "ª":
						resultadoOperacion = concatenacion(operando1, operando2);
						break;
					case "|":
						resultadoOperacion = union(operando1, operando2);
						break;
					}

				} else { // Evaluacion para operadores unarios
					Automata operando1 = operandosAutomatas.pop();
					operandos.pop();

					// Realizar operaciones
					switch (simbolo) {
					case "*":
						resultadoOperacion = cerraduraKlenee(operando1);
						break;
					case "+":
						resultadoOperacion = cerraduraPositiva(operando1);
						break;
					case "?":
						resultadoOperacion = cerraduraOpcional(operando1);
						break;
					}

				}
				operandosAutomatas.push(resultadoOperacion);
				operandos.push("a");
			}
			operandos.peek();
		}

		return operandosAutomatas.pop();
	}

	public Automata concatenacion(Automata A1, Automata A2) {
		int totalEstados = A1.getNumEstados() + A2.getNumEstados() - 1;
		Automata resultado = new Automata(totalEstados);
		Automata aux;

		// Agregar el contenido de A1 al inicio del Resultado
		aux = new Automata(A1);
		int numEstadosA1 = aux.getNumEstados();
		for (int i = 0; i < numEstadosA1; i++) {
			resultado.insertarTransiciones(i, aux.getTransiciones(i));
		}

		// Concatenar el contenido de A2 al Resultado
		aux = new Automata(A2);
		int numEstadosA2 = aux.getNumEstados();
		for (int i = 0; i < numEstadosA2; i++) {
			try {
				aux.getTransiciones(i).incrementarEstadosDestino(numEstadosA1 - 1);
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
			resultado.insertarTransiciones(i + numEstadosA1 - 1, aux.getTransiciones(i));
		}

		return resultado;
	}

	public Automata union(Automata A1, Automata A2) {
		int totalEstados = A1.getNumEstados() + A2.getNumEstados() + 2;
		Automata resultado = new Automata(totalEstados);
		Automata aux;

		resultado.insertarTransicion(0, 1, "Ɛ");
		resultado.insertarTransicion(0, A1.getNumEstados() + 1, "Ɛ");

		// Agregar el contenido de A1 al Resultado
		aux = new Automata(A1);
		int numEstadosA1 = aux.getNumEstados();
		for (int i = 0; i < numEstadosA1; i++) {
			try {
				aux.getTransiciones(i).incrementarEstadosDestino(1);
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
			resultado.insertarTransiciones(i + 1, aux.getTransiciones(i));
		}

		// Agregar transicion al estado final
		resultado.insertarTransicion(aux.getNumEstados(), totalEstados - 1, "Ɛ");

		// Agregar el contenido de A2 al Resultado
		aux = new Automata(A2);
		int numEstadosA2 = aux.getNumEstados();
		for (int i = 0; i < numEstadosA2; i++) {
			try {
				aux.getTransiciones(i).incrementarEstadosDestino(numEstadosA1 + 1);
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
			resultado.insertarTransiciones(i + numEstadosA1 + 1, aux.getTransiciones(i));
		}

		// Agregar transicion al estado final
		resultado.insertarTransicion(totalEstados - 2, totalEstados - 1, "Ɛ");

		return resultado;
	}

	public Automata cerraduraKlenee(Automata A) {
		int totalEstados = A.getNumEstados() + 2;
		Automata resultado = new Automata(totalEstados);
		Automata aux;

		// Agregar transiciones iniciales
		resultado.insertarTransicion(0, 1, "Ɛ");
		resultado.insertarTransicion(0, totalEstados - 1, "Ɛ");

		// Agregar el contenido de A al Resultado
		aux = new Automata(A);
		int numEstadosA = aux.getNumEstados();
		for (int i = 0; i < numEstadosA; i++) {
			try {
				aux.getTransiciones(i).incrementarEstadosDestino(1);
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
			resultado.insertarTransiciones(i + 1, aux.getTransiciones(i));
		}

		// Agregar transiciones finales
		resultado.insertarTransicion(numEstadosA, totalEstados - 1, "Ɛ");
		resultado.insertarTransicion(numEstadosA, 1, "Ɛ");

		return resultado;
	}

	public Automata cerraduraPositiva(Automata A) {
		int totalEstados = A.getNumEstados() + 2;
		Automata resultado = new Automata(totalEstados);
		Automata aux;

		// Agregar transiciones iniciales
		resultado.insertarTransicion(0, 1, "Ɛ");

		// Agregar el contenido de A al Resultado
		aux = new Automata(A);
		int numEstados = aux.getNumEstados();
		for (int i = 0; i < numEstados; i++) {
			try {
				aux.getTransiciones(i).incrementarEstadosDestino(1);
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
			resultado.insertarTransiciones(i + 1, aux.getTransiciones(i));
		}

		// Agregar transiciones finales
		resultado.insertarTransicion(numEstados, totalEstados - 1, "Ɛ");
		resultado.insertarTransicion(numEstados, 1, "Ɛ");

		return resultado;
	}

	public Automata cerraduraOpcional(Automata A) {
		int totalEstados = A.getNumEstados() + 2;
		Automata resultado = new Automata(totalEstados);
		Automata aux;

		// Agregar transiciones iniciales
		resultado.insertarTransicion(0, 1, "Ɛ");
		resultado.insertarTransicion(0, totalEstados - 1, "Ɛ");

		// Agregar el contenido de A al Resultado
		aux = new Automata(A);
		int numEstadosA = aux.getNumEstados();
		for (int i = 0; i < numEstadosA; i++) {
			try {
				aux.getTransiciones(i).incrementarEstadosDestino(1);
			} catch (NullPointerException e) {
				System.out.println("Advertencia El estado (" + i + ") no contiene transiciones.");
			}
			resultado.insertarTransiciones(i + 1, aux.getTransiciones(i));
		}

		// Agregar transiciones finales
		resultado.insertarTransicion(numEstadosA, totalEstados - 1, "Ɛ");

		return resultado;

	}

	private boolean operadorBinario(String op) {
		boolean resultado;
		switch (op) {
		case "*":
		case "+":
		case "?":
			resultado = false;
			break;
		default:
			resultado = true;
			break;
		}
		return resultado;
	}
}
