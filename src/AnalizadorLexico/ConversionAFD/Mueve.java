package AnalizadorLexico.ConversionAFD;

import java.util.ArrayList;

import AnalizadorLexico.AlgoritmoThompson.Thompson;
import Utilidades.Automata;
import Utilidades.ConjuntoEstados;

public class Mueve {
    public static ConjuntoEstados mueve(ConjuntoEstados T, String simbolo, Automata afn) {
        ConjuntoEstados resultado = new ConjuntoEstados();
        ArrayList<Integer> auxiliar = T.getEstados();
        int[] arrayAux;

        for (int i = 0; i < auxiliar.size(); i++) {
            arrayAux = afn.getEstadosDestinoSimbolo(auxiliar.get(i), simbolo);
            for (int estado : arrayAux) {
                resultado.getEstados().add(estado);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        Automata afd = new Automata("a");
        Automata afd2 = new Automata("b");
        Thompson aux = new Thompson();
        Automata ejemplo = aux.concatenacion(afd, afd2);
        ConjuntoEstados estados = new ConjuntoEstados();
        estados.getEstados().add(0);
        
        ConjuntoEstados resultado = Mueve.mueve(estados, "a" , ejemplo);
        System.out.println("Resultado ");
    }
}