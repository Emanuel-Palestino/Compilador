package Utilidades;

import Utilidades.Alfabeto.Alfabeto;
import Utilidades.Automata;
import Utilidades.ConjuntoEstados;
import Utilidades.Listas.ListaDoblementeEnlazada;

public class Mueve(ConjuntoEstados T, Automata a){
    totalEstados = a.getNumEstados();
    adyacencia = new ListaDoblementeEnlazada[totalEstados];
    
    for(int i=0 ; i < totalEstados ; i++){
        if(adyacencia[i+1] == a){
            a.insertarTransicion(adyacencia[i],adyacencia[i+1],a/*símbolo a analizar*/)
        }
    }
}
