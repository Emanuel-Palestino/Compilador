package Utilidades.Gramatica;

import java.util.ArrayList;

public class Gramatica {
    private ArrayList<String> noTerminales;
    private ArrayList<String> terminales;
    private ArrayList<ReglaProduccion> reglasProduccion;
    private String simboloInicial;
    
    // Constructores
    public Gramatica() {
        noTerminales = new ArrayList<String>();
        terminales = new ArrayList<String>();
        reglasProduccion = new ArrayList<ReglaProduccion>();
        simboloInicial = null;
    }

    // pasar cada uno de los atributos
    public Gramatica(ArrayList<String> ter, ArrayList<String> noTer) {
        noTerminales = noTer;
        terminales = ter;
        reglasProduccion = new ArrayList<ReglaProduccion>();
        simboloInicial = null;
    }

    public Gramatica(ArrayList<ReglaProduccion> reglas) {
        noTerminales = new ArrayList<String>();
        terminales = new ArrayList<String>();
        reglasProduccion = reglas;
        simboloInicial = null;
    }

    public Gramatica(String simbolo) {
        noTerminales = new ArrayList<String>();
        terminales = new ArrayList<String>();
        reglasProduccion = new ArrayList<ReglaProduccion>();
        simboloInicial = simbolo;
    }

    // getter y setters

    public ArrayList<String> getNoTerminales() {
        return noTerminales;
    }

    public ArrayList<String> getTerminales() {
        return terminales;
    }

    public ArrayList<ReglaProduccion> getReglasProduccion() {
        return reglasProduccion;
    }

    public String getSimboloInicial() {
        return simboloInicial;
    }

    public void setNoTerminales(ArrayList<String> noTerminales) {
        this.noTerminales = noTerminales;
    }

    public void setTerminales(ArrayList<String> terminales) {
        this.terminales = terminales;
    }

    public void setReglasProduccion(ArrayList<ReglasProduccion> reglas) {
        this.reglasProduccion = reglas;
    }

    public void setSimboloInicial(String simboloInicial) {
        this.simboloInicial = simboloInicial;
    }

    // Métodos
    boolean esTerminal(String aVerificar) {
        return getTerminales().contains(aVerificar);
    }

    ArrayList<ReglaProduccion> reglasDeSimbolo(String simbolo) {
        // regresar arraylist de las reglas de produccion del string de entrada
        ArrayList<ReglaProduccion> resultado = new ArrayList<ReglaProduccion>();
        for (ReglaProduccion buscando : reglasProduccion) {
            if (buscando.getSimboloGramatical() == simbolo) {
                resultado.add(buscando);
            }
        }
        return resultado;
    }
}
