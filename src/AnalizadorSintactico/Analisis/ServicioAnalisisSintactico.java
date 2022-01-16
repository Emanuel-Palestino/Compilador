package AnalizadorSintactico.Analisis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import AnalizadorLexico.Final.EvaluarCodigo;
import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.ResultadoAnalisisLexico;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Gramatica.Gramatica;

public class ServicioAnalisisSintactico {

	private Gramatica gramatica;
	private ColeccionCanonica coleccionCanonica;
	private TablaLR tablaAnalisis;
	private ResultadoAnalisisLexico resultadoLexico;
	private ResultadoAnalisisSintactico resultadoSintactico;

	public ServicioAnalisisSintactico() {
		gramatica = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajsFinal.txt");
		coleccionCanonica = ColeccionCanonica.hacer(gramatica);
		tablaAnalisis = TablaLR.construir(coleccionCanonica, gramatica);
		resultadoLexico = new ResultadoAnalisisLexico();
		resultadoSintactico = null;
	}

	public void ejecutar(String rutaPrograma) throws IOException, ExcepcionER {
		resultadoLexico = EvaluarCodigo.evaluar(rutaPrograma);
		resultadoSintactico = AnalisisSintactico.analizar(resultadoLexico.getTiraTokens(), gramatica, tablaAnalisis);

		// nuevoResultado
		int tam = 0;
		for (int i = 0; i < resultadoSintactico.getEntrada().length; i++) {
			if (resultadoSintactico.getEntrada()[i] == null) {
				tam = i;
				break;
			}
		}


		Stack<String>[] pila = new Stack[tam];
		ArrayList<String>[] entrada = new ArrayList[tam];
		String[] accion = new String[tam];

		for (int i = 0; i < tam; i++) {
			pila[i] = resultadoSintactico.getPila()[i];
			entrada[i] = resultadoSintactico.getEntrada()[i];
			accion[i] = resultadoSintactico.getAccion()[i];
		}

		ResultadoAnalisisSintactico resultado = new ResultadoAnalisisSintactico(pila, entrada, accion);
		resultadoSintactico = resultado;
	}

	// Getters
	public Gramatica getGramatica() {
		return gramatica;
	}

	public ColeccionCanonica getColeccionCanonica() {
		return coleccionCanonica;
	}

	public TablaLR getTablaAnalisis() {
		return tablaAnalisis;
	}

	public ResultadoAnalisisLexico getResultadoLexico() {
		return resultadoLexico;
	}

	public ResultadoAnalisisSintactico getResultadoSintactico() {
		return resultadoSintactico;
	}

	// Metodos
}
