package Utilidades;

import java.util.ArrayList;

public class ConjuntoSimbolos{
    private String id;
    private ArrayList<String> simbolos;

    //constructor
    public ConjuntoSimbolos(){
        id = null;
        simbolos = new ArrayList<String>();
    }

    public ConjuntoSimbolos(String identificador, ArrayList<String> simbolosTemp){
        id = identificador;
        simbolos = simbolosTemp;
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

    //método
    public void agregarSimbolo(String simbolo){
        simbolos.add(simbolo);
    }

    public boolean contiene(String simbolo) {
        return simbolos.contains(simbolo);
    }
}