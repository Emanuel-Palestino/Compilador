package AnalizadorSintactico.PrimerosSiguientes;

import java.util.ArrayList;

public class PrimerosSiguientes {


	
	public static ResultadoPrimerosSiguientes hacer(Gramatica gramatica) {

		return new ResultadoPrimerosSiguientes();
	}


	public ArrayList<String> primeros (String simbolos, Gramatica gramatica){

		ArrayList<String> primeros = new ArrayList<String>();
		
		// Si x es un terminador, el PRIMER conjunto es él mismo
		// Aquí, la cadena vacía también se incluye en el símbolo del terminal
		if(gramatica.terminales().contains(simbolos)){
			primeros.add(simbolos);
			return primeros;
		}
		else{

			// De lo contrario, es igual a la suma de los PRIMEROS conjuntos de símbolos de la derecha
			ArrayList<String> elementosDerecha = new ArrayList<String>();

			//Ciclo para insertar los elementos de la derecha
			for(String regla: gramatica.getProduccion()){

				if(regla ==  simbolos){
					elementosDerecha.add(regla);
				}
			}


			// atraviesa cada parte derecha
			for(String valor : elementosDerecha){

				ArrayList<String> listaFracmentada = gramatica.desmontar(valor);

				
			}

		}
	}



}
