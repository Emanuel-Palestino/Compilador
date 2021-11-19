package AnalizadorSintactico.PrimerosSiguientes;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class PrimerosSiguientes {
	
	/*public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {

		return new ResultadoPrimerosSiguientes();
	}*/

	static ArrayList<ConjuntoSimbolos> siguientes(ArrayList <String> simbolos, Gramatica gramatica){
		ArrayList <ConjuntoSimbolos> resultados = new ArrayList<ConjuntoSimbolos>();
		ArrayList <String> pruebas = new ArrayList<String>();
		int tamañoVariable;

		//Primer caso
		resultados.get(0).setId(gramatica.simboloInicial);
		resultados.get(0).getSimbolos().add("$");

		for(ReglaProduccion buscar : gramatica.getReglaProduccion()){
			for(String buscaProduccion : buscar.getProduccion()){
				if(resultados.get(0).getId() == buscaProduccion){
					resultados.get(0).get(buscaProduccion);
					tamañoVariable = resultados.get(0).size();

					if(resultados.get(0).get(buscaProduccion) == tamañoVariable-1){
						/**/
					}else if(simbolos.contains(resultados.get(0).get(buscaProduccion+1)) == true)){		
						//primeros(resultados.get(0).get(buscaProduccion+1).contains(epsilon))
					}
				}
			}
		}
	}

}
