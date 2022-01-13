package AnalizadorSintactico.PrimerosSiguientes;

import java.util.ArrayList;

import javax.swing.JFrame;

import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class IniciarPrimerosSiguientes {

	public IniciarPrimerosSiguientes(JFrame parent) {
		PrimerosSiguientes ps = new PrimerosSiguientes();
		// Cargar gramatica
		Gramatica grama = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajs10.txt");
		ResultadoPrimerosSiguientes resultado = ps.hacer(grama);

		// pasarle los datos a la ventana
		String noTerminales, terminales, simboloInicial, gramatica, primeros, siguientes;
		ArrayList<String> aux = new ArrayList<String>();

		// No Terminales
		aux = grama.getNoTerminales();
		noTerminales = "";
		for (String simbolo : aux) {
			noTerminales += simbolo + " ";
		}

		// Terminales
		aux = grama.getTerminales();
		terminales = "";
		for (String simbolo : aux) {
			terminales += simbolo + " ";
		}

		// Simbolo Inicial
		simboloInicial = grama.getSimboloInicial();

		// Gramatica
		gramatica = "";
		ArrayList<ReglaProduccion> reglas = grama.getReglasProduccion();
		for (ReglaProduccion regla : reglas) {
			gramatica += regla.getSimboloGramatical() + " -> ";
			aux = regla.getProduccion();
			for (String simbolo : aux)
				gramatica += simbolo + " ";
			gramatica += "\n";
		}

		// Primeros
		primeros = "";
		ArrayList<ConjuntoSimbolos> conjuntoSimbolos = resultado.getPrimeros();
		for (ConjuntoSimbolos simbolos : conjuntoSimbolos) {
			primeros += "P(" + simbolos.getId() + ") = { ";
			aux = simbolos.getSimbolos();
			for (String simbolo : aux)
				primeros += simbolo + " ";
			primeros += "}\n";
		}

		// Siguientes
		siguientes = "";
		conjuntoSimbolos = resultado.getSiguientes();
		for (ConjuntoSimbolos simbolos : conjuntoSimbolos) {
			siguientes += "S(" + simbolos.getId() + ") = { ";
			aux = simbolos.getSimbolos();
			for (String simbolo : aux)
				siguientes += simbolo + " ";
			siguientes += "}\n";
		}

		new VentanaPrimerosSiguientes(parent, noTerminales, terminales, simboloInicial, gramatica, primeros, siguientes);
	}

}
