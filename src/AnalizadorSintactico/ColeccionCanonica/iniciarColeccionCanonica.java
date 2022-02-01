package AnalizadorSintactico.ColeccionCanonica;

import javax.swing.JFrame;
import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class iniciarColeccionCanonica {

	public iniciarColeccionCanonica(JFrame parent) {
		Gramatica grama = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajs1.txt");
		String resultado = ColeccionCanonica.hacer(grama).getProceso();

		new VentanaColeccionCanonica(parent, grama.stringSimbolosNoTerminales(), grama.stringSimbolosTerminales(), grama.getSimboloInicial(), grama.stringGramatica(), resultado);
	}

}
