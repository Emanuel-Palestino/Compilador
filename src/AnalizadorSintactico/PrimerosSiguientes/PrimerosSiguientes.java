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


	public static ConjuntoSimbolos siguiente(String simbolo, Gramatica gramatica){
		ArrayList <ReglaProduccion> reglasSimboloActual = new ArrayList <ReglaProduccion>();
		ConjuntoSimbolos resultado = new ConjuntoSimbolos();

		int i=0, tamañoVariable, posicionA, contadorBeta; //contador
		String posicionBeta;
		//Recorremos gramatica.getReglasProduccion() y movemos esas reglas para siguiente
		for(ReglaProduccion buscando : gramatica.getReglasProduccion()){
			reglasSimboloActual.add(new ReglaProduccion());
			reglasSimboloActual.get(i).setSimboloGramatical(simbolo);
			if(buscando.getSimboloGramatical() == simbolo){
				reglasSimboloActual.get(i).getProduccion().addAll(buscando.getProduccion());
			}
		}

		for(ReglaProduccion buscar : gramatica.getReglasProduccion()){			
			posicionA = buscar.getProduccion().indexOf(buscar.getSimboloGramatical()); 
			if ( posicionA >= 0){
				tamañoVariable = buscar.getProduccion().size();
				if (posicionA == tamañoVariable -1){
					//Tercer caso alpha
					

				} else if (buscar.getProduccion().contains(gramatica.getNoTerminales()) == true){
						posicionBeta = buscar.getProduccion().get(posicionA+1);
						contadorBeta = Integer.parseInt(posicionBeta);
						for(int conta = contadorBeta ; i < tamañoVariable ; i++){
							siguiente(buscar.getProduccion().get(conta),gramatica);
						}
						if (contains(gramatica.getNoTerminales()) == true){
							//Pendiente  A-> A C
							
						} else {
							if (resultado.getSimbolos().contains(buscar.getProduccion().get(posicionA+1)) == false){
								//resultados.get(i).getSimbolos().add (buscar.getProduccion().get(posicionA+1));

								//primeros(resultados.get(0).get(buscaProduccion+1).contains(epsilon))
							} 
							//Tercer caso 
						}
					}	else {				
					
					}
			}
		}
	} 

	static ArrayList <ConjuntoSimbolos> siguientes(ArrayList <String> simbolos, Gramatica gramatica){
		ArrayList <ConjuntoSimbolos> resultados = new ArrayList <ConjuntoSimbolos>();
		ConjuntoSimbolos temporales = new ConjuntoSimbolos();
		ArrayList <String> pruebas = new ArrayList<String>();
		int i=0;

		temporales = siguiente()
		resultados.add();

		resultados.get(i).setId(gramatica.getSimboloInicial());
		//Primer caso
		if (i == 0){
			resultados.get(i).getSimbolos().add("$");
		}

	}
}
