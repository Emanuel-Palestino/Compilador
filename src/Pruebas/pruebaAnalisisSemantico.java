package Pruebas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import AnalizadorLexico.Final.EvaluarCodigo;
import AnalizadorSemantico.AnalisisSemantico;
import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.ResultadoAnalisisSemantico;

import Utilidades.AnalizadorLexico.ResultadoAnalisisLexico;
import Utilidades.AnalizadorLexico.Token;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.SimboloGramatical;

public class pruebaAnalisisSemantico {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajsFinal.txt");
        TablaLR tablaPrueba = new TablaLR();
        ResultadoAnalisisLexico resultadoLexico = new ResultadoAnalisisLexico();
        ColeccionCanonica coleccionPrueba = new ColeccionCanonica();
        ResultadoAnalisisSemantico resultadoSemantico;
        try {
            coleccionPrueba = ColeccionCanonica.hacer(gramatica);
            tablaPrueba = TablaLR.construir(coleccionPrueba, gramatica);
            resultadoLexico = EvaluarCodigo.evaluar("src/ArchivosExtra/programaPruebaTokens.js");
            resultadoSemantico = AnalisisSemantico.analizar(resultadoLexico.getTokens(),resultadoLexico.getTiraTokens(),gramatica,tablaPrueba);
            // imprimimos la pilaString
            System.out.println("Imprimimos Pila");
            for(String recorrePila : resultadoSemantico.getPilaString()){
                System.out.println(recorrePila);
            }

            // imprimimos entradaString
            System.out.println("Imprimimos Entrada");
            for(String recorreEntradas : resultadoSemantico.getEntradaString()){
                System.out.println(recorreEntradas);
            }

            // imprimimos acciones
            System.out.println("Imprimimos Acciones");
            for (String recorreAcciones : resultadoSemantico.getAccion()) {
                System.out.println(recorreAcciones);
            }
            //imprimimos la pila con objeto
            System.out.println("Imprimimos Pila Objeto");
            for(Stack<SimboloGramatical> recorreStackInterno : resultadoSemantico.getPila()){
                for(SimboloGramatical recorreSimboloGramatical : recorreStackInterno){
                    System.out.print(recorreSimboloGramatical.getSimboloGramatical() + " ");
                }
                System.out.println();
            }

            //imprimimos la entrada con objetos
            System.out.println("Imprimimos entrada con objeto");
            for(ArrayList<Token> recorreArrayListInterno : resultadoSemantico.getEntrada()){
                for(Token recorrerTokens : recorreArrayListInterno){
                    System.out.print(recorrerTokens.getToken());
                }
                System.out.println();
            }

        } catch (IOException | ExcepcionER e) {
            e.printStackTrace();
        }
    }
}
