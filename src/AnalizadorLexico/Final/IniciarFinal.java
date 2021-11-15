package AnalizadorLexico.Final;

import java.io.IOException;

import javax.swing.JFrame;

import Utilidades.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;

public class IniciarFinal {

	public IniciarFinal(JFrame parent) {
		String rutaArchivo = "src/ArchivosExtra/programa.js";
		try {
			ResultadoAnalisisLexico res = EvaluarCodigo.evaluar(rutaArchivo);

			// Mostrar resultado en Ventana
			String[][] resultadoTokens, resultadoSimbolos, resultadoErrores;
			resultadoTokens = res.getTokens();
			resultadoSimbolos = res.getSimbolos();
			resultadoErrores = res.getErrores();

			new VentanaFinal(parent, true, resultadoTokens, resultadoSimbolos, resultadoErrores, rutaArchivo);
		} catch (IOException | ExcepcionER e) {
			e.printStackTrace();
		}
	}

}
