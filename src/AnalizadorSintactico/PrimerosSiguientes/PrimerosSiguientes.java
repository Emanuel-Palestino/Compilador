package AnalizadorSintactico.PrimerosSiguientes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;


public class PrimerosSiguientes {
	static ArrayList <ConjuntoSimbolos> resultados = new ArrayList <ConjuntoSimbolos>();

	/*public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {

		return new ResultadoPrimerosSiguientes();
	}*/


	public static ConjuntoSimbolos siguiente(String simbolo, Gramatica gramatica){
		ArrayList <ReglaProduccion> reglasSimboloActual = new ArrayList <ReglaProduccion>();
		ConjuntoSimbolos resultado = new ConjuntoSimbolos();
		ConjuntoSimbolos temporal = new ConjuntoSimbolos();
		Iterator <String> iteradorTerminales = gramatica.getNoTerminales().iterator();

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

		for(ReglaProduccion buscar : reglasSimboloActual){			
			posicionA = buscar.getProduccion().indexOf(buscar.getSimboloGramatical()); 
			if ( posicionA >= 0){
				tamañoVariable = buscar.getProduccion().size();
				if (posicionA == tamañoVariable - 1){
					//Con esto comprobamos que no hay un beta y solamente queda del tipo B -> αA
					//Comprobar que en siguientes de B no este vacio
					temporal = siguiente(buscar.getSimboloGramatical(),gramatica); //siguiente(B)
					resultado.getSimbolos().addAll(temporal.getSimbolos());

				}else if (buscar.getProduccion().contains(gramatica.getNoTerminales()) == true){
					posicionBeta = buscar.getProduccion().get(posicionA+1);
					contadorBeta = Integer.parseInt(posicionBeta);
					List<String> betaSubarreglo = buscar.getProduccion().subList(contadorBeta, tamañoVariable);
					for(String mueveBeta : betaSubarreglo){	//recorremos el subarreglo de beta
						//Con ese while comprobamos si lo que tiene mueveBeta en la posicion de la lista es un no terminal
						if(compruebaNoTerminales(iteradorTerminales,mueveBeta) == 1){
							//primeros(mueveBeta);
						}else{
							temporal = siguiente(buscar.getSimboloGramatical(),gramatica); //siguiente(B)
							resultado.getSimbolos().addAll(temporal.getSimbolos());
						}
					}
					
				}else{				
				
					
				}
			}
		}
		return resultado;
	} 

	static ArrayList <ConjuntoSimbolos> siguientes(ArrayList <String> simbolos, Gramatica gramatica){
		ConjuntoSimbolos temporales = new ConjuntoSimbolos();
		ArrayList <String> pruebas = new ArrayList<String>();
		int i=0;

		temporales = siguiente();
		resultados.add(temporales);

		resultados.get(i).setId(gramatica.getSimboloInicial());
		//Primer caso
		if (i == 0){
			resultados.get(i).getSimbolos().add("$");
		}

	}

	static int compruebaNoTerminales(Iterator <String> iteradorTerminales , String simboloComparar){
		while(iteradorTerminales.hasNext()){
			if(simboloComparar == iteradorTerminales.next()){
				return 1;
			}else{
				iteradorTerminales.next();
			}
		}
		return 0;
	}
}
