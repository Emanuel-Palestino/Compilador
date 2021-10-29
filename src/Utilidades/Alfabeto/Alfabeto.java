package Utilidades.Alfabeto;

import java.util.ArrayList;

public class Alfabeto {

	private ArrayList<String> simbolos;

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

	public boolean letraValido(String s) {
		char letra = s.charAt(0);
		return Character.isLetter(letra);
	}

	public boolean digitoValido(String s) {
		char digito = s.charAt(0);
		return Character.isDigit(digito);
	}
	
	public String[] obtenerArreglo() {
		int tamaño = simbolos.size();
		String[] res = new String[tamaño];
		for (int i = 0; i < tamaño; i++)
			res[i] = simbolos.get(i);
		return res;
	}

	public ArrayList<String> getLista() {
		return simbolos;
	}

}
