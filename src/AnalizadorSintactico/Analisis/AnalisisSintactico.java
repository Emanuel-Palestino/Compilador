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
		ArrayList<String>[] entradaResultado = (ArrayList<String>[]) new ArrayList[500];
		Stack<String>[] pilaResultado = new Stack[500];
		Stack<String> pila = new Stack<String>();
		String casoD = new String();
		int elementoTopePila; 
		int a = 0; //Posicion actual de la tira de tokens
		int iterador = 0; 
		int index = 0;
		boolean bandera = true;
		pila.push("0");		//inicializamos la pila en 0
		while(bandera){
			int indiceTemp = Integer.parseInt(pila.peek());
			if(a >= tiraTokens.size()){
				bandera = false;
				break;
			}
			String temp = tablaLR.getAcciones().get(indiceTemp).get(tiraTokens.get(a));
			if(temp == null){
				temp = "e";	
			}
			String num = temp.substring(1);
			copiaPila.clear();
			char operacionSwitch = temp.charAt(0);
			switch(operacionSwitch){
				case'd':
					index = Integer.parseInt(num);
					accionResultado [iterador] = "d" + index;
					copiaPila = (Stack<String>) pila.clone();//Copia pila
					pilaResultado[iterador] = new Stack<String>();
					pilaResultado[iterador].addAll(copiaPila);
					pila.push(String.valueOf(a));
					pila.push(String.valueOf(index));
					copiaTokens.set(a, "");
					entradaResultado[iterador] = new ArrayList<String>();
					entradaResultado[iterador].addAll(copiaTokens);
					a++;
					iterador ++;
					break;

				case 'r':        /* reducir A -> β*/  
					index = Integer.parseInt(num);      
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index); // f -> id 
					accionResultado[iterador] = "r" + index;
					//colocamos la regla de produccion
					accionResultado[iterador] += (" " + rProduccion.getSimboloGramatical() + "»" + rProduccion.getProduccion());
					copiaPila = (Stack<String>) pila.clone();
					pilaResultado[iterador] =  new Stack<String>();
					pilaResultado[iterador].addAll(copiaPila);
					entradaResultado[iterador] = new ArrayList<String>();
					entradaResultado[iterador].addAll(copiaTokens);
					if(rProduccion.getProduccion().get(0).equals("Ɛ")){
						elementoTopePila = Integer.parseInt(pila.firstElement());
					}else{
						int tamañoPop = rProduccion.getProduccion().size()*2;
						for(int i = 0; i < tamañoPop ; i++){
							pila.pop();
						}
						elementoTopePila = Integer.parseInt(pila.firstElement());	//elemento del tope de la pila antes del pop
					}
					pila.push(rProduccion.getSimboloGramatical());
					String s = tablaLR.getIrA().get(elementoTopePila).get(rProduccion.getSimboloGramatical());
					pila.push(s);
					iterador++;
					break;

				case 'a':
					accionResultado[iterador] = "Aceptar";
					copiaPila = (Stack<String>) pila.clone();
					pilaResultado[iterador] = new Stack<String>();
					pilaResultado[iterador] = copiaPila ;
					entradaResultado[iterador] = new ArrayList<String>();
					entradaResultado[iterador].addAll(copiaTokens);
					bandera = false;
					break;
				
				default:
					entradaResultado[iterador] = new ArrayList<String>();
					entradaResultado[iterador].addAll(copiaTokens);
					pilaResultado[iterador] =  new Stack<String>();
					pilaResultado[iterador] = copiaPila;
					accionResultado[iterador] = "Error sintáctico, se esperaba: ";
					for(String simboloTerminal : gramatica.getTerminales()){
						if(!tablaLR.getAcciones().get(index).get(simboloTerminal).equals("$")){
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
