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
		String[] accionResultado = new String[100];
		Stack<String> copiaPila = new Stack<String>();
		ArrayList<String>[] entradaResultado = new ArrayList[100];
		Stack<String>[] pilaResultado = new Stack[100];
		Stack<String> pila = new Stack<String>();
		String casoD = new String();
		int a = 0; //Posicion actual de la tira de tokens
		int iterador = 0; 
		boolean bandera = true;
		pila.push("0");		//inicializamos la pila en 0

		while(bandera){
			String temp = tablaLR.getAcciones().get(Integer.parseInt(pila.peek())).get(tiraTokens.get(a));
			String num = temp.substring(1);
			char operacionSwitch = temp.charAt(0);
			int index = Integer.parseInt(num);
			switch(operacionSwitch){
				case'd':
					accionResultado [iterador] = "d" + index;
					copiaPila.addAll(pila); //Copia pila
					pilaResultado[iterador] = copiaPila ;
					pila.push(String.valueOf(a));
					pila.push(String.valueOf(index));
					casoD = copiaTokens.get(a);
					casoD =  " ";
					entradaResultado[iterador].addAll(copiaTokens);
					a++;
					iterador ++;
					break;

				case 'r':        /* reducir A -> β*/         
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index); // f -> id 
					accionResultado [iterador] = "r" + index;
					accionResultado [iterador] += rProduccion;
					copiaPila.addAll(pila);
					pilaResultado[iterador] = copiaPila ;
					entradaResultado[iterador].addAll(copiaTokens);
					int tamañoPop = rProduccion.getProduccion().size()*2;
					for(int i = 0; i < tamañoPop ; i++){
						pila.pop();
					}
					String j = pila.peek();
					pila.push(rProduccion.getSimboloGramatical());
					String s = tablaLR.getIrA().get(index).get(rProduccion.getSimboloGramatical());
					pila.push(s);
					iterador++;
					break;

				case 'a':
					accionResultado[iterador] = "Aceptar";
					copiaPila.addAll(pila);
					pilaResultado[iterador] = copiaPila ;
					entradaResultado[iterador].addAll(copiaTokens);
					bandera = false;
					break;
				
				default:
					entradaResultado[iterador].addAll(copiaTokens);
					pilaResultado[iterador] = copiaPila;
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
