package AnalizadorLexico.ConversionAFD;
import java.util.ArrayList;
import java.util.Stack;
import Utilidades.ConjuntoEstados;
import Utilidades.Automata;

//class como metodo de Thompson
public class CerraduraEpsilon{
    public static ConjuntoEstados doCerraduraEpsilon(ConjuntoEstados conjunto, Automata A ){  //metodo para hacer la cerradura  
    ConjuntoEstados resultado = new ConjuntoEstados();
    ArrayList<Integer>estadosEjemplo=conjunto.getEstados();
    Stack<Integer> T = new Stack<Integer>();                        //Inicializacion de pila "T"
    int verificador;                                                //Verificador almacena un estado especifico como entero




       // for(int i=0;i<resultado.getEstados().size();i++){                        //si no, se calcula el tamaño del arreglo de estados y se meten en la pila
       //     T.push(estadosEjemplo.get(i));
       // }


        for(Integer estado : estadosEjemplo){
            T.push(estado);
            resultado.insertarEstado(estado);
        }

    ArrayList<Integer> estadosResultado = resultado.getEstados();
    while(!T.isEmpty()){                                                          //while pila no este vacia
        verificador = T.pop(); 
        int[] arreglo = A.getEstadosDestinoSimbolo(verificador,"Ɛ");                                            //Se saca el tope de la pila
        for (Integer v : arreglo){            //for cada Epsilon (DETALLE: ¿cómo se representan transiciones multiples?)
            if(!estadosResultado.contains(v)){//si el estado que sigue no está en la pila
                resultado.insertarEstado(v);//el estado siguiente del automata se añado el estado
                T.push(v);    //el estado siguiente se añade a la pila
            }
        }
    }

    return resultado;
}
}


