package AnalizadorSemantico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.Archivo;
import Utilidades.ResultadoAnalisisSemantico;
import Utilidades.AnalizadorLexico.Token;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;
import Utilidades.Gramatica.SimboloGramatical;

public class AnalisisSemantico {
    public static ResultadoAnalisisSemantico analizar(ArrayList<Token> tiraTokens, ArrayList<String>tiraTokensString, ArrayList<String> tiraTokensSemantico, Gramatica gramatica, TablaLR tablaLR) {
		//declaramos variables locales
        ArrayList<String> accionResultado = new ArrayList<String>();
        Stack<String> pilaResultadoString = new Stack<String>();
        ArrayList<String> entradaResultadoString = new ArrayList<String>();
		ArrayList<String> entradaResultadoSemantico = new ArrayList<String>();

        //Agregar "$" en tira de token
        tiraTokensString.add("$");
		tiraTokensSemantico.add("$");

        ArrayList<String> copiaTokensString = new ArrayList<String>(tiraTokensString);
		ArrayList<String> copiaTokensSemantico = new ArrayList<String>(tiraTokensSemantico);
        Stack<String> copiaPilaString = new Stack<String>();
        Stack<String> pilaString = new Stack<String>();
		int elementoTopePila; 
		int a = 0; //Posicion actual de la tira de tokens
		int index = 0;
		String erroresSintacticos = new String();
        String auxiliarCopia = new String();
		boolean bandera = true;

		//inicializamos la pila en 0
        pilaString.add("0");
        

		//Agregamos los tokens como los recibimos en las entradasResultados
		entradaResultadoString.add(copiaTokensString.toString());
		entradaResultadoSemantico.add(copiaTokensSemantico.toString());

		while(bandera){
			int indiceTemp = Integer.parseInt(pilaString.peek());
			String temp = tablaLR.getAcciones().get(indiceTemp).get(tiraTokensString.get(a));
			if(temp == null || temp == ""){
				temp = "e";	
			}
			String num = temp.substring(1);

			char operacionSwitch = temp.charAt(0);
			switch(operacionSwitch){
				case'd':
                    //Parte String
					index = Integer.parseInt(num);
					accionResultado.add("d" + index);
					copiaPilaString = (Stack<String>) pilaString.clone();//Copia pila string
					pilaResultadoString.add(copiaPilaString.toString());
                    if(!tiraTokens.get(a).esPalabraReservada()){
                        //Si no es una palabra reservada le copiamos con punto y su valor lexico a la pila
                        auxiliarCopia = tiraTokensString.get(a) + ".'" + tiraTokens.get(a).getValorLexico() + "'";
                        pilaString.add(auxiliarCopia);
                    }else{
                        //Si es una palabra reservada solo le copiamos lo que este en la pilaString
                        pilaString.add(tiraTokensString.get(a));
                    }
					pilaString.push(String.valueOf(index));
					copiaTokensString.set(a, "");
					entradaResultadoString.add(copiaTokensString.toString());

					//Manipulamos la arrayList de resultado
					copiaTokensSemantico.set(a,"");
					entradaResultadoSemantico.add(copiaTokensSemantico.toString());

                    
					a++;
					break;

				case 'r':        /* reducir A -> β*/  
					index = Integer.parseInt(num);      
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index-1); // f -> id 
					//colocamos la regla de produccion y el r + indice
					accionResultado.add(imprimirAccion(rProduccion, index));    //Aqui llamamos al metodo donde concatenara la acción y la regla de produccion
																				//La parte comentada del metodo es donde esta la accion Semantica
                    //parte String
                    copiaPilaString = (Stack<String>) pilaString.clone();
					pilaResultadoString.add(copiaPilaString.toString());
					entradaResultadoString.add(copiaTokensString.toString());

					//Manipulamos la arrayList de resultado
					entradaResultadoSemantico.add(copiaTokensSemantico.toString());

					if(rProduccion.getProduccion().get(0).equals("Ɛ")){
						elementoTopePila = Integer.parseInt(pilaString.lastElement());
                    }else{
						int tamañoPop = rProduccion.getProduccion().size()*2;
						for(int i = 0; i < tamañoPop ; i++){
							pilaString.pop();
						}
						elementoTopePila = Integer.parseInt(pilaString.lastElement());	//elemento del tope de la pila antes del pop
					}
					pilaString.push(rProduccion.getStringSimboloGramatical());
					String s = tablaLR.getIrA().get(elementoTopePila).get(rProduccion.getStringSimboloGramatical());
					pilaString.push(s);
					break;

				case 'a':
					accionResultado.add("Aceptar");
                    //parte String
					copiaPilaString = (Stack<String>) pilaString.clone();
					pilaResultadoString.add(copiaPilaString.toString());

					bandera = false;
					break;
				
				default:
                    //parte String
					entradaResultadoString.add(copiaTokensString.toString());

					//Manipulamos la arrayList de resultado
					entradaResultadoSemantico.add(copiaTokensSemantico.toString());

					pilaResultadoString.add(copiaPilaString.toString());

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
		//Arreglamos la entradaString para que se vea con puntos y acciones


		
		return new ResultadoAnalisisSemantico( pilaResultadoString, entradaResultadoSemantico, accionResultado);	
	}

    static private String imprimirAccion(ReglaProduccion reglaProduccionImprimir, int index){
        String temp = new String();
        temp = "r" + index + " " + reglaProduccionImprimir.getStringSimboloGramatical() + "»" + reglaProduccionImprimir.getProduccion() /*+ " " 
        + reglaProduccionImprimir.getStringSimboloGramatical() + ".trad := " + reglaProduccionImprimir.getObjetoSimboloGramatical().getTraduccion()*/;
        return temp;
    }
	
}
