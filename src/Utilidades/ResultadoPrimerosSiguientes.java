package Utilidades;

import java.util.ArrayList;

public class ResultadoPrimerosSiguientes{
    private ArrayList<ConjuntoSimbolos> primeros;
    private ArrayList<ConjuntoSimbolos> siguientes;
    
    //constructor
    public ResultadoPrimerosSiguientes(){
        primeros = new ArrayList<ConjuntoSimbolos>();
        siguientes = new ArrayList<ConjuntoSimbolos>();
    }
    public ResultadoPrimerosSiguientes(ArrayList<ConjuntoSimbolos> primerosTemp,ArrayList<ConjuntoSimbolos> siguientesTemp){
        primeros = primerosTemp;
        siguientes = siguientesTemp;
    } 

    //getters
    public ArrayList<ConjuntoSimbolos> getPrimeros(){
        return primeros;
    }

    public ArrayList<ConjuntoSimbolos> getSiguientes(){
        return siguientes;
    }

    //setters
    public void setPrimeros(ArrayList<ConjuntoSimbolos> primeros){
        this.primeros = primeros;
    }

    public void setSiguientes(ArrayList<ConjuntoSimbolos> siguientes){
        this.siguientes = siguientes;
    }

}