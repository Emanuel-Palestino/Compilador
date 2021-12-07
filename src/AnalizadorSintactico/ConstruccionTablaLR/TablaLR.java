package AnalizadorSintactico.ConstruccionTablaLR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ColeccionCanonica;
import Utilidades.ConjuntoSimbolos;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;
import AnalizadorSintactico.PrimerosSiguientes.PrimerosSiguientes;

public class TablaLR {

  private ArrayList<Map<String, String>> acciones;
  private ArrayList<Map<String, String>> irA;

  // Constructor vacio
  public TablaLR() {
    acciones = new ArrayList<Map<String, String>>();
    irA = new ArrayList<Map<String, String>>();
  }

  // Constructor con parametros
  public TablaLR(ArrayList<Map<String, String>> acciones, ArrayList<Map<String, String>> irA) {
    this.acciones = acciones;
    this.irA = irA;
  }

  // Getters y Setters
  public ArrayList<Map<String, String>> getAcciones() {
    return acciones;
  }

  public ArrayList<Map<String, String>> getIrA() {
    return irA;
  }

  public void setAcciones(ArrayList<Map<String, String>> acciones){
    this.acciones.addAll(acciones);
  }

  public void setIra(ArrayList<Map<String, String>> irA){
    this.irA.addAll(irA);
  }



  // Metodos
  public static TablaLR construir(ColeccionCanonica coleccionCanonica, Gramatica gramatica) {
    
    ArrayList<Map<String, String>> acciones = new ArrayList<Map<String, String>>();
    ArrayList<Map<String, String>> irA = new ArrayList<Map<String, String>>();
    
    for(int i = 0; i < coleccionCanonica.getConjuntosElementos().size() ; i++){

      HashMap<String, String> aux = new HashMap<String, String>();
      for (String simbolo_A : gramatica.getTerminales()){
        aux.put(simbolo_A, "");
      }
      aux.put ("$","");
      acciones.add(aux);
    }
    for( int k = 0; k < coleccionCanonica.getConjuntosElementos().size() ; k++){

      HashMap<String, String> aux = new HashMap<String, String>();
      for (String simbolo_a : gramatica.getNoTerminales()){
        aux.put(simbolo_a, "");
      }
      irA.add(aux);
    }

    for(int i = 0; i < coleccionCanonica.getConjuntosElementos().size() ; i++) {

      ConjuntoElementos recorreConjunto = new ConjuntoElementos();
      recorreConjunto = coleccionCanonica.getConjuntosElementos().get(i);
      for (Elemento recorreElemento : recorreConjunto.getElementos()) {
        
        
        ArrayList<String> temp = new ArrayList<String>(recorreElemento.getProduccion());
      
        ReglaProduccion temporalProduccion = new ReglaProduccion();
        temporalProduccion.setSimboloGramatical(recorreElemento.getSimboloGramatical());

        if (gramatica.esTerminal(recorreElemento.getSimboloDespuesDePunto())) {
          ConjuntoElementos aux = IrA.hacer(recorreConjunto, recorreElemento.getSimboloDespuesDePunto(), gramatica);
          acciones.get(i).replace(recorreElemento.getSimboloDespuesDePunto(), "d" + coleccionCanonica.indiceDe(aux));
          continue;

        }
        if (recorreElemento.getIndexPunto() == (recorreElemento.getTamañoProduccion() - 1)) {
          PrimerosSiguientes auxSiguientes = new PrimerosSiguientes();
          ConjuntoSimbolos resultadoSiguiente = new ConjuntoSimbolos();
          resultadoSiguiente = auxSiguientes.siguiente(recorreElemento.getSimboloGramatical(), gramatica);
          temp.remove(recorreElemento.getTamañoProduccion() - 1);
          temporalProduccion.setProduccion(temp); 
          if (!recorreElemento.getSimboloGramatical().equals(gramatica.getSimboloInicial() + "'")) {
            for (String recorreSiguientes : resultadoSiguiente.getSimbolos()) {
              
              acciones.get(i).replace(recorreSiguientes, "r" + gramatica.indiceReglaProduccion(temporalProduccion));
            }
          }
          continue;

        } 
        if (recorreElemento.getSimboloDespuesDePunto().equals("$")) {

          acciones.get(i).replace("$", "acep");
          continue;
        }

      } 
    }
	for( int i = 0; i < coleccionCanonica.getConjuntosElementos().size() ; i++){
		for(int j = 0; j < gramatica.getNoTerminales().size(); j++) {
			ConjuntoElementos temporal = new ConjuntoElementos(); 
			temporal = IrA.hacer(coleccionCanonica.getConjuntosElementos().get(i), gramatica.getNoTerminales().get(j),gramatica);
			if(coleccionCanonica.indiceDe(temporal) == -1){

				irA.get(i).replace(gramatica.getNoTerminales().get(j), "");
			}else{

				irA.get(i).replace(gramatica.getNoTerminales().get(j),coleccionCanonica.indiceDe(temporal) + "");
			}
				
		}
	}
    return new TablaLR(acciones, irA);
  }
}