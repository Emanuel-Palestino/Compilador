package Utilidades.Alfabeto;

import java.util.ArrayList;

public class Alfabeto {

	private ArrayList<String> simbolos;

	public Alfabeto(String[] simb) {
		simbolos = new ArrayList<String>();
		for (String simbolo : simb)
			simbolos.add(simbolo);
	}

	public Alfabeto(Alfabeto alf) {
		simbolos = new ArrayList<String>();
		ArrayList<String> lista = alf.getLista();
		for (String simbolo : lista)
			simbolos.add(simbolo);
	}

	// Metodos
	public Boolean simboloValido(String s) {
		if (simbolos.contains(s))
			return true;
		else if (simbolos.contains("letra") && letraValido(s) && !s.equals("ı") && !s.equals("ß"))
			return true;
		else if (simbolos.contains("digito") && digitoValido(s))
			return true;
		else
			return false;
	}

	public boolean letraValido(String s) {
		if (s.length() != 1)
			return false;
		char letra = s.charAt(0);
		return Character.isLetter(letra);
	}

	public boolean digitoValido(String s) {
		if (s.length() != 1)
			return false;
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
