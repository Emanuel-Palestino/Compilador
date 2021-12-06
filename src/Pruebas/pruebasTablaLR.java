package Pruebas;

import java.util.ArrayList;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class pruebasTablaLR {

	public static void main(String[] args) {
        TablaLR tablaPrueba = new TablaLR();
	
		Gramatica gramatica = new Gramatica("src/Pruebas/gramatica.txt");
        ColeccionCanonica prueba = ColeccionCanonica.hacer(gramatica);
        // Gramatica aumentada
        ArrayList<ReglaProduccion> auxGramatica = new ArrayList<ReglaProduccion>();
        ReglaProduccion aumentada = new ReglaProduccion();
        ArrayList<String> produ = new ArrayList<String>();
        produ.add("E");
        produ.add("$");
        aumentada.setSimboloGramatical("E'");
        aumentada.setProduccion(produ);
        auxGramatica.addAll(gramatica.getReglasProduccion());
        auxGramatica.add(0, aumentada);
        gramatica.setReglasProduccion(auxGramatica);
        //
        
        tablaPrueba = TablaLR.construir(prueba,gramatica); 
        int n = prueba.getConjuntosElementos().size();

        for (int i = 0 ; i < n ; i++){
			System.out.print(tablaPrueba.getAcciones().get(i));
            System.out.print(tablaPrueba.getSimboloTerminal().get(i));
            System.out.print(tablaPrueba.getIrA().get(i));
            System.out.print(tablaPrueba.getSimboloNoTerminal().get(i));
        }
		System.out.println(tablaPrueba);
	}

}
