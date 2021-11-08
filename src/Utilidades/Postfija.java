package Utilidades;

import java.util.ArrayList;
import java.util.Stack;
import Utilidades.Excepciones.ExcepcionER;

public class Postfija {

	// Metodos
	public String[] crear(String inf) throws ExcepcionER {
		String[] infija = crearArrayInfija(inf);
		ArrayList<String> post = new ArrayList<String>();

		// Pila de Operadores
		Stack<String> operadores = new Stack<String>();

		// Algorimo para transformar de forma Infija a Postfija
		for (String simb : infija) {
			if (!esOperador(simb))
				post.add(simb);
			else {
				while (!operadores.empty() && precedencia(operadores.peek(), simb))
					post.add(operadores.pop());
				if (operadores.empty() || !simb.equals("┐"))
					operadores.push(simb);
				else
					operadores.pop();
			}
		}

		while (!operadores.empty())
			post.add(operadores.pop());
		
		return post.toArray(new String[0]);
	}

	private boolean precedencia(String op1, String op2) throws ExcepcionER {
		boolean res;
		int priori1 = 0, priori2 = 0;
		switch (op1) {
		case "┐":
			priori1 = 1;
			break;
		case "┌":
			priori1 = 0;
			break;
		case "ı":
			priori1 = 2;
			break;
		case "┼":
			priori1 = 3;
			break;
		case "º":
			priori1 = 4;
			break;
		case "ß":
			priori1 = 4;
			break;
		case "×":
			priori1 = 4;
			break;

		}
		switch (op2) {
		case "┐":
			priori2 = 0;
			break;
		case "┌":
			priori2 = 1;
			break;
		case "ı":
			priori2 = 2;
			break;
		case "┼":
			priori2 = 3;
			break;
		case "º":
			priori2 = 4;
			break;
		case "ß":
			priori2 = 4;
			break;
		case "×":
			priori2 = 4;
			break;
		}
		if (priori1 > priori2)
			res = true;
		else if (priori1 == priori2 && priori2 != 0)
			res = true;
		else
			res = false;

		if (priori1 != 1 && priori2 == 1)
			res = false;
		else if (priori1 == 1)
			throw new ExcepcionER();

		if (priori1 != 0 && priori2 == 0)
			res = true;

		return res;
	}

	private String[] crearArrayInfija(String cadenaInfija) {
		String[] infijaArreglo;
		infijaArreglo = cadenaInfija.split(" ");
		return infijaArreglo;
	}

	private boolean esOperador(String o) {
		String[] res = o.split("[┌|┐|×|º|ß|┼|ı]+");
		if (res.length > 0)
			return false;
		else
			return true;
	}

}
