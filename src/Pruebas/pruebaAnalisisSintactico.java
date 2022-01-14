package Pruebas;

import java.io.IOException;
import java.util.Stack;

import AnalizadorLexico.Final.EvaluarCodigo;
import AnalizadorSintactico.Analisis.AnalisisSintactico;
import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.ResultadoAnalisisLexico;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Gramatica.Gramatica;

public class pruebaAnalisisSintactico {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/GramaticaJS/gramaticajs1.txt");
        AnalisisSintactico analisisSintactico = new AnalisisSintactico();
        TablaLR tablaPrueba = new TablaLR();
        EvaluarCodigo pruebaCodigo = new EvaluarCodigo();
        ResultadoAnalisisLexico resultadoAnalisisL = new ResultadoAnalisisLexico();
        ColeccionCanonica coleccionPrueba = new ColeccionCanonica();
        ResultadoAnalisisSintactico resultadoAnalisisSin;
        try {
            coleccionPrueba = ColeccionCanonica.hacer(gramatica);
            tablaPrueba = TablaLR.construir(coleccionPrueba, gramatica);
            resultadoAnalisisL = EvaluarCodigo.evaluar("src/ArchivosExtra/programa.js");
            resultadoAnalisisSin = AnalisisSintactico.analizar(resultadoAnalisisL.getTiraTokens(), gramatica,tablaPrueba);
            // imprimimos la pila
            System.out.println("Imprimimos Pila");
            for (int i = 0; i < resultadoAnalisisSin.getPila().length; i++) {
                if (resultadoAnalisisSin.getPila()[i] == null) {
                    break;
                } else {
                    System.out.println(resultadoAnalisisSin.getPila()[i]);
                }
            }

            // imprimimos entrada
            System.out.println("Imprimimos Entrada");
            for (int i = 0; i < resultadoAnalisisSin.getEntrada().length; i++) {
                if (resultadoAnalisisSin.getEntrada()[i] == null) {
                    break;
                } else {
                    System.out.println(resultadoAnalisisSin.getEntrada()[i]);
                }
            }

            // imprimimos acciones
            System.out.println("Imprimimos Acciones");
            for (String recorreAcciones : resultadoAnalisisSin.getAccion()) {
                if (recorreAcciones == null) {
                    break;
                }

                System.out.println(recorreAcciones);

            }
        } catch (IOException | ExcepcionER e) {
            e.printStackTrace();
        }
    }
}
