package AnalizadorSintactico.ColeccionCanonica;

import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import java.util.Set;

public class IrA {
    public static ConjuntoElementos hacer (ConjuntoElementos conjuntoelementos, String simbolo){
        ConjuntoElementos resultadosIra = new ConjuntoElementos();
        Set <Elemento> elementos = conjuntoelementos.getElementos();
        for (Elemento busquedaElemento : elementos){
            Elemento auxiliar = new Elemento(busquedaElemento);
            if (auxiliar.getSimboloDespuesDePunto() != "" && auxiliar.getSimboloDespuesDePunto()==simbolo){
                auxiliar.moverPunto();
                resultadosIra.agregar(auxiliar);
            }        
        }
        return resultadosIra;
    }
}
