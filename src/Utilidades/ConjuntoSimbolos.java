package Utilidades.Gramatica;

import java.util.ArrayList;

public class ConjuntoSimbolos{
    private String id;
    private ArrayList<String> simbolos;

    //constructor
    public ConjuntoSimbolos(){
        id = null;
        simbolos = new ArrayList<String>();
    }

    public ConjuntoSimbolos(String identificador, ArrayList<String> simbols){
        id = identificador;
        simbolos = simbols;
    }

    //getters
    public ArrayList<String> getSimbolos(){
        return simbolos;
    }
    public String getId(){
        return id;
    }

    //setters
    public void setSimbolos(ArrayList<String> simbolos){
        this.simbolos = simbolos;
    }

    public void setId(String id){
        this.id = id; 
    }
}