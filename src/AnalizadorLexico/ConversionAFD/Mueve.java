package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;

import Utilidades.Automata;
import Utilidades.ConjuntoEstados;
import Utilidades.Listas.ListaDoblementeEnlazadaD;
import Utilidades.Listas.NodoListaD;

public class Mueve {
    public static ConjuntoEstados mueve(ConjuntoEstados T, String simbolo, Automata AFN) {
        ConjuntoEstados resultado = new ConjuntoEstados();
        ArrayList<Integer> auxiliar = T.getEstados();
        int[] arrayAux;

        for (int i = 0; i < auxiliar.size(); i++) {
            arrayAux = AFN.getEstadosDestinoSimbolo(auxiliar.get(i), simbolo);
            for (int estado : arrayAux) {
                resultado.getEstados().add(estado);
            }
        }
        return resultado;
    }

    public static ConjuntoEstados mueve(ConjuntoEstados T, String simbolo, ArrayList<ListaDoblementeEnlazadaD> AFD) {
        ConjuntoEstados resultado = new ConjuntoEstados();
        ListaDoblementeEnlazadaD estado = new ListaDoblementeEnlazadaD();
        for(ListaDoblementeEnlazadaD lista : AFD) {
           if (lista.getEstado().equals(T)) {
               estado = lista;
               break;
           } 
        }

        NodoListaD aux = estado.getInicio();
        while(aux != null) {
            if (aux.getTransicion().equals(simbolo)) {
                resultado = aux.getEstados();
                break;
            }
            aux = aux.getSiguiente();
        }

        return resultado;
    }

}