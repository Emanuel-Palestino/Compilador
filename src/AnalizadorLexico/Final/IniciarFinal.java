package AnalizadorLexico.Final;

import java.io.IOException;

import javax.swing.JFrame;

import Utilidades.AnalizadorLexico.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;

public class IniciarFinal {

	public IniciarFinal(JFrame parent) {
		String rutaArchivo = "src/ArchivosExtra/programa3.js";
		try {
			ResultadoAnalisisLexico res = EvaluarCodigo.evaluar(rutaArchivo);

			// Mostrar resultado en Ventana
			String[][] resultadoTokens, resultadoSimbolos, resultadoErrores;
			resultadoTokens = res.getTokensTabla();
			resultadoSimbolos = res.getSimbolosTabla();
			resultadoErrores = res.getErroresTabla();

			new VentanaFinal(parent, true, resultadoTokens, resultadoSimbolos, resultadoErrores, rutaArchivo);
		} catch (IOException | ExcepcionER e) {
			e.printStackTrace();
		}
	}

}
