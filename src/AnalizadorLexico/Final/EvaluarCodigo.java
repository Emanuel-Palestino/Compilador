package AnalizadorLexico.Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import AnalizadorLexico.AlgoritmoThompson.Thompson;
import AnalizadorLexico.ConversionAFD.ConvierteAFD;
import Utilidades.Archivo;
import Utilidades.AnalizadorLexico.Error;
import Utilidades.AnalizadorLexico.ResultadoAnalisisLexico;
import Utilidades.AnalizadorLexico.Simbolo;
import Utilidades.AnalizadorLexico.Token;
import Utilidades.Automatas.Automata;
import Utilidades.Automatas.AutomataDeterminista;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Excepciones.ExcepcionLexico;

public class EvaluarCodigo {

	public static ResultadoAnalisisLexico evaluar(String rutaArchivo) throws IOException, ExcepcionER {
		BufferedReader contenidoArchivo = Archivo.abrirArchivo(rutaArchivo);
		ResultadoAnalisisLexico resultado = new ResultadoAnalisisLexico();

		// Cargar expresion regular para reconocer el lexico
		Thompson thomp = new Thompson();
		ArrayList<String> expr = Archivo.capturaDatosArchivo("src/ArchivosExtra/ExpresionRegular.txt");
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
		//ArrayList<ArrayList<String>> tiraTokens = new ArrayList<ArrayList<String>>();
		ArrayList<Token> tiraTokens = new ArrayList<Token>();
		ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
		ArrayList<Error> errores = new ArrayList<Error>();

		String linea;
		Boolean comentarioMultiLinea = false;
		Stack<String> palabras = new Stack<String>();
		int numLinea = 0;
		palabras.push("");
		while ((linea = contenidoArchivo.readLine()) != null) {
			numLinea++;
			// Descartar comentario multilinea y de linea
			// Encontrar comentario de una linea y quitarlo
			String[] comentario = linea.split("//");
			linea = comentario[0];
			// Encontrar comentario multilinea
			// Saber si acaba algún comentario multilinea
			int lineaComentario = linea.indexOf("*/");
			if (comentarioMultiLinea && lineaComentario >= 0) {
				linea = linea.substring(lineaComentario + 2, linea.length()); // Actualizamos la linea
				comentarioMultiLinea = false;
			}

			// Saber si empieza un comentario multilinea
			lineaComentario = linea.indexOf("/*");
			if (!comentarioMultiLinea && lineaComentario >= 0) { // Existe comentario
				comentarioMultiLinea = true;
				String lineaAux = linea.substring(0, lineaComentario);
				// Saber si termina en esta linea o en otra
				lineaComentario = linea.lastIndexOf("*/");
				if (lineaComentario >= 0) {
					lineaAux += linea.substring(lineaComentario + 2, linea.length());
					comentarioMultiLinea = false;
				}

				linea = lineaAux;
			}

			if (!palabras.peek().equals(""))
				palabras.push("");
			// Recorrer linea leida
			for (int i = 0; i < linea.length(); i++) {
				palabras.push(palabras.pop() + linea.charAt(i));
				try {
					if (EvaluarAFD.evaluar(AFD, palabras.peek())) { // correcto
						// Agregar ultimo token de la linea
						if (i == linea.length() - 1) {
							// Rellenar resultados
							rellenarResultados(palabras.peek(), numLinea, palabrasRerservadas, tiraTokens, simbolos);
						}
					} else { // incorrecto
						System.out.println("no esta");
					}
				} catch (ExcepcionLexico e) { // Simbolo no encontrado
					palabras.push(palabras.pop().replace(e.simbolo, ""));

					// Rellenar resultados
					rellenarResultados(palabras.peek(), numLinea, palabrasRerservadas, tiraTokens, simbolos);

					// aquí sabe lo que se va a eliminar (omitir) por ahora espacios y tabs
					if (!e.simbolo.equals(" ") && !e.simbolo.equals("	")) {
						i--;
						// Si hay algun simbolo no valido, agregar a la tabla de errores
						try {
							EvaluarAFD.evaluar(AFD, e.simbolo);
						} catch (ExcepcionLexico e1) {
							// Eliminar de la cadena
							palabras.pop();
							linea = linea.replaceFirst(e.simbolo, "");

							// Agregar a Errores
							Error filaErrores = new Error();
							filaErrores.setLinea(numLinea);
							filaErrores.setDescripcion("Error: símbolo " + e.simbolo + " no definido.");
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

		resultado.setTokens(tiraTokens);
		resultado.setSimbolos(simbolos);
		resultado.setErrores(errores);

		return resultado;
	}

	private static void rellenarResultados(String lexema, int numLinea, ArrayList<String> palabrasRerservadas,
			ArrayList<Token> tiraTokens, ArrayList<Simbolo> simbolos) {

		if (!lexema.equals("")) {
			Token fila = new Token();

			// linea
			fila.setLinea(numLinea);

			// lexema
			fila.setLexema(lexema);

			// saber qué representa (token)
			if (palabrasRerservadas.contains(lexema)) { // Es una palabra reservada
				fila.setToken(lexema);
			} else if (lexema.matches("-?\\d+(\\.\\d+)?")) { // Es un numero, ya sea entero o flotante
				fila.setToken("num");
			} else if (lexema.matches("(\"(.*)\")|('(.*)')")) { // Es un string
				fila.setToken("string");
			} else if (lexema.matches("[a-zA-Z]*(\\w+)")) { // Es un id
				fila.setToken("id");

				// Agregar a la tabla de simbolos
				Simbolo simbolo = new Simbolo(lexema, "", "");

				simbolos.add(simbolo);
			} else {
				fila.setToken("desconocido");
			}

			tiraTokens.add(fila);
		}

	}

}
