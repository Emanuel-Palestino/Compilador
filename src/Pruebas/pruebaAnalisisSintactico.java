package Pruebas;

import java.io.IOException;

import AnalizadorLexico.Final.EvaluarCodigo;
import AnalizadorSintactico.Analisis.AnalisisSintactico;
import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.AnalizadorLexico.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Gramatica.Gramatica;

public class pruebaAnalisisSintactico {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajsFinal.txt");
        TablaLR tablaPrueba = new TablaLR();
        ResultadoAnalisisLexico resultadoAnalisisL = new ResultadoAnalisisLexico();
        ColeccionCanonica coleccionPrueba = new ColeccionCanonica();
        ResultadoAnalisisSintactico resultadoAnalisisSin;
        try {
            coleccionPrueba = ColeccionCanonica.hacer(gramatica);
            tablaPrueba = TablaLR.construir(coleccionPrueba, gramatica);
            resultadoAnalisisL = EvaluarCodigo.evaluar("src/ArchivosExtra/programaPruebaTokens.js");
            resultadoAnalisisSin = AnalisisSintactico.analizar(resultadoAnalisisL.getTiraTokens(), gramatica,tablaPrueba);
            // imprimimos la pila
            System.out.println("Imprimimos Pila");
            for(String recorrePila : resultadoAnalisisSin.getPila()){
                System.out.println(recorrePila);
            }

            // imprimimos entrada
            System.out.println("Imprimimos Entrada");
            for(String recorreEntradas : resultadoAnalisisSin.getEntrada()){
                System.out.println(recorreEntradas);
            }

            // imprimimos acciones
            System.out.println("Imprimimos Acciones");
            for (String recorreAcciones : resultadoAnalisisSin.getAccion()) {
                System.out.println(recorreAcciones);
            }

        } catch (IOException | ExcepcionER e) {
            e.printStackTrace();
        }
    }
}
