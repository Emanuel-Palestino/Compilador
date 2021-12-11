package Pruebas;

import java.util.ArrayList;
import AnalizadorSintactico.ColeccionCanonica.IrA;
import Utilidades.ConjuntoElementos.ConjuntoElementos;
import Utilidades.ConjuntoElementos.Elemento;
import Utilidades.Gramatica.Gramatica;

public class pruebaCerradura {
    public static void main(String[] args) {
        ConjuntoElementos resultados = new ConjuntoElementos();
        ArrayList <String> produccion = new ArrayList <String>();
        Elemento elemento = new Elemento();
<<<<<<< HEAD
        produccion.add("T");
        produccion.add("*");
        produccion.add("■");
        produccion.add("F");
        
        
        elemento.setSimboloGramatical("T");
=======
        Elemento elemento2 = new Elemento();
        Elemento elemento3 = new Elemento();
        Elemento elemento4 = new Elemento();
        Elemento elemento5 = new Elemento();
        Elemento elemento6 = new Elemento();
        Elemento elemento7 = new Elemento();
        ArrayList<String> producciones = new ArrayList<String>();
        ArrayList<String> producciones2 = new ArrayList<String>();
        ArrayList<String> producciones3 = new ArrayList<String>();
        ArrayList<String> producciones4 = new ArrayList<String>();
        ArrayList<String> producciones5 = new ArrayList<String>();
        ArrayList<String> producciones6 = new ArrayList<String>();
        produccion.add ("■");
        produccion.add("E");
        produccion.add("$");

        producciones.add(0, "■");
        producciones.add(1, "E");
        producciones.add(2, "+");
        producciones.add(3, "T");

        producciones2.add("■");
        producciones2.add("T");

        producciones3.add("■");
        producciones3.add("T");
        producciones3.add("*");
        producciones3.add("F");

        producciones4.add("■");
        producciones4.add("F");

        producciones5.add("■");
        producciones5.add("(");
        producciones5.add("E");
        producciones5.add(")");

        producciones6.add("■");
        producciones6.add("id");


        elemento.setSimboloGramatical("E'");
>>>>>>> 19689bd739b88ee1f61c9d0184d6b64a43694421
        elemento.setProduccion(produccion);

        elemento2.setSimboloGramatical("E");
        elemento2.setProduccion(producciones);
        
        elemento3.setSimboloGramatical("E");
        elemento3.setProduccion(producciones2);
        
        elemento4.setSimboloGramatical("T");
        elemento4.setProduccion(producciones3);

        elemento5.setSimboloGramatical("T");
        elemento5.setProduccion(producciones4);

        elemento6.setSimboloGramatical("F");
        elemento6.setProduccion(producciones5);

        elemento7.setSimboloGramatical("F");
        elemento7.setProduccion(producciones6);

        ConjuntoElementos entradaPrueba = new ConjuntoElementos();
        entradaPrueba.agregar(elemento);
        entradaPrueba.agregar(elemento2);
        entradaPrueba.agregar(elemento3);
        entradaPrueba.agregar(elemento4);
        entradaPrueba.agregar(elemento5);
        entradaPrueba.agregar(elemento6);
        entradaPrueba.agregar(elemento7);
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica.txt");
        
        //resultados = Cerradura.hacer(entradaPrueba, gramatica);
        resultados = IrA.hacer(entradaPrueba, "(", gramatica);
        for(Elemento recorre : resultados.getElementos()){
            System.out.print(recorre.getSimboloGramatical());
            System.out.println(recorre.getProduccion());
        }
    }
}
