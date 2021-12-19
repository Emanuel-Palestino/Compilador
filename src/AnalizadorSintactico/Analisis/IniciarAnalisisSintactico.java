package AnalizadorSintactico.Analisis;

import javax.swing.JFrame;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class IniciarAnalisisSintactico {

	public IniciarAnalisisSintactico(JFrame parent) {
		Gramatica grama = new Gramatica("src/ArchivosExtra/gramatica.txt");
		ColeccionCanonica cc = ColeccionCanonica.hacer(grama);
		TablaLR tablaLR = TablaLR.construir(cc, grama);
		AnalisisSintactico.analizar(tiraTokens, grama, tablaLR);
		new VentanaAnalisisSintactico();
	}
	
}
