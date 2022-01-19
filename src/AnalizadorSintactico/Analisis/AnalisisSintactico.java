package AnalizadorSintactico.Analisis;

import java.util.ArrayList;
import java.util.Stack;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class AnalisisSintactico {
	public static ResultadoAnalisisSintactico analizar(ArrayList<String> tiraTokens, Gramatica gramatica, TablaLR tablaLR) {
		//Agregar "$" en tira de tokens
		tiraTokens.add("$");
		
		//declaramos variables locales
		ArrayList<String> copiaTokens = new ArrayList<String>(tiraTokens);
		ArrayList<String> accionResultado = new ArrayList<String>();
		Stack<String> copiaPila = new Stack<String>();
		ArrayList<String> entradaResultado = new ArrayList<String>();
		Stack<String> pilaResultado = new Stack<String>();
		Stack<String> pila = new Stack<String>();
		int elementoTopePila; 
		int a = 0; //Posicion actual de la tira de tokens
		int index = 0;
		String erroresSintacticos = new String();
		boolean bandera = true;
		pila.push("0");		//inicializamos la pila en 0

		//Agregamos los tokens como los recibimos en entradaResultado
		entradaResultado.add(copiaTokens.toString());

		while(bandera){
			int indiceTemp = Integer.parseInt(pila.peek());
			String temp = tablaLR.getAcciones().get(indiceTemp).get(tiraTokens.get(a));
			if(temp == null || temp == ""){
				temp = "e";	
			}
			String num = temp.substring(1);
			copiaPila.clear();
			char operacionSwitch = temp.charAt(0);
			switch(operacionSwitch){
				case'd':
					index = Integer.parseInt(num);
					accionResultado.add("d" + index);
					copiaPila = (Stack<String>) pila.clone();//Copia pila
					pilaResultado.add(copiaPila.toString());
					pila.push(tiraTokens.get(a));
					pila.push(String.valueOf(index));
					copiaTokens.set(a, "");
					entradaResultado.add(copiaTokens.toString());
					a++;
					break;

				case 'r':        /* reducir A -> β*/  
					index = Integer.parseInt(num);      
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index-1); // f -> id 
					//colocamos la regla de produccion y el r + indice
					accionResultado.add("r" + index +" " + rProduccion.getSimboloGramatical() + "»" + rProduccion.getProduccion());
					copiaPila = (Stack<String>) pila.clone();
					pilaResultado.add(copiaPila.toString());
					entradaResultado.add(copiaTokens.toString());
					if(rProduccion.getProduccion().get(0).equals("Ɛ")){
						elementoTopePila = Integer.parseInt(pila.lastElement());
					}else{
						int tamañoPop = rProduccion.getProduccion().size()*2;
						for(int i = 0; i < tamañoPop ; i++){
							pila.pop();
						}
						elementoTopePila = Integer.parseInt(pila.lastElement());	//elemento del tope de la pila antes del pop
					}
					pila.push(rProduccion.getSimboloGramatical());
					String s = tablaLR.getIrA().get(elementoTopePila).get(rProduccion.getSimboloGramatical());
					pila.push(s);
					break;

				case 'a':
					accionResultado.add("Aceptar");
					copiaPila = (Stack<String>) pila.clone();
					pilaResultado.add(copiaPila.toString());
					bandera = false;
					break;
				
				default:
					entradaResultado.add(copiaTokens.toString());
					pilaResultado.add(copiaPila.toString());
					for(String simboloTerminal : gramatica.getTerminales()){
						if(!tablaLR.getAcciones().get(index).get(simboloTerminal).equals("$")){
							erroresSintacticos += simboloTerminal + " ó ";
						}
					}
					accionResultado.add("Error sintáctico, se esperaba: " + erroresSintacticos);
					bandera = false;
					break;
			}
		}	
		return new ResultadoAnalisisSintactico(pilaResultado, entradaResultado, accionResultado);	
	}
}
