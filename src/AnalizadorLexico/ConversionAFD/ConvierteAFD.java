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
    Automata aobk = thomp.cerraduraKlenee(aob);
    Automata aobka = thomp.concatenacion(aobk, a);
    Automata aobkab = thomp.concatenacion(aobka, b);
    Automata res = thomp.concatenacion(aobkab, b);

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
    for (int i = 0; i < estadosD.size() && estadosD.get(i).getMarcado() == false; i++){
      
      estadosD.get(i).setMarcado(true); //Se marca el estado T
      
      ArrayList <String> transiciones = new ArrayList<String>();

      for (Integer estado : estadosD.get(i).getEstados()) {
        
        for(String transicion: automata.getSimbolosTransiciones(estado)){

          if(!transicion.equals("Ɛ") && !transiciones.contains(transicion))
            transiciones.add(transicion);
        }
        //Usar for each para recorrer array de stirng que devuelve getSimbolosTransiciones
        //Y meter a transiciones
      }

      for(int k = 0; k < transiciones.size(); k++){
        
        ConjuntoEstados U = new ConjuntoEstados();
        // Suponiendo que Roborto hace un metodo mover para la clase mueve
        U = CerraduraEpsilon.doCerraduraEpsilon(Mueve.mueve(estadosD.get(i), transiciones.get(k), automata),automata);
        Boolean bandera = true;
        for (ConjuntoEstados a : estadosD) {
          if(U.getEstados().equals(a.getEstados())){
            U = a;
            bandera = false;
          }
        }
        if(bandera){ //Index of regresa -1 si U no se encuentra en el Arreglo
          
          letra++;
          U.setId(letra +"");
          U.setMarcado(false);
          estadosD.add(U);
        }

        if(i == tranD.size()){
          ListaDoblementeEnlazadaD lista = new ListaDoblementeEnlazadaD();
          lista.insertar(U, transiciones.get(k));
          tranD.add(lista);
        }else{
          tranD.get(i).insertar(U, transiciones.get(k));
        }
        
      }
    }
    
    return tranD;
  
  }


}
