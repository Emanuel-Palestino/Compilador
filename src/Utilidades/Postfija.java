package Utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Utilidades.Alfabeto.Alfabeto;

public class Postfija {

	public Postfija() {

	}

	// Metodos
	public String crear(String inf, Alfabeto alf) {
		// String[] infija = convertirArrayAlfabeto(inf, alf);
		String postfija = "";

		// Pila de Operadores
		Stack<String> operadores = new Stack<String>();

		int tamaño = inf.length();
		String simbolo;

		// Algorimo para transformar de forma Infija a Postfija
		for (int i = 0; i < tamaño; i++) {
			simbolo = "" + inf.charAt(i);

			if (alf.simboloValido(simbolo)) {
				postfija += simbolo;
			} else {
				while (!operadores.empty() && precedencia(operadores.peek(), simbolo))
					postfija += operadores.pop();
				if (operadores.empty() || !simbolo.equals(")"))
					operadores.push(simbolo);
				else
					operadores.pop();
			}
		}

		while (!operadores.empty())
			postfija += operadores.pop();

		return postfija;
	}

	private boolean precedencia(String op1, String op2) {
		boolean res;
		int priori1 = 0, priori2 = 0;
		switch (op1) {
		case ")":
			priori1 = 1;
			break;
		case "(":
			priori1 = 0;
			break;
		case "|":
			priori1 = 2;
			break;
		case "ª": // concatenacion
			priori1 = 3;
			break;
		case "?":
			priori1 = 4;
			break;
		case "+":
			priori1 = 4;
			break;
		case "*":
			priori1 = 4;
			break;

		}
		switch (op2) {
		case ")":
			priori2 = 0;
			break;
		case "(":
			priori2 = 1;
			break;
		case "|":
			priori2 = 2;
			break;
		case "ª": // Concatenacion
			priori2 = 3;
			break;
		case "?":
			priori2 = 4;
			break;
		case "+":
			priori2 = 4;
			break;
		case "*":
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
		else if (priori1 == 1) {
			System.out.println("Error: Ecuación errornea.");
			res = false;
		}

		if (priori1 != 0 && priori2 == 0)
			res = true;

		return res;
	}

	private String[] convertirArrayAlfabeto(String s, Alfabeto alf) {
		String palabra = "";
		List<String> cosas = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				palabra += s.charAt(j);
				if (alf.simboloValido(palabra) || esOperador(palabra)) {
					i = j;
					cosas.add(palabra);
					palabra = "";
					break;
				}
			}
		}
		return cosas.toArray(new String[0]);
	}

	private boolean esOperador(String o) {
		String[] res = o.split("[(|)|'*'|'?'|'+'|ª]+");
		if (res.length > 0)
			return false;
		else
			return true;
	}
}
