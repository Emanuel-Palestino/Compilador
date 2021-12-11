package AnalizadorSintactico.ColeccionCanonica;

import Utilidades.Gramatica.Gramatica;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;

import java.util.ArrayList;

public class IrA {
    public static ConjuntoElementos hacer (ConjuntoElementos conjuntoelementos, String simbolo, Gramatica gramatica){
        ConjuntoElementos resultadosIra = new ConjuntoElementos();
        ArrayList <Elemento> elementos = conjuntoelementos.getElementos();
        for (Elemento busquedaElemento : elementos){
            Elemento auxiliar = new Elemento(busquedaElemento);
            if (!auxiliar.getSimboloDespuesDePunto().equals("") && auxiliar.getSimboloDespuesDePunto().equals(simbolo)){
                auxiliar.moverPunto();
                resultadosIra.agregar(auxiliar);
            }        
        }
        return Cerradura.hacer(resultadosIra, gramatica);
    }
}
