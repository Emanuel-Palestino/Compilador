package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;

import Utilidades.ConjuntoEstados;
import Utilidades.Automatas.Automata;
import Utilidades.Automatas.AutomataDeterminista;
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
            else if (simbols[0].equals("todo"))
                simbolos.add("todo");
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
        else if (simbolo.equals("todo"))
            simbolos.add("todo");
        else if (AFN.getAlfabeto().simboloValido(simbolo)) {
            simbolos.add(simbolo);
        }

        for (Integer estado : auxiliar) {
            for (String simbol : simbolos) {
                arrayAux = AFN.getEstadosDestinoSimbolo(estado, simbol);
                for (int edo : arrayAux)
                    resultado.getEstados().add(edo);
            }
        }
        return resultado;
    }

    public static ConjuntoEstados mueve(ConjuntoEstados T, String simbolo, AutomataDeterminista AFD) {
        ConjuntoEstados resultado = new ConjuntoEstados();
        ListaDoblementeEnlazadaD estado = new ListaDoblementeEnlazadaD();

        // obtener la listaEnlazada que corresponda al conjunto
        for (int i = 0; i < AFD.getTotalEstados(); i++) {
            ListaDoblementeEnlazadaD lista = AFD.getTransiciones(i);
            if (lista.getEstado().equals(T)) {
                estado = lista;
                break;
            }
        }

        // comprobar transicion
        NodoListaD aux = estado.getInicio();
        while (aux != null) {
            if (aux.getTransicion().equals(simbolo)) {
                resultado = aux.getEstados();
                break;
            } else if (aux.getTransicion().equals("letra")) {
                if (AFD.getAlfabeto().letraValido(simbolo)) {
                    resultado = aux.getEstados();
                    break;
                }
            } else if (aux.getTransicion().equals("digito")) {
                if (AFD.getAlfabeto().digitoValido(simbolo)) {
                    resultado = aux.getEstados();
                    break;
                }
            } else if (aux.getTransicion().equals("todo")) {
                if (AFD.getAlfabeto().simboloValido(simbolo)) {
                    resultado = aux.getEstados();
                    break;
                }
            } else {
                String[] parte = aux.getTransicion().split("-");
                if (parte.length > 1 && parte[0].equals("letra") && !parte[1].equals(simbolo)) {
                    if (AFD.getAlfabeto().letraValido(simbolo)) {
                        resultado = aux.getEstados();
                        break;
                    }
                } else if (parte.length > 1 && parte[0].equals("digito") && !parte[1].equals(simbolo)) {
                    if (AFD.getAlfabeto().digitoValido(simbolo)) {
                        resultado = aux.getEstados();
                        break;
                    }
                } else if (parte.length > 1 && parte[0].equals("todo") && !parte[1].equals(simbolo)) {
                    if (AFD.getAlfabeto().simboloValido(simbolo)) {
                        resultado = aux.getEstados();
                        break;
                    }
                }
            }
            aux = aux.getSiguiente();
        }

        return resultado;
    }

}