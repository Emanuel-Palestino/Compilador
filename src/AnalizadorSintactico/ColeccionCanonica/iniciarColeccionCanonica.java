package AnalizadorSintactico.ColeccionCanonica;

import java.util.ArrayList;

import javax.swing.JFrame;

import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class iniciarColeccionCanonica {

	public iniciarColeccionCanonica(JFrame parent) {
		Gramatica grama = new Gramatica("src/ArchivosExtra/gramatica.txt");
		String resultado = ColeccionCanonica.hacer(grama).getProceso();

		// pasarle los datos a la ventana
		String noTerminales, terminales, simboloInicial, gramatica;
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


		new VentanaColeccionCanonica(parent, noTerminales, terminales, simboloInicial, gramatica, resultado);
	}

}
