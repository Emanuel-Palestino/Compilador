package AnalizadorSintactico.PrimerosSiguientes;

import javax.swing.JFrame;

import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;

public class IniciarPrimerosSiguientes {

	public IniciarPrimerosSiguientes(JFrame parent) {
		PrimerosSiguientes ps = new PrimerosSiguientes();
		// Cargar gramatica
		Gramatica grama = new Gramatica("src/ArchivosExtra/gramatica.txt");
		ResultadoPrimerosSiguientes resultado = ps.hacer(grama);
		System.out.println("algo");
		new VentanaPrimerosSiguientes();
	}

}
