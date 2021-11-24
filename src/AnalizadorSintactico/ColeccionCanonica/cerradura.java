package AnalizadorSintactico.ColeccionCanonica;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;

public class cerradura {
    public ConjuntoElementos hacer(ConjuntoElementos entradaElementos){
        String posicionElemento;

        ConjuntoElementos resultadosCerradura = new ConjuntoElementos();
        while(true){
            for(Elemento recorreElementos : entradaElementos.getElementos()){   //con este for recorremos los elementos que hay en entradaElementos
                for(String recorrerProduccion : recorreElementos.getProduccion()){  //con este for recorremos las producciones de elementos
                    posicionElemento = recorreElementos.getSimboloDespuesDePunto();
                    

                }
            }   
        }
    }
}
