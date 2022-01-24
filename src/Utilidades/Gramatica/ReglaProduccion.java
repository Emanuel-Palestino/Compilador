package Utilidades.Gramatica;

import java.util.ArrayList;

import Utilidades.AccionSemantica;

public class ReglaProduccion{
    private SimboloGramatical simboloGramatical;
    private ArrayList<String> produccion;
    private boolean marcado;
    private boolean marcadoSiguiente;
    private AccionSemantica accion;

    //constructor
    public ReglaProduccion(){
        simboloGramatical = null;
        produccion = new ArrayList<String>();
        marcado = false;
        marcadoSiguiente = false;
        accion = new AccionSemantica();
    }
    public ReglaProduccion(String gramatical, ArrayList<String> produccionTemp,boolean marcadoTemp){
        simboloGramatical.setSimboloGramatical(gramatical);
        produccion = produccionTemp;
        marcado = marcadoTemp;
        marcadoSiguiente = false;
    } 
    
    public ReglaProduccion(String gramatical, ArrayList<String> produccionTemp){
        SimboloGramatical simboloAux = new SimboloGramatical(gramatical);
        simboloGramatical = new SimboloGramatical(simboloAux);
        produccion = produccionTemp;
        marcado = false;
        marcadoSiguiente = false;
    } 

    public ReglaProduccion(String simboloGramatical, ArrayList<String> reglaProduccion, String accionSemantica) {
        this.simboloGramatical = new SimboloGramatical(simboloGramatical);
        produccion = reglaProduccion;
        marcado = false;
        marcadoSiguiente = false;
        accion = new AccionSemantica(accionSemantica);
    }

    //getters

    public SimboloGramatical getObjetoSimboloGramatical(){
        return simboloGramatical;
    }

    public String getStringSimboloGramatical(){
        return simboloGramatical.getSimboloGramatical();
    }

    public ArrayList<String> getProduccion(){
        return produccion;
    }

    public boolean getMarcado(){
        return marcado;
    }

    public boolean getMarcadoSiguiente(){
        return marcadoSiguiente;
    }

    public AccionSemantica getAccionSemantica() {
        return accion;
    }

    //setters
    public void setSimboloGramatical(String simboloGramatical){
        this.simboloGramatical = new SimboloGramatical(simboloGramatical);
    }

    public void setProduccion(ArrayList<String> produccion){
        this.produccion = produccion;
    }

    public void setMarcado(boolean marcado){
        this.marcado = marcado;
    }

    public void setMarcadoSiguiente(boolean marcadoSiguiente){
        this.marcadoSiguiente = marcadoSiguiente;
    }

    public boolean indexReglaProduccion (String producciones,ArrayList<String> produccionTemp){
        for (String recorreProducciones : produccionTemp) {
            if (recorreProducciones.equals(producciones)){
                return true;
            }
        } 
        return false;
    }
}