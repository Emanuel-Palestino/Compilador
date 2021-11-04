package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;
import java.util.List;


import Utilidades.Automata;
import Utilidades.ConjuntoEstados;
import Utilidades.Listas.ListaDoblementeEnlazadaD;

public class ConvierteAFD {



  //Identificador de conjunto de estados "A", "B"
  public ArrayList<ListaDoblementeEnlazadaD> convierteAFD(Automata automata) {

    ArrayList<ListaDoblementeEnlazadaD> tranD = new ArrayList<ListaDoblementeEnlazadaD>();
    ArrayList<ConjuntoEstados> estadosD = new ArrayList<ConjuntoEstados>();
    char letra = 'A';
    
    //Suponiendo que Rugal hara un metodo cerradura para la clase cerraduraEpsilon
    estadosD.add(CerraduraEpsilon.doCerradura(0, automata));
    estadosD.get(0).setId("A");
    
    //recorrer la lista; mientras no haya marcados o llegemos al final de la lista
    for (int i = 0; estadosD.get(i).getMarcado() == false && i <= estadosD.size(); i++){
      
      estadosD.get(i).setMarcado(true); //Se marca el estado T
      
      ArrayList <String> transiciones = new ArrayList<String>();

      for (Integer estado : estadosD.get(i).getEstados()) {
        
        for(String transicion: automata.getSimbolosTransiciones(estado)){

          transiciones.add(transicion);
        }
        //Usar for each para recorrer array de stirng que devuelve getSimbolosTransiciones
        //Y meter a transiciones
      }

      for(int k = 0; k <= transiciones.size(); k++){
        
        ConjuntoEstados U = new ConjuntoEstados();
        // Suponiendo que Roborto hace un metodo mover para la clase mueve
        U = CerraduraEpsilon.doCerradura(Mueve.mueve(estadosD.get(i), automata),automata);
        
        if(!estadosD.contains(U)){ //Index of regresa -1 si U no se encuentra en el Arreglo
          
          U.setId(letra +"");
          letra++;
          U.setMarcado(false);
          estadosD.add(U);
        }
        ListaDoblementeEnlazadaD lista = new ListaDoblementeEnlazadaD();
        lista.insertar(U, transiciones.get(k));
        tranD.add(lista);
      }
    }
    
    return tranD;
  
  }


}
