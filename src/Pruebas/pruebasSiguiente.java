package Pruebas;

import java.util.ArrayList;

import AnalizadorSintactico.PrimerosSiguientes.PrimerosSiguientes;
import Utilidades.ConjuntoSimbolos;
import Utilidades.Gramatica.Gramatica;

public class pruebasSiguiente {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica1.txt");
        PrimerosSiguientes prueba = new PrimerosSiguientes();
        ArrayList <ConjuntoSimbolos> resultado = new ArrayList<ConjuntoSimbolos>();

        resultado = prueba.siguientes(gramatica.getNoTerminales(),gramatica);

        for(ConjuntoSimbolos recorre : resultado){
            System.out.print(recorre.getId());
            System.out.println(recorre.getSimbolos());
        }
    }
}
