package Pruebas;

import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class pruebaCC {

	public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica.txt");
		ColeccionCanonica.hacer(gramatica);
	}
	
}
