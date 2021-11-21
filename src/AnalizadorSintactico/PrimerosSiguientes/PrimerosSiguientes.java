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
	static ArrayList <ConjuntoSimbolos> resultados = new ArrayList <ConjuntoSimbolos>();

	/*public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {

		return new ResultadoPrimerosSiguientes();
	}*/

	public static ConjuntoSimbolos siguiente(String simbolo, Gramatica gramatica){
		ArrayList <ReglaProduccion> reglasSimboloActual = new ArrayList <ReglaProduccion>();
		ConjuntoSimbolos temporal3 = new ConjuntoSimbolos(); 
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
			if(posicionA >= 0){
				tamañoVariable = buscar.getProduccion().size();
				if (posicionA == tamañoVariable - 1){
					//Con esto comprobamos que no hay un beta y solamente queda del tipo B -> αA
					temporal = siguiente(buscar.getSimboloGramatical(),gramatica); //siguiente(B)
					List<String> temporal2 = temporal.getSimbolos().stream().distinct().collect(Collectors.toList());
					resultado.setId(simbolo); 
					resultado.getSimbolos().addAll(temporal2);		//equals
					buscar.setMarcado(true);  //B -> αA (marcado true)

				}else if ((buscar.getProduccion().contains(gramatica.getNoTerminales()) == true) || (buscar.getProduccion().contains(gramatica.getTerminales()) == true)){
					posicionBeta = buscar.getProduccion().get(posicionA+1);
					contadorBeta = Integer.parseInt(posicionBeta);
					List<String> betaSubarreglo = buscar.getProduccion().subList(contadorBeta, tamañoVariable);
					for(String mueveBeta : betaSubarreglo){	//recorremos el subarreglo de beta
						//Con ese while comprobamos si lo que tiene mueveBeta en la posicion de la lista es un no terminal
						if(compruebaNoTerminales(iteradorTerminales,mueveBeta) == 1){
							temporal3 = primero(mueveBeta,gramatica);
							temporal3.getSimbolos().remove('Ɛ');
							List<String> temporal2 = temporal3.getSimbolos().stream().distinct().collect(Collectors.toList());
							resultado.setId(simbolo);
							resultado.getSimbolos().addAll(temporal2);
							break; //L A   C A G O   M A R I O	
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

	static public ConjuntoSimbolos primero(String simbolo, Gramatica gramatica){

		ConjuntoSimbolos primeros = new ConjuntoSimbolos();
		
		// Si simbolos es un terminal, el PRIMER conjunto es él mismo
		// Aquí, la cadena vacía también se incluye en el símbolo del terminal
		if(gramatica.esTerminal(simbolo)){
			primeros.getSimbolos().add(simbolo);
			return primeros;
		}
		else{

			// De lo contrario, es igual a la suma de los PRIMEROS conjuntos de símbolos de la derecha
			//Extraer las reglas de produccion desde gramatica
			ArrayList<ReglaProduccion> produccion = new ArrayList <ReglaProduccion>();
			ArrayList<ArrayList<String>> elementosDerecha = new ArrayList<ArrayList<String>>();
			
			produccion = gramatica.getReglasProduccion();
			for(ReglaProduccion regla : produccion){ //For each para recorrer las reglas de produccion

				//si la regla de produccion tiene simbolo igual que el simbolo que se analiza y no esta marcada se asigna a elementos derecha
				if(regla.getSimboloGramatical().equals(simbolo) && regla.getMarcado() == false){
					elementosDerecha.add( regla.getProduccion());
					regla.setMarcado(true); //Se marca esta regla
				}
			}

			//Pasa por cada elemento de la derecha
			for (ArrayList<String> regla : elementosDerecha) {

				ArrayList<String> reglaProduccion = regla;

				//Si la produccion de la derecha es un solo simbolo, se agrega directamente al primer conjunto
				if(reglaProduccion.size() == 1){
					primeros.getSimbolos().addAll(primero(reglaProduccion.get(0),gramatica).getSimbolos());
				}else{

					//Si hay varios simbolos a la derecha, mirar al primer simbolo de la cadena
					for(int i = 0; i < reglaProduccion.size(); i++){

						String caracter = reglaProduccion.get(i);
						
                        // Si es un terminal, agregar directamente a la primera colección
						if(gramatica.esTerminal(caracter)){
							primeros.getSimbolos().add(caracter);
							break;
						}
						else{
							// Si es un símbolo no terminal, calcule su PRIMER conjunto
							
							ArrayList<String> cPrimeros = primero(caracter,gramatica).getSimbolos();
							// Si hay una cadena vacía, es necesario continuar calculando el PRIMER conjunto del siguiente símbolo
							if(cPrimeros.contains("Ɛ")){
								// Si este es el último símbolo
                                // Es necesario agregar la página de cadena vacía a la primera colección
								if (i == cPrimeros.size() - 1){
									primeros.getSimbolos().addAll(cPrimeros);
									
                                } else {
                                    cPrimeros.remove("Ɛ");
									primeros.getSimbolos().addAll(cPrimeros);
                                
                                }
                            } else {
                                // Si no contiene una cadena vacía, no es necesario continuar con el cálculo
								primeros.getSimbolos().addAll(cPrimeros);
                                break;
                            }
						}
					}
				}
			}
		}
		return primeros;
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
