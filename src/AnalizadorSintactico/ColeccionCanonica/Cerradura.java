package AnalizadorSintactico.ColeccionCanonica;
import java.util.ArrayList;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class Cerradura {
    static public ConjuntoElementos hacer(ConjuntoElementos entradaElementos) {
        String B;
        Gramatica gramatica = new Gramatica();
        ArrayList<ReglaProduccion> reglasSimbolo = new ArrayList<ReglaProduccion>();

        ConjuntoElementos resultadosCerradura = new ConjuntoElementos();
        for (Elemento recorreElementos : entradaElementos.getElementos()) { // con este for recorremos los elementos que
            B = recorreElementos.getSimboloDespuesDePunto();                //Obtenemos la posición de B                                                    
            reglasSimbolo = gramatica.reglasDeSimbolo(B);                   //Obtenemos las reglas de produccion para B
            for (ReglaProduccion recorreReglasSimbolo : reglasSimbolo) {
                Elemento auxiliarConvierte = new Elemento(recorreReglasSimbolo);
                auxiliarConvierte.insertarPuntoInicial();
                resultadosCerradura.agregar(auxiliarConvierte);
            } 
        }
        return resultadosCerradura;
    }

}
