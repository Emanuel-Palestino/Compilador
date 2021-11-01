package AnalizadorLexico.ConvierteAFD;

import java.util.ArrayList;
import Utilidades.Automata;
import Utilidades.ConjuntoEstados;


public class ConvierteAFD {

  //Identificador de conjunto de estados "A", "B"
  public ArrayList<ConjuntoEstados> convierteAFD(Automata automata) {

    ArrayList<ConjuntoEstados> estadosD = new ArrayList<ConjuntoEstados>();
    CerraduraEpsilon cerraduraEpsilon = new CerraduraEmpsilon();  //Corregir a como lo deje Rugal
    Mueve mueve = new Mueve();
    ConjuntoEstados U = new ConjuntoEstados();

    //Suponiendo que Rugal hara un metodo cerradura para la clase cerraduraEpsilon
    estadosD.add(cerraduraEpsilon.cerradura(automata.getTransiciones(0)));

    //recorrer la lista; mientras no haya marcados o llegemos al final de la lista
    for (int i = 0; estadosD.get(i).getMarcado() == false && i <= estadosD.size(); i++){

      estadosD.get(i).setMarcado(true); //Se marca el estado T
      
      //Suponiendo que getSimbolosTransiciones devuelve un string con  
      String[] transiciones = automata.getSimbolosTransiciones(i);
      for(int k = 0; k <= transiciones.length; k++){

        // Suponiendo que Roborto hace un metodo mover para la clase mueve
        U = cerraduraEpsilon(mueve.mover(estadosD.get(i).getEstados()),transiciones[k]);
        U.setId(id); // Determinar como agregar id a los conjuntos de estados

        if(estadosD.indexOf(U) != -1){ //Index of regresa -1 si U no se encuentra en el Arreglo


        }
      }
      
    }
    
    return estadosD;
  }


  
}