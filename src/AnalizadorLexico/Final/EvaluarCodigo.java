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

	public static void main(String[] args) throws IOException, ExcepcionER {
		ResultadoAnalisisLexico res = EvaluarCodigo.evaluar("src/ArchivosExtra/programa.js");
		System.out.println("algo");
	}

	public static ResultadoAnalisisLexico evaluar(String rutaArchivo) throws IOException, ExcepcionER {
		BufferedReader contenidoArchivo = Archivo.abrirArchivo(rutaArchivo);

		// Cargar expresion regular para reconocer el lexico
		Thompson thomp = new Thompson();
		Archivo file = new Archivo();
		ArrayList<String> expr = file.capturaDatosArchivo("src/ArchivosExtra/ExpresionRegular.txt");
		Automata AFN = thomp.evaluarER(expr.get(1), expr.get(0));
		AutomataDeterminista AFD = ConvierteAFD.convierte(AFN);

		String linea;
		Boolean agregarToken = false;
		Stack<String> palabras = new Stack<String>();
		//palabras.push("");
		while ((linea = contenidoArchivo.readLine()) != null) {
			palabras.push("");
			// Recorrer linea leida
			char[] caracteres = linea.toCharArray();
			for (int i = 0; i < caracteres.length; i++) {
				palabras.push(palabras.pop() + caracteres[i]);
				try {
					if (EvaluarAFD.evaluar(AFD, palabras.peek())) { // correcto
						System.out.println("si está");
					} else { // incorrecto
						System.out.println("no esta");
					}
				} catch (ExcepcionLexico e) { // Simbolo no encontrado
					palabras.push(palabras.pop().replace(e.simbolo, ""));
					// aquí sabe lo que se va a eliminar (omitir) por ahora espacios y tabs
					if (!e.simbolo.equals(" ") && !e.simbolo.equals("	")) {
						//palabras.push(e.simbolo);
						i--;
					}
					if (!palabras.peek().isEmpty())
						palabras.push("");
				}
			}
		}
		contenidoArchivo.close();

		return new ResultadoAnalisisLexico();
	}

}
