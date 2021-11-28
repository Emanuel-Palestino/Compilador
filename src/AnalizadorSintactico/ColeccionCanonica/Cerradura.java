package AnalizadorSintactico.ColeccionCanonica;
import java.util.ArrayList;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class Cerradura {
    static public ConjuntoElementos hacer(ConjuntoElementos entradaElementos, Gramatica gramatica) {
        String B,simboloDespuesDePunto;
        ArrayList<ReglaProduccion> reglasSimbolo = new ArrayList<ReglaProduccion>();
        ConjuntoElementos resultadosCerradura = new ConjuntoElementos();
        ConjuntoElementos resultadosNuevoB = new ConjuntoElementos();

        for(Elemento recorreElementosEntrada : entradaElementos.getElementos()){
            resultadosCerradura.agregar(recorreElementosEntrada);
        }

        for(Elemento recorreElementos : resultadosCerradura.getElementos()) { // con este for recorremos los elementos que      
            B = recorreElementos.getSimboloDespuesDePunto();                //Obtenemos la posición de B                                                    
            reglasSimbolo = gramatica.reglasDeSimbolo(B);                   //Obtenemos las reglas de produccion para B
            for (ReglaProduccion recorreReglasSimbolo : reglasSimbolo) {
                Elemento auxiliarConvierte = new Elemento(recorreReglasSimbolo);
                auxiliarConvierte.insertarPuntoInicial();
                resultadosCerradura.agregar(auxiliarConvierte);
                simboloDespuesDePunto = auxiliarConvierte.getSimboloDespuesDePunto();
                if(!B.equals(simboloDespuesDePunto)){
                    ConjuntoElementos elementosNuevoB = new ConjuntoElementos(auxiliarConvierte);
                    resultadosNuevoB = hacer(elementosNuevoB, gramatica);
                    for(Elemento recorreElementosNuevos : resultadosNuevoB.getElementos()){
                        resultadosCerradura.agregar(recorreElementosNuevos);
                    }
                }
            } 
        }
        return resultadosCerradura;
    }

}
