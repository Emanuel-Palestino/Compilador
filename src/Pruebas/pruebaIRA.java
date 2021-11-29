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
        Elemento temporal3 = new Elemento ();
        ArrayList<String> producciones = new ArrayList<String>();
        ArrayList<String> producciones2 = new ArrayList<String>();
        producciones.add(0, "■");
        producciones.add(1, "E");
        producciones.add(2, "+");
        producciones.add(3, "T");
        producciones2.add(0, "■");
        producciones2.add(1, "F");
        producciones2.add(2, "*");
        producciones2.add(3, "T");
        temporal2.setSimboloGramatical("E");
        temporal3.setSimboloGramatical("E");
        temporal2.setProduccion(producciones);
        temporal3.setProduccion(producciones2);
        ConjuntoElementos prueba = new ConjuntoElementos();
        prueba.agregar(temporal2);
        prueba.agregar(temporal3);
        ConjuntoElementos temporalResultados = new ConjuntoElementos();
        /*
        temporalResultados = IrA.hacer(prueba, temporal2.getSimboloGramatical());
        Set <Elemento> resultados =  temporalResultados.getElementos();
        for (Elemento recorre : resultados) {
            Elemento finale = new Elemento(recorre);
            System.out.println( finale.getProduccion());
        }  
        */      
    }
}
