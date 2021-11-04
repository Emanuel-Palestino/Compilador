package AnalizadorLexico.ConversionAFD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Utilidades.Archivo;
import Utilidades.Automata;
import Utilidades.Listas.ListaDoblementeEnlazada;
import Utilidades.Listas.NodoLista;

public class IniciarConjuntos{
    public IniciarConjuntos() throws FileNotFoundException, IOException {
        //Codigo del pale para unir la ventana



        new VentanaConjuntos(null,false);   
        //Para fines practicos tengo el constructor sobrecargado para que
        //puedas ver la ventana de prueba, esta comentada la funcion rellenaInfo
        
    }
}