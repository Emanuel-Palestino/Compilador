package Pruebas;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;

public class pruebasTablaLR {

	public static void main(String[] args) {
        TablaLR tablaPrueba = new TablaLR();
	
		Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica.txt");
        ColeccionCanonica prueba = ColeccionCanonica.hacer(gramatica);
        int n = prueba.getConjuntosElementos().size();
        tablaPrueba = TablaLR.construir(prueba,gramatica); 

        for (int i = 0 ; i < n ; i++){
			System.out.print(tablaPrueba.getAcciones().get(i));
            System.out.print(tablaPrueba.getSimboloTerminal().get(i));
            System.out.print(tablaPrueba.getIrA().get(i));
            System.out.print(tablaPrueba.getSimboloNoTerminal().get(i));
        }
		System.out.println(tablaPrueba);
	}

}
