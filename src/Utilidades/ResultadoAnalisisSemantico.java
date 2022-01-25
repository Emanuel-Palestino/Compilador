package Utilidades;

import java.util.ArrayList;
import java.util.Stack;

public class ResultadoAnalisisSemantico {
    private Stack<String> pilaString;
    private ArrayList<String> entradaString;
    private ArrayList<String> accion;
    private String traduccionFinal;

    //Constructores
    public ResultadoAnalisisSemantico(Stack<String> pila, ArrayList<String> entrada, ArrayList<String> accion){
        pilaString = pila;
        entradaString = entrada;
        this.accion = accion;
        traduccionFinal = "";
    }


    //Getters 

    public ArrayList<String> getAccion() {
        return accion;
    }

    public Stack<String> getPilaString() {
        return pilaString;
    }

    public ArrayList<String> getEntradaString() {
        return entradaString;
    }

    public String getTraduccionFinal(){
        return traduccionFinal;
    }


    //Setters

    public void setAccion(ArrayList<String> accion){
        this.accion = accion;
    }

    public void setPilaString(Stack<String> pilaString){
        this.pilaString = pilaString;
    }

    public void setEntradaString(ArrayList<String> entradaString){
        this.entradaString = entradaString;
    }
    
    public void setTraduccionFinal(String traduccionFinal){
        this.traduccionFinal = traduccionFinal;
    }
    //Metodos
    	public String[][] getDatosTabla() {
		String[][] res = new String[pilaString.size()][3];
		for (int j = 0; j < pilaString.size(); j++) {
			res[j][0] = (pilaString.get(j) == null) ? "" : pilaString.get(j).toString();
			res[j][1] = entradaString.get(j).toString();
			res[j][2] = accion.get(j).toString();
		}

		return res;
	}

}
