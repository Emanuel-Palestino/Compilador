package Utilidades.Alfabeto;

import java.util.ArrayList;
import java.util.List;

public class Alfabeto {

	private List<String> simbolos;

	public Alfabeto(String[] simb) {
		simbolos = new ArrayList<String>();
		for (String simbolo : simb)
			simbolos.add(simbolo);
	}

	// Metodos
	public boolean simboloValido(String s) {
		if (simbolos.contains(s))
			return true;
		else
			return false;
	}
	
}
