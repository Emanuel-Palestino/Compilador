package AnalizadorSintactico.ConstruccionTablaLR;

import javax.swing.JFrame;

import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class IniciarTablaLR {

	public IniciarTablaLR(JFrame parent) {
		Gramatica grama = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajs1.txt");
		ColeccionCanonica cc = ColeccionCanonica.hacer(grama);
		TablaLR resultado = TablaLR.construir(cc, grama);
		new VentanaTablaLR(parent, grama.stringSimbolosNoTerminales(), grama.stringSimbolosTerminales(), grama.getSimboloInicial(), grama.stringGramatica(), resultado, grama.getTerminales(), grama.getNoTerminales());
	}
	
}
