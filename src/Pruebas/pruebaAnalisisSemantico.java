package Pruebas;

import java.io.IOException;
import AnalizadorLexico.Final.EvaluarCodigo;
import AnalizadorSemantico.AnalisisSemantico;
import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.ColeccionCanonica;
import Utilidades.ResultadoAnalisisSemantico;

import Utilidades.AnalizadorLexico.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;
import Utilidades.Gramatica.Gramatica;

public class pruebaAnalisisSemantico {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/RecursosGramaticasClase/gramatica1.txt", "src/ArchivosExtra/RecursosGramaticasClase/accionSemantica1.txt");
        TablaLR tablaPrueba = new TablaLR();
        ResultadoAnalisisLexico resultadoLexico = new ResultadoAnalisisLexico();
        ColeccionCanonica coleccionPrueba = new ColeccionCanonica();
        ResultadoAnalisisSemantico resultadoSemantico;
        try {
            coleccionPrueba = ColeccionCanonica.hacer(gramatica);
            tablaPrueba = TablaLR.construir(coleccionPrueba, gramatica);
            resultadoLexico = EvaluarCodigo.evaluar("src/ArchivosExtra/RecursosGramaticasClase/programaC1.txt");

            // Resultado
            resultadoSemantico = AnalisisSemantico.analizar(resultadoLexico.getTokens(),resultadoLexico.getTiraTokens(),resultadoLexico.getTiraTokensSemantico(),gramatica,tablaPrueba);

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

            // imprimimos la traduccion final
            System.out.println("Traduccion: ");
            System.out.println(resultadoSemantico.getTraduccionFinal());

        } catch (IOException | ExcepcionER e) {
            e.printStackTrace();
        }
    }
}
