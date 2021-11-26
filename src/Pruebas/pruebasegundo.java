package Pruebas;
import java.util.ArrayList;
import AnalizadorSintactico.PrimerosSiguientes.*;
import Utilidades.ConjuntoSimbolos;
import Utilidades.Gramatica.Gramatica;
public class pruebasegundo { 
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramatica1.txt");
        PrimerosSiguientes prueba = new PrimerosSiguientes();
        ArrayList <ConjuntoSimbolos> resultados = new ArrayList<ConjuntoSimbolos>();

        resultados = prueba.siguientes(gramatica.getNoTerminales(), gramatica);

        for (ConjuntoSimbolos recorre : resultados) {
        System.out.print(recorre.getId());
        System.out.println(recorre.getSimbolos());
        }        
    }
}

