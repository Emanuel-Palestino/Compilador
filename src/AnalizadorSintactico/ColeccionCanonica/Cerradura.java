package AnalizadorSintactico.ColeccionCanonica;
import java.util.ArrayList;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class Cerradura {
    public ConjuntoElementos hacer(ConjuntoElementos entradaElementos, Gramatica gramatica) {
        String B;
        ArrayList<ReglaProduccion> reglasSimbolo = new ArrayList<ReglaProduccion>();
        ConjuntoElementos resultadosCerradura = new ConjuntoElementos();
        ArrayList<String> simboloInicialApostrofe = new ArrayList<String>();
        String simboloApostrofeString;
        simboloInicialApostrofe.add(gramatica.getSimboloInicial());     //Hacemos un arraylist que contendra el Simboloinicial' un ejemplo seria E'
        simboloInicialApostrofe.add("'");                                       
        simboloApostrofeString = String.join("", simboloInicialApostrofe);      //Convertimos el arraylist en un String para poder comparar

        for(Elemento recorreElementos : entradaElementos.getElementos()) { // con este for recorremos los elementos que
            if(recorreElementos.getSimboloGramatical().equals(simboloApostrofeString)){
                for(String recorreNoTerminales : gramatica.getNoTerminales()){
                    reglasSimbolo = gramatica.reglasDeSimbolo(recorreNoTerminales);
                    for (ReglaProduccion recorreReglasSimbolo : reglasSimbolo) {
                        Elemento auxiliarConvierte = new Elemento(recorreReglasSimbolo);
                        auxiliarConvierte.insertarPuntoInicial();
                        resultadosCerradura.agregar(auxiliarConvierte);
                    }
                }
            }else{
                B = recorreElementos.getSimboloDespuesDePunto();                //Obtenemos la posición de B                                                    
                reglasSimbolo = gramatica.reglasDeSimbolo(B);                   //Obtenemos las reglas de produccion para B
                for (ReglaProduccion recorreReglasSimbolo : reglasSimbolo) {
                    Elemento auxiliarConvierte = new Elemento(recorreReglasSimbolo);
                    auxiliarConvierte.insertarPuntoInicial();
                    resultadosCerradura.agregar(auxiliarConvierte);
                } 
            }
            
        }
        return resultadosCerradura;
    }

}
