package Pruebas;

import java.io.IOException;

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
        Gramatica gramatica = new Gramatica("src/ArchivosExtra/gramaticajs1.txt");
        AnalisisSintactico analisisSintactico = new AnalisisSintactico();
        TablaLR tablaPrueba = new TablaLR();
        EvaluarCodigo pruebaCodigo = new EvaluarCodigo();
        ResultadoAnalisisLexico resultadoAnalisisL = new ResultadoAnalisisLexico();
        ColeccionCanonica coleccionPrueba = new ColeccionCanonica();
        ResultadoAnalisisSintactico resultadoAnalisisSin;
        try {
            coleccionPrueba = ColeccionCanonica.hacer(gramatica);
            tablaPrueba = TablaLR.construir(coleccionPrueba, gramatica);
            resultadoAnalisisL = EvaluarCodigo.evaluar("src/ArchivosExtra/programa2.js");
            resultadoAnalisisSin = AnalisisSintactico.analizar(resultadoAnalisisL.getTiraTokens(), gramatica, tablaPrueba);
            System.out.println(resultadoAnalisisSin.getPila());
            System.out.println(resultadoAnalisisSin.getAccion());
            System.out.println(resultadoAnalisisSin.getEntrada());
        } catch (IOException | ExcepcionER e) {
            e.printStackTrace();
        }
    }
}
