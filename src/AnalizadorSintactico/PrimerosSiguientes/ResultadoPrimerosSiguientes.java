package AnalizadorSintactico.PrimerosSiguientes;

import java.util.ArrayList;
import Utilidades.Gramatica.ConjuntoSimbolos;

public class ResultadoPrimerosSiguientes{
    private ArrayList<ConjuntoSimbolos> primeros;
    private ArrayList<ConjuntoSimbolos> siguientes;
    
    //constructor
    public ResultadoPrimerosSiguientes(){
        primeros = new ArrayList<ConjuntoSimbolos>();
        siguientes = new ArrayList<ConjuntoSimbolos>();
    }
    public ResultadoPrimerosSiguientes(ArrayList<ConjuntoSimbolos> prim,ArrayList<ConjuntoSimbolos> sig){
        primeros = prim;
        siguientes = sig;
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