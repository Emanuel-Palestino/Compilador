package AnalizadorSintactico.ColeccionCanonica;
import java.util.ArrayList;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class Cerradura {
    static public ConjuntoElementos hacer(ConjuntoElementos entradaElementos, Gramatica gramatica) {
        String B;
        ArrayList<ReglaProduccion> reglasSimbolo = new ArrayList<ReglaProduccion>();
        ConjuntoElementos resultadosCerradura = new ConjuntoElementos();

        for (Elemento recorreElementosEntrada : entradaElementos.getElementos())
            resultadosCerradura.agregar(new Elemento(recorreElementosEntrada));

        for (int i = 0; i < resultadosCerradura.getElementos().size(); i++) {
            Elemento elementoActual = resultadosCerradura.getElementos().get(i);
            B = elementoActual.getSimboloDespuesDePunto();
            reglasSimbolo = gramatica.reglasDeSimbolo(B);
            for (ReglaProduccion recorreReglasProduccion : reglasSimbolo) {
                Elemento aux = new Elemento(recorreReglasProduccion);
                aux.insertarPuntoInicial();
                aux.getProduccion().remove("Ɛ");
                resultadosCerradura.agregar(aux);
            }
        }

        return resultadosCerradura;

    }

}
