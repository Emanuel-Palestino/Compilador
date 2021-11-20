package AnalizadorSintactico.PrimerosSiguientes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;


public class PrimerosSiguientes {
	
	/*public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {

		return new ResultadoPrimerosSiguientes();
	}*/
	static ArrayList <ConjuntoSimbolos> resultados = new ArrayList <ConjuntoSimbolos>();

	public static ConjuntoSimbolos siguiente(String simbolo, Gramatica gramatica){
		ArrayList <ReglaProduccion> reglasSimboloActual = new ArrayList <ReglaProduccion>();
		ArrayList <String> temporal3 = new ArrayList <String> (); 
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
			i++;
		}

		for(ReglaProduccion buscar : reglasSimboloActual){			
			posicionA = buscar.getProduccion().indexOf(buscar.getSimboloGramatical()); 
			if ( posicionA >= 0){
				tamañoVariable = buscar.getProduccion().size();
				if (posicionA == tamañoVariable - 1){
					//Con esto comprobamos que no hay un beta y solamente queda del tipo B -> αA
					temporal = siguiente(buscar.getSimboloGramatical(),gramatica); //siguiente(B)
					List<String> temporal2 = temporal.getSimbolos().stream().distinct().collect(Collectors.toList());
					resultado.setId(simbolo); 
					resultado.getSimbolos().addAll(temporal2);		//equals
					buscar.setMarcado(true);
				}else if ((buscar.getProduccion().contains(gramatica.getNoTerminales()) == true) || (buscar.getProduccion().contains(gramatica.getTerminales()) == true)){
					posicionBeta = buscar.getProduccion().get(posicionA+1);
					contadorBeta = Integer.parseInt(posicionBeta);
					List<String> betaSubarreglo = buscar.getProduccion().subList(contadorBeta, tamañoVariable);
					for(String mueveBeta : betaSubarreglo){	//recorremos el subarreglo de beta
						//Con ese while comprobamos si lo que tiene mueveBeta en la posicion de la lista es un no terminal
						if(compruebaNoTerminales(iteradorTerminales,mueveBeta) == 1){
							temporal3 = primeros (mueveBeta,gramatica);
							temporal3.remove('Ɛ');
							List<String> temporal2 = temporal3.stream().distinct().collect(Collectors.toList());
							resultado.setId(simbolo);
							resultado.getSimbolos().addAll(temporal2);	
						}else{
							temporal = siguiente(buscar.getSimboloGramatical(),gramatica); //siguiente(B)
							List<String> temporal2 = temporal.getSimbolos().stream().distinct().collect(Collectors.toList());
							resultado.setId(simbolo);
							resultado.getSimbolos().addAll(temporal2);					
						}
					}
					buscar.setMarcado(true);
				}
			}
		}
		return resultado;
	} 

	static ArrayList <ConjuntoSimbolos> siguientes(ArrayList <String> simbolos, Gramatica gramatica){
		ConjuntoSimbolos temporales = new ConjuntoSimbolos();
		int i=0;

		for (String mueveSimbolos : simbolos){
			temporales = siguiente(mueveSimbolos,gramatica);
			resultados.add(temporales);
			//Primer caso
			if (i == 0){
				//resultados.get(i).setId(gramatica.getSimboloInicial()); Por si las moscas 
				resultados.get(i).getSimbolos().add("$");
			}
			i++;
		}
		return resultados;
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
