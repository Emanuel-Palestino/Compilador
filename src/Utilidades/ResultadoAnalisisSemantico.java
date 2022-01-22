package Utilidades;

import java.util.ArrayList;
import java.util.Stack;

import Utilidades.AnalizadorLexico.Token;
import Utilidades.Gramatica.SimboloGramatical;


public class ResultadoAnalisisSemantico {
    private Stack<Stack<SimboloGramatical>> pila;
    private Stack<String> pilaString;
    private ArrayList<ArrayList<Token>> entrada;
    private ArrayList<String> entradaString;
    private ArrayList<String> accion;

    //Constructores
    public ResultadoAnalisisSemantico(Stack<Stack<SimboloGramatical>> pila, ArrayList<ArrayList<Token>> entrada, ArrayList<String> accion, Stack<String> pilaString, ArrayList<String> entradaString){
        this.pila = pila;
        this.entrada = entrada;
        this.accion = accion;
        this.pilaString = pilaString;
        this.entradaString = entradaString;
    }


    //Getters 
    public Stack<Stack<SimboloGramatical>> getPila(){
        return pila;
    }

    public ArrayList<ArrayList<Token>> getEntrada(){
        return entrada;
    }

    public ArrayList<String> getAccion() {
        return accion;
    }

    public Stack<String> getPilaString() {
        return pilaString;
    }

    public ArrayList<String> getEntradaString() {
        return entradaString;
    }


    //Setters
    public void setPila(Stack<Stack<SimboloGramatical>> pila){
        this.pila = pila;
    }

    public void setEntrada(ArrayList<ArrayList<Token>> entrada){
        this.entrada = entrada;
    }

    public void setAccion(ArrayList<String> accion){
        this.accion = accion;
    }

    public void setPilaString(Stack<String> pilaString){
        this.pilaString = pilaString;
    }

    public void setEntradaString(ArrayList<String> entradaString){
        this.entradaString = entradaString;
    }
    
    //Metodos
    
}
