package Utilidades.Gramatica;

import java.util.ArrayList;

public class ReglaProduccion{
    private String simboloGramatical;
    private ArrayList<String> produccion;
    private boolean marcado;

    //constructor
    public ReglaProduccion(){
        simboloGramatical = null;
        produccion = new ArrayList<String>();
        marcado = false;
    }
    public ReglaProduccion(String gramatical, ArrayList<String> production,boolean marked){
        simboloGramatical = gramatical;
        produccion = production;
        marcado = marked;
    } 

    //getters
    public String getSimboloGramatical(){
        return simboloGramatical;
    }

    public ArrayList<String> getProduccion(){
        return produccion;
    }

    public boolean getMarcado(){
        return marcado;
    }

    //setters
    public void setSimboloGramatical(String simboloGramatical){
        this.simboloGramatical = simboloGramatical;
    }

    public void setProduccion(ArrayList<String> produccion){
        this.produccion = produccion;
    }

    public void setMarcado(boolean marcado){
        this.marcado = marcado;
    }
}