package AnalizadorSintactico.Analisis;

import java.util.ArrayList;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Gramatica.Gramatica;

public class AnalisisSintactico {
	public static ResultadoAnalisisSintactico analizar(ArrayList<String> tiraTokens, Gramatica gramatica, TablaLR tablaLR) {
		return new ResultadoAnalisisSintactico(null, null, null);
	}
}
