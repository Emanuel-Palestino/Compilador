package AnalizadorLexico.AlgoritmoThompson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Utilidades.Archivo;
import Utilidades.Automata;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Listas.ListaDoblementeEnlazada;
import Utilidades.Listas.NodoLista;

public class IniciarThompson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ExcepcionER {
		new IniciarThompson();
	}

	public IniciarThompson() throws FileNotFoundException, IOException, ExcepcionER {
		Thompson thomp = new Thompson();
		Archivo file = new Archivo();
		ArrayList<String> expr = file.capturaDatosArchivo("src/ArchivosExtra/ExpresionRegular.txt");
		Automata result = thomp.evaluarER(expr.get(1), expr.get(0));

		String[] alfa = expr.get(0).split(" ");
		String[] encabezado = new String[alfa.length + 2];
		encabezado[0] = "Estado";
		for (int i = 0; i < alfa.length; i++) {
			encabezado[i + 1] = alfa[i];
		}
		encabezado[alfa.length + 1] = "Ɛ";

		// Inicializar matriz con -
		String[][] datos = new String[result.getNumEstados()][encabezado.length];
		for (int i = 0; i < datos.length; i++) {
			for (int j = 0; j < datos[0].length; j++)
				datos[i][j] = "-";
		}

		for (int i = 0; i < result.getNumEstados(); i++) {
			ListaDoblementeEnlazada transiciones = result.getTransiciones(i);
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
					else if (datos[i][pos].matches("\\d+")) {		// saber si es un digito
						datos[i][pos] = "{" + datos[i][pos] + "," + tran.getEstadoDestino() + "}";
					} else {
						datos[i][pos] = datos[i][pos].replace("}", "," + tran.getEstadoDestino() + "}");
					}
				}

				tran = tran.getSiguiente();
			}
		}

		new VentanaThompson(null, false, expr.get(0), expr.get(1), encabezado, datos);
	}

}
