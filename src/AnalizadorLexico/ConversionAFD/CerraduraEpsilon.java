package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import Utilidades.ConjuntoEstados;
import Utilidades.Automata;

//class como metodo de Thompson
public class CerraduraEpsilon {

    public static ConjuntoEstados doCerraduraEpsilon(ConjuntoEstados conjunto, Automata A) {
        ConjuntoEstados resultado = new ConjuntoEstados();
        ArrayList<Integer> estadosEjemplo = conjunto.getEstados();
        Stack<Integer> T = new Stack<Integer>(); // Inicializacion de pila "T"
        int verificador; // Verificador almacena un estado especifico como entero

        for (Integer estado : estadosEjemplo) {
            T.push(estado);
            resultado.insertarEstado(estado);
        }

        ArrayList<Integer> estadosResultado = resultado.getEstados();
        while (!T.isEmpty()) { // while pila no este vacia
            verificador = T.pop();
            int[] arreglo = A.getEstadosDestinoSimbolo(verificador, "Ɛ");
            for (Integer v : arreglo) {
                if (!estadosResultado.contains(v)) {
                    resultado.insertarEstado(v);
                    T.push(v);
                }
            }
        }

        // Ordenar estados
        Collections.sort(resultado.getEstados());

        return resultado;
    }
}
