package AnalizadorSintactico.PrimerosSiguientes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PrimerosSiguientes {
	
	/*public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {

		return new ResultadoPrimerosSiguientes();
	}*/


	public static ConjuntoSimbolos siguiente (String simbolos){
		
	} 

	static ArrayList <ConjuntoSimbolos> siguientes(String simbolos, Gramatica gramatica){
		ArrayList <ConjuntoSimbolos> resultados = new ArrayList <ConjuntoSimbolos>();
		ArrayList <String> pruebas = new ArrayList<String>();
		int i,tamañoVariable, posicionA;
		resultados.add(new ConjuntoSimbolos);

		resultados.get(i).setId(gramatica.simboloInicial);
		//Primer caso
		if (i == 0){
			resultados.get(i).getSimbolos().add("$");
		}

		for(ReglaProduccion buscar : gramatica.getReglaProduccion()){			
			posicionA = buscar.getReglaProduccion().IndexOf(buscar.getSimboloGramatical()); 
			if ( posicionA >= 0){
				tamañoVariable = buscar.getProduccion().size();
				if (posicionA == tamañoVariable -1){
					//Tercer caso


				} else if (buscar.getProduccion().contains(gramatica.getNoTerminales()) == true){
						if (buscar.getProduccion().get(posicionA+1).contains(gramatica.getNoTerminales()) == true){
							//Pendiente  A-> A C
							
						} else {
							if (resultados.get(i).getSimbolos().contains(buscar.getProduccion().get(posicionA+1)) == false){
								//resultados.get(i).getSimbolos().add (buscar.getProduccion().get(posicionA+1));

								//primeros(resultados.get(0).get(buscaProduccion+1).contains(epsilon))
							} 
							//Tercer caso 
						}
					}	else {				
						}
			}
			i++;
		}

	}
}
