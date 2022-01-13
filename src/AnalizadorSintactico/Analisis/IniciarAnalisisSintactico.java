package AnalizadorSintactico.Analisis;

import java.io.IOException;

import javax.swing.JFrame;

import AnalizadorLexico.Final.EvaluarCodigo;
import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.ResultadoAnalisisLexico;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Gramatica.Gramatica;

public class IniciarAnalisisSintactico {

	public IniciarAnalisisSintactico(JFrame parent) {
		Gramatica grama = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajs1.txt");
		ColeccionCanonica cc = ColeccionCanonica.hacer(grama);
		TablaLR tablaLR = TablaLR.construir(cc, grama);
		ResultadoAnalisisLexico analisisLexico = new ResultadoAnalisisLexico();
		try {
			analisisLexico = EvaluarCodigo.evaluar("src/ArchivosExtra/programaGramatica1.js");
		} catch (IOException | ExcepcionER e) {
			e.printStackTrace();
		}
		ResultadoAnalisisSintactico analisisSintactico = AnalisisSintactico.analizar(analisisLexico.getTiraTokens(),
				grama, tablaLR);
		new VentanaAnalisisSintactico(parent, grama.stringSimbolosNoTerminales(), grama.stringSimbolosTerminales(),
				grama.getSimboloInicial(), grama.stringGramatica(), tablaLR, grama.getTerminales(),
				grama.getNoTerminales(), analisisSintactico);
	}

}
