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

        // Tratar simbolo
        ArrayList<String> simbolos = new ArrayList<String>();
        String[] simbols = simbolo.split("-");
        if (simbols.length > 1) {
            if (simbols[0].equals("letra")) // letra
                simbolos.add("letra");
            else if (simbols[0].equals("digito")) // digito
                simbolos.add("digito");
        } else if (AFN.getAlfabeto().letraValido(simbolo)) {
            simbolos.add(simbolo);
            simbolos.add("letra");
        } else if (AFN.getAlfabeto().digitoValido(simbolo)) {
            simbolos.add(simbolo);
            simbolos.add("digito");
        } else if (simbolo.equals("digito"))
            simbolos.add("digito");
        else if (simbolo.equals("letra"))
            simbolos.add("letra");

        for (Integer estado : auxiliar) {
            for (String simbol : simbolos) {
                arrayAux = AFN.getEstadosDestinoSimbolo(estado, simbol);
                for (int edo : arrayAux)
                    resultado.getEstados().add(edo);
            }
        }
        return resultado;
    }

    public static ConjuntoEstados mueve(ConjuntoEstados T, String simbolo, ArrayList<ListaDoblementeEnlazadaD> AFD) {
        ConjuntoEstados resultado = new ConjuntoEstados();
        ListaDoblementeEnlazadaD estado = new ListaDoblementeEnlazadaD();
        for (ListaDoblementeEnlazadaD lista : AFD) {
            if (lista.getEstado().equals(T)) {
                estado = lista;
                break;
            }
        }

        NodoListaD aux = estado.getInicio();
        while (aux != null) {
            if (aux.getTransicion().equals(simbolo)) {
                resultado = aux.getEstados();
                break;
            }
            aux = aux.getSiguiente();
        }

        return resultado;
    }

}