package AnalizadorSemantico;

import java.util.ArrayList;
import java.util.Stack;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ResultadoAnalisisSemantico;
import Utilidades.AnalizadorLexico.Token;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;
import Utilidades.Gramatica.SimboloGramatical;

public class AnalisisSemantico {
    public static ResultadoAnalisisSemantico analizar(ArrayList<Token> tiraTokens, ArrayList<String>tiraTokensString, Gramatica gramatica, TablaLR tablaLR) {
		//declaramos variables locales
        Stack<Stack<SimboloGramatical>> pilaResultado = new Stack<Stack<SimboloGramatical>>();
		ArrayList<ArrayList<Token>> entradaResultado = new ArrayList<ArrayList<Token>>();
        ArrayList<String> accionResultado = new ArrayList<String>();
        Stack<String> pilaResultadoString = new Stack<String>();
        ArrayList<String> entradaResultadoString = new ArrayList<String>();

        //Agregar "$" en tiras de tokens
        Token temporalPrimerElemento = new Token();
        temporalPrimerElemento.setToken("$");
		tiraTokens.add(temporalPrimerElemento);
        //TiraStrings
        tiraTokensString.add("$");

        SimboloGramatical temporalAñadiduras = new SimboloGramatical();
        SimboloGramatical auxiliarIndex = new SimboloGramatical();
        ArrayList<String> copiaTokensString = new ArrayList<String>(tiraTokensString);
		ArrayList<Token> copiaTokens = new ArrayList<Token>(tiraTokens);
		Stack<SimboloGramatical> copiaPila = new Stack<SimboloGramatical>();
        Stack<String> copiaPilaString = new Stack<String>();
		Stack<SimboloGramatical> pila = new Stack<SimboloGramatical>();
        Stack<String> pilaString = new Stack<String>();
		int elementoTopePila; 
		int a = 0; //Posicion actual de la tira de tokens
		int index = 0;
		String erroresSintacticos = new String();
        String auxiliarCopia = new String();
		boolean bandera = true;
        boolean banderaAccion = false;
		ArrayList<Token> temporalTira = new ArrayList<Token>();

		//inicializamos las pilas en 0
        temporalAñadiduras.setSimboloGramatical("0");
        pila.add(temporalAñadiduras);
        pilaString.add("0");
        

		//Agregamos los tokens como los recibimos en las entradasResultados
		entradaResultadoString.add(copiaTokensString.toString());
        entradaResultado.add(tiraTokens);

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
                    if(!tiraTokens.get(a).getValorLexico().isBlank()){
                        //Si no esta vacio el valor lexico le copiamos con punto y su valor lexico a la pila
                        auxiliarCopia = tiraTokensString.get(a) + ".'" + tiraTokens.get(a).getValorLexico() + "'";
                        pilaString.add(auxiliarCopia);
                    }else{
                        //Si esta vacio solo le copiamos lo que este en la pilaString
                        pilaString.add(tiraTokensString.get(a));
                    }
					pilaString.push(String.valueOf(index));
					copiaTokensString.set(a, "");
					entradaResultadoString.add(copiaTokensString.toString());
                    
                    //Parte Objeto
                    copiaPila = (Stack<SimboloGramatical>)pila.clone();//Copia pila
                    pilaResultado.add(copiaPila);
                    temporalAñadiduras = new SimboloGramatical(tiraTokens.get(a));
                    pila.push(temporalAñadiduras);
                    auxiliarIndex = new SimboloGramatical(String.valueOf(index));
                    pila.push(auxiliarIndex);
					if(!copiaTokens.get(0).getToken().equals("$")){
						//Si el ultimo elemento de copiaTokens no es $ elimina el primer elemento
						copiaTokens.remove(0);
					}  	
                    entradaResultado.add(copiaTokens);
					a++;
					break;

				case 'r':        /* reducir A -> β*/  
					index = Integer.parseInt(num);      
					ReglaProduccion rProduccion = new ReglaProduccion();
					rProduccion = gramatica.getReglasProduccion().get(index-1); // f -> id 
					//colocamos la regla de produccion y el r + indice
					accionResultado.add(imprimirAccion(rProduccion, index));    //añadimos la accion a accionResultado
					
                    //parte String
                    copiaPilaString = (Stack<String>) pilaString.clone();
					pilaResultadoString.add(copiaPilaString.toString());
					entradaResultadoString.add(copiaTokensString.toString());
					if(rProduccion.getProduccion().get(0).equals("Ɛ")){
						elementoTopePila = Integer.parseInt(pilaString.lastElement());
                        banderaAccion = true;
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

                    //parte Objeto
                    copiaPila = (Stack<SimboloGramatical>)pila.clone();
                    pilaResultado.add(copiaPila);
					temporalTira = (ArrayList<Token>)copiaTokens.clone();
                    entradaResultado.add(temporalTira);
                    if(banderaAccion == false){     //Si la condición del if de arriba no se cumple
                        int tamañoPop = rProduccion.getProduccion().size()*2;
                        for(int i = 0; i < tamañoPop ; i++){
							pila.pop();
						}
                        elementoTopePila = Integer.parseInt(pila.lastElement().getSimboloGramatical());
                    }else{
                        elementoTopePila = Integer.parseInt(pila.lastElement().getSimboloGramatical());
                    }
                    pila.push(rProduccion.getObjetoSimboloGramatical());
                    temporalAñadiduras.setSimboloGramatical(s);
                    pila.push(temporalAñadiduras);

                    banderaAccion = false;  //reiniciamos la bandera
					break;

				case 'a':
					accionResultado.add("Aceptar");
                    //parte String
					copiaPilaString = (Stack<String>) pilaString.clone();
					pilaResultadoString.add(copiaPilaString.toString());

                    //parte objeto
                    copiaPila = (Stack<SimboloGramatical>)pila.clone();
                    pilaResultado.add(copiaPila);
					bandera = false;
					break;
				
				default:
                    //parte String
					entradaResultadoString.add(copiaTokensString.toString());
					pilaResultadoString.add(copiaPilaString.toString());

                    //parte Objeto
                    entradaResultado.add(copiaTokens);
                    pilaResultado.add(copiaPila);

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
		return new ResultadoAnalisisSemantico(pilaResultado, entradaResultado, accionResultado, pilaResultadoString, entradaResultadoString);	
	}

    static private String imprimirAccion(ReglaProduccion reglaProduccionImprimir, int index){
        String temp = new String();
        temp = "r" + index + " " + reglaProduccionImprimir.getStringSimboloGramatical() + "»" + reglaProduccionImprimir.getProduccion() + " " + "{" 
        + reglaProduccionImprimir.getStringSimboloGramatical() + ".trad := " + reglaProduccionImprimir.getObjetoSimboloGramatical().getTraduccion() + "}";
        return temp;
    }

    
}
