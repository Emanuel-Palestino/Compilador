package AnalizadorSintactico.ColeccionCanonica;

import java.util.ArrayList;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

public class cerradura {
    public ConjuntoElementos hacer(ConjuntoElementos entradaElementos){
        String posicionElemento;
        Gramatica gramatica = new Gramatica();
        ArrayList <ReglaProduccion> reglasSimbolo = new ArrayList<ReglaProduccion>();


        ConjuntoElementos resultadosCerradura = new ConjuntoElementos();
        while(true){
            for(Elemento recorreElementos : entradaElementos.getElementos()){   //con este for recorremos los elementos que hay en entradaElementos
                posicionElemento = recorreElementos.getSimboloDespuesDePunto();     //Obtenemos el elemento que esta despues de punto
                reglasSimbolo = gramatica.reglasDeSimbolo(posicionElemento);
                for(ReglaProduccion recorreReglasSimbolo : reglasSimbolo){
                    for(String recorreProduccion : recorreReglasSimbolo.getProduccion()){
                        
                    }
                }        //Con el objeto gramatica sacamos las reglas del elemento obtenido anteriormente
            }   
        }
    }
}
