package Pruebas;

import java.util.ArrayList;
import java.util.Set;

import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;

public class pruebaIRA {
    public static void main(String[] args) {
        //Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica.txt");
        Elemento temporal2 = new Elemento();
        ArrayList<String> producciones = new ArrayList<String>();
        producciones.add(0, "■");
        producciones.add(1, "E");
        producciones.add(2, "+");
        producciones.add(3, "T");
        temporal2.setSimboloGramatical("E");
        temporal2.setProduccion(producciones);
        
        ConjuntoElementos temporal = new ConjuntoElementos(temporal2);
        ConjuntoElementos temporalResultados = new ConjuntoElementos();
        
        temporalResultados = IrA.hacer(temporal, temporal2.getSimboloGramatical());
        Set <Elemento> resultados =  temporalResultados.getElementos();
        for (Elemento recorre : resultados) {
            Elemento finale = new Elemento(recorre);
            System.out.println( finale.getProduccion());
        }        
    }
}
