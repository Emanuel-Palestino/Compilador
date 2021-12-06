package Pruebas;

import java.util.ArrayList;

import AnalizadorSintactico.ColeccionCanonica.Cerradura;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;

public class pruebaCerradura {
    public static void main(String[] args) {
        ConjuntoElementos resultados = new ConjuntoElementos();
        ArrayList <String> produccion = new ArrayList <String>();
        Elemento elemento = new Elemento();
        produccion.add("T");
        produccion.add("*");
        produccion.add("■");
        produccion.add("F");
        
        
        elemento.setSimboloGramatical("T");
        elemento.setProduccion(produccion);
        ConjuntoElementos entradaPrueba = new ConjuntoElementos(elemento);

        Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica.txt");

        resultados = Cerradura.hacer(entradaPrueba, gramatica);
        for(Elemento recorre : resultados.getElementos()){
            System.out.print(recorre.getSimboloGramatical());
            System.out.println(recorre.getProduccion());
        }
    }
}
