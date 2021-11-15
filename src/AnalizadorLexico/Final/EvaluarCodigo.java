package AnalizadorLexico.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import AnalizadorLexico.AlgoritmoThompson.Thompson;
import AnalizadorLexico.ConversionAFD.ConvierteAFD;
import Utilidades.Archivo;
import Utilidades.Automata;
import Utilidades.AutomataDeterminista;
import Utilidades.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Excepciones.ExcepcionLexico;

public class EvaluarCodigo {

	public static ResultadoAnalisisLexico evaluar(String rutaArchivo) throws IOException, ExcepcionER {
		BufferedReader contenidoArchivo = Archivo.abrirArchivo(rutaArchivo);
		ResultadoAnalisisLexico resultado = new ResultadoAnalisisLexico();

		// Cargar expresion regular para reconocer el lexico
		Thompson thomp = new Thompson();
		Archivo file = new Archivo();
		ArrayList<String> expr = file.capturaDatosArchivo("src/ArchivosExtra/ExpresionRegular.txt");
		Automata AFN = thomp.evaluarER(expr.get(1), expr.get(0));
		AutomataDeterminista AFD = ConvierteAFD.convierte(AFN);

		// Cargar lista de Palabras Resevadas
		ArrayList<String> palabrasRerservadas = new ArrayList<String>();
		BufferedReader contenidoPReservadas = Archivo.abrirArchivo("src/ArchivosExtra/PalabrasReservadasJS.txt");
		String reservada;
		while ((reservada = contenidoPReservadas.readLine()) != null) {
			palabrasRerservadas.add(reservada);
		}
		contenidoPReservadas.close();

		// Variables auxiliares
		ArrayList<ArrayList<String>> tiraTokens = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> simbolos = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> errores = new ArrayList<ArrayList<String>>();

		String linea;
		Boolean agregarUltimoToken = false;
		Stack<String> palabras = new Stack<String>();
		int numLinea = 0;
		palabras.push("");
		while ((linea = contenidoArchivo.readLine()) != null) {
			numLinea++;
			if (!palabras.peek().equals(""))
				palabras.push("");
			// Recorrer linea leida
			for (int i = 0; i < linea.length(); i++) {
				palabras.push(palabras.pop() + linea.charAt(i));
				try {
					if (EvaluarAFD.evaluar(AFD, palabras.peek())) { // correcto
						// Agregar ultimo token de la linea
						if (i == linea.length() - 1 && agregarUltimoToken) {
							// Rellenar resultados
							String lexema = palabras.peek();
							if (!lexema.equals("")) {
								ArrayList<String> fila = new ArrayList<String>();
								// linea
								fila.add("" + numLinea);
								// tokensLinea.add("" + numLinea);
								// lexema
								fila.add(lexema);
								// tokensLexema.add(lexema);
								// saber qué representa (token)
								if (palabrasRerservadas.contains(lexema)) { // Es una palabra reservada
									fila.add(lexema);
									// tokensToken.add(lexema);
								} else if (lexema.matches("-?\\d+(\\.\\d+)?")) { // Es un numero, ya sea entero o
																					// flotante y
									fila.add("num");
									// tokensToken.add("num");
								} else if (lexema.matches("(\"(\\w*)\")|('(\\w*)')")) { // Es un string
									fila.add("string");
									// tokensToken.add("string");
								} else if (lexema.matches("[a-zA-Z](\\w+)")) { // Es un id
									fila.add("id");
									// tokensToken.add("id");
									// Agregar a la tabla de simbolos
									ArrayList<String> filaSimbolos = new ArrayList<String>();
									filaSimbolos.add(lexema);
									// simbolosId.add(lexema);
									// no se cómo obtener estos valores
									filaSimbolos.add("");
									filaSimbolos.add("");
									// simbolosValor.add("");
									// simbolosFuncion.add("");
									simbolos.add(filaSimbolos);
								} else {
									fila.add("desconocido");
									// tokensToken.add("desconocido");
								}
								tiraTokens.add(fila);
							}

						}
					} else { // incorrecto
						System.out.println("no esta");
					}
				} catch (ExcepcionLexico e) { // Simbolo no encontrado
					palabras.push(palabras.pop().replace(e.simbolo, ""));
					// bandera para agregar el ultimo token de la linea
					agregarUltimoToken = true;

					// Rellenar resultados
					String lexema = palabras.peek();
					if (!lexema.equals("")) {
						ArrayList<String> fila = new ArrayList<String>();
						// linea
						fila.add("" + numLinea);
						// tokensLinea.add("" + numLinea);
						// lexema
						fila.add(lexema);
						// tokensLexema.add(lexema);
						// saber qué representa (token)
						if (palabrasRerservadas.contains(lexema)) { // Es una palabra reservada
							fila.add(lexema);
							// tokensToken.add(lexema);
						} else if (lexema.matches("-?\\d+(\\.\\d+)?")) { // Es un numero, ya sea entero o flotante y
							fila.add("num");
							// tokensToken.add("num");
						} else if (lexema.matches("(\"(\\w*)\")|('(\\w*)')")) { // Es un string
							fila.add("string");
							// tokensToken.add("string");
						} else if (lexema.matches("[a-zA-Z](\\w+)")) { // Es un id
							fila.add("id");
							// tokensToken.add("id");
							// Agregar a la tabla de simbolos
							ArrayList<String> filaSimbolos = new ArrayList<String>();
							filaSimbolos.add(lexema);
							// simbolosId.add(lexema);
							// no se cómo obtener estos valores
							filaSimbolos.add("");
							filaSimbolos.add("");
							// simbolosValor.add("");
							// simbolosFuncion.add("");
							simbolos.add(filaSimbolos);
						} else {
							fila.add("desconocido");
							// tokensToken.add("desconocido");
						}
						tiraTokens.add(fila);
					}

					// aquí sabe lo que se va a eliminar (omitir) por ahora espacios y tabs
					if (!e.simbolo.equals(" ") && !e.simbolo.equals("	")) {
						i--;
						// Si hay algun simbolo no valido, agregar a la tabla de errores
						if (!AFN.getAlfabeto().simboloValido(e.simbolo)) {
							// Eliminar de la cadena
							palabras.pop();
							linea = linea.replaceFirst(e.simbolo, "");
							// Agregar
							ArrayList<String> filaErrores = new ArrayList<String>();
							filaErrores.add("" + numLinea);
							filaErrores.add("Error: símbolo " + e.simbolo + " no definido.");
							// erroresLinea.add("" + numLinea);
							// erroresDescripcion.add("Error: símbolo " + e.simbolo + " no definido.");
							errores.add(filaErrores);
						}
					}

					// Agrega elemento vacio
					if (!palabras.peek().isEmpty()) {
						palabras.push("");
					}
				}

			}
		}
		contenidoArchivo.close();

		// Rellenar los Resultados
		String[][] tiraTokensArr = new String[tiraTokens.size()][3];
		for (int i = 0; i < tiraTokens.size(); i++) {
			for (int j = 0; j < 3; j++) {
				tiraTokensArr[i][j] = tiraTokens.get(i).get(j);
			}
		}
		String[][] simbolosArr = new String[simbolos.size()][3];
		for (int i = 0; i < simbolos.size(); i++) {
			for (int j = 0; j < 3; j++) {
				simbolosArr[i][j] = simbolos.get(i).get(j);
			}
		}
		String[][] erroresArr = new String[errores.size()][2];
		for (int i = 0; i < errores.size(); i++) {
			for (int j = 0; j < 2; j++) {
				erroresArr[i][j] = errores.get(i).get(j);
			}
		}

		resultado.setTokens(tiraTokensArr);
		resultado.setSimbolos(simbolosArr);
		resultado.setErrores(erroresArr);

		return resultado;
	}

}
