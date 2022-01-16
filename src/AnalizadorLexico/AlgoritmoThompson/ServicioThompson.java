package AnalizadorLexico.AlgoritmoThompson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Utilidades.Archivo;
import Utilidades.Automatas.Automata;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Listas.ListaDoblementeEnlazada;
import Utilidades.Listas.NodoLista;

public class ServicioThompson {
	private Thompson algoritmo;

	public ServicioThompson() {
		algoritmo = new Thompson();
	}

	public ModeloResultadoThompson ejecutar(String rutaExpresionRegular) throws ExcepcionER, FileNotFoundException, IOException {
		// Se carga la expresion regular y la expresión a evaluar
		ArrayList<String> expresion = Archivo.capturaDatosArchivo(rutaExpresionRegular);

		// Se evalua la expresion regular para generar el AFN
		Automata resultado = algoritmo.evaluarER(expresion.get(1), expresion.get(0));

		// Se crea el encabezado de la tabla
		String[] alfabeto = expresion.get(0).split(" ");
		String[] encabezado = new String[alfabeto.length + 2];
		encabezado[0] = "Estado";
		for (int i = 0; i < alfabeto.length; i++) {
			encabezado[i + 1] = alfabeto[i];
		}
		encabezado[alfabeto.length + 1] = "Ɛ";

		// Inicializar matriz con -
		String[][] datos = new String[resultado.getNumEstados()][encabezado.length];
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[0].length; j++)
				datos[i][j] = "-";
		}

		for (int i = 0; i < resultado.getNumEstados(); i++) {
			ListaDoblementeEnlazada transiciones = resultado.getTransiciones(i);
			datos[i][0] = "" + i;

			// Recorrer lista
			NodoLista tran = null;
			try {
				tran = transiciones.getInicio();
			} catch (NullPointerException e) {
				System.out.println("Sin transiciones: " + i);
			}
			while (tran != null) {
				String simbolo = tran.getSimbolo();
				int pos = -1;

				// Encontrar el indice de la transicion que concuerde
				for (int a = 0; a < encabezado.length; a++) {
					if (simbolo.equals(encabezado[a])) {
						pos = a;
						break;
					}
				}

				// Agregar a qué estado llega con la transicion
				if (pos >= 0) {
					// poner entre llaves
					if (datos[i][pos].equals("-"))
						datos[i][pos] = "" + tran.getEstadoDestino();
					else if (datos[i][pos].matches("\\d+")) { // saber si es un digito
						datos[i][pos] = "{" + datos[i][pos] + "," + tran.getEstadoDestino() + "}";
					} else {
						datos[i][pos] = datos[i][pos].replace("}", "," + tran.getEstadoDestino() + "}");
					}
				}

				tran = tran.getSiguiente();
			}
		}

		// Se retornan los datos: Alfabeto, Expresion Regular, Encabezado y Datos
		return new ModeloResultadoThompson(expresion.get(0), expresion.get(1), encabezado, datos);
	
	}
}
