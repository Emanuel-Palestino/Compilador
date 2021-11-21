package Pruebas;

import java.util.ArrayList;
import AnalizadorSintactico.PrimerosSiguientes.*;
import Utilidades.ConjuntoSimbolos;
import Utilidades.Gramatica.Gramatica;

public class pruebaPrimeros {

	public static void main(String[] args) {

		Gramatica gramatica = new Gramatica("src/pruebas/gramatica.txt");
		PrimerosSiguientes prueba = new PrimerosSiguientes();

		ArrayList<ConjuntoSimbolos> resultados = new ArrayList<ConjuntoSimbolos>();

		resultados = prueba.primeros(gramatica.getNoTerminales(), gramatica);

		for (ConjuntoSimbolos recorre : resultados) {
			System.out.print(recorre.getId());
			System.out.println(recorre.getSimbolos());

		}
	}
}