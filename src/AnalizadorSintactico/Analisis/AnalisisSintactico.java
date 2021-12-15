package AnalizadorSintactico.Analisis;

import java.util.ArrayList;
import java.util.Stack;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class AnalisisSintactico {
	public static ResultadoAnalisisSintactico analizar(ArrayList<String> tiraTokens, Gramatica gramatica, TablaLR tablaLR) {
		ArrayList <String> copiaTokens = new ArrayList<String>(tiraTokens);
		Stack<String> pila = new Stack<String>();
		int a = 0; //Posicion actual de la tira de tokens
		int iterador = 0; 
		boolean bandera = true;
		//Pila.push("0");

		while(bandera){
			String temp = tablaLR.getAcciones().get(pila.peek()).get(tiraTokens.get(a));
			String num = temp.substring(1);
			char operacionSwitch = temp.charAt(0);
			switch(operacionSwitch){
				case'd':
					int index = Integer.parseInt(num);
					//accionResultado [iterador] = "d" + index;
					Stack<String> copiaPila = new Stack<String>(pila);
					//pilaResultado[iterador] = copiaPila ;
					//pila.push(a);
					//pila.push(index);
					copiaTokens.get(a) = "";
					//entradaResultado[iterador].addAll(copiaTokens)
					a++;
					iterador ++;
					break;

				case 'r':        /* reducir A -> β*/
					int indexR = Integer.parseInt(num);          
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index); // f -> id 
					accionResultado [iterador] = "r" + index;
					accionResultado [iterador] += rProduccion;
					Stack<String> copiaPila = new Stack<String>(pila);
					pilaResultado[iterador] = copiaPila ;
					entradaResultado[iterador].addAll(copiaTokens)
					int tamañoPop = rProduccion.getProduccion().size()*2;
					for(int i = 0; i < tamañoPop ; i++){
						pila.pop();
					}
					j = pila.peek();
					pila.push(rProduccion.getSimboloGramatical());
					String s = tablaLR.getIrA().get(index).get(rProduccion.getSimboloGramatical());
					pila.push(s);
					iterador++;
					break;

				case 'a':
					accionResultado[iterador] = "Aceptar";
					Stack<String> copiaPila = new Stack<String>(pila);
					pilaResultado[iterador] = copiaPila ;
					entradaResultado[iterador].addAll(copiaTokens);
					bandera = false;
					break;
				
				default:
				int index = Integer.parseInt(num); 
            	entradaResultado[iterador].addAll(copiaTokens);
            	pilaResultado[iterador] = pila copia;
            	accionResultado[iterador] = "Error sintáctico, se esperaba: ";
           	 	for(String simboloTerminal : gramatica.getTerminales()){
					if(!tablaLR.getAcciones().get(index).get(simboloTerminal).equals("")){
						accionResultado[iterador] += simboloTerminal + " ó ";
					}
            	}
				bandera = false;
				break;
			}
		}
		return new ResultadoAnalisisSintactico(pilaResultado, entradaResultado, accionResultado);	
	}
}
