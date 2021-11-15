package AnalizadorLexico.Final;

import java.io.IOException;

import Utilidades.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;

public class IniciarFinal {

	public IniciarFinal() {
		try {
			
			ResultadoAnalisisLexico res = EvaluarCodigo.evaluar("src/ArchivosExtra/programa.js");

			// Imprimir Resultado en consola
			String[][] aux;
			System.out.println("");

			aux = res.getTokens();
			System.out.println("\t\tTIRA DE TOKENS");
			System.out.println("Linea\t\tLexema\t\tToken");
			for (int i = 0; i < aux.length; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(aux[i][j] + "\t\t");
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");

			aux = res.getSimbolos();
			System.out.println("\t\tTABLA DE SIMBOLOS");
			System.out.println("Id\t\tValor\t\tFuncion");
			for (int i = 0; i < aux.length; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(aux[i][j] + "\t\t");
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");

			aux = res.getErrores();
			System.out.println("\tTABLA DE ERRORES");
			System.out.println("Linea\t\tDescripcion");
			for (int i = 0; i < aux.length; i++) {
				for (int j = 0; j < 2; j++) {
					System.out.print(aux[i][j] + "\t\t");
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");

		} catch (IOException | ExcepcionER e) {
			e.printStackTrace();
		}
	}
	
}
