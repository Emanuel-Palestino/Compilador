package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;
import AnalizadorLexico.AlgoritmoThompson.Thompson;
import Utilidades.Automata;
import Utilidades.ConjuntoEstados;
import Utilidades.Listas.ListaDoblementeEnlazadaD;

public class ConvierteAFD {


  public static void main(String[] args) {

    Thompson thomp = new Thompson();
    Automata a = new Automata("a");
    Automata b = new Automata("b");
    Automata aob = thomp.union(a, b);
    Automata res = thomp.concatenacion(b, aob);

    ConvierteAFD afd = new ConvierteAFD();
    ArrayList<ListaDoblementeEnlazadaD> resultado = afd.convierteAFD(res);
    System.out.println("a");

  }

  //Identificador de conjunto de estados "A", "B"
  public ArrayList<ListaDoblementeEnlazadaD> convierteAFD(Automata automata) {

    ArrayList<ListaDoblementeEnlazadaD> tranD = new ArrayList<ListaDoblementeEnlazadaD>();
    ArrayList<ConjuntoEstados> estadosD = new ArrayList<ConjuntoEstados>();
    char letra = 'A';
    
    //Suponiendo que Rugal hara un metodo cerradura para la clase cerraduraEpsilon
    ConjuntoEstados inicio = new ConjuntoEstados();
    inicio.insertarEstado(0);
    estadosD.add(CerraduraEpsilon.doCerraduraEpsilon(inicio, automata));
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
        U = CerraduraEpsilon.doCerraduraEpsilon(Mueve.mueve(estadosD.get(i), transiciones.get(k), automata),automata);
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
